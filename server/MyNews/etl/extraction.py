import hashlib
import logging
import re
import random
import bs4
import requests
from apps.article.models import Article
from apps.source.models import Source

logger = logging.getLogger(__name__)

is_well_formed_url = re.compile(r'^https?://.+/.+$')
is_root_path = re.compile(r'^/.+$')


def build_link(host, link):
    logger.info('Building link ' + link)
    if is_well_formed_url.match(link):
        return link
    elif is_root_path.match(link):
        return '{host}{uri}'.format(host=host, uri=link[1:len(link)])
    else:
        return '{host}/{uri}'.format(host=host, uri=link)


def scrap_article(url: str, source: Source):
    logger.info('Scrapping ' + url)
    response = requests.get(url)
    if response.status_code == 200:
        soup = bs4.BeautifulSoup(response.text, 'html.parser')
        article = Article()
        article.uuid = hashlib.md5(bytes(url.encode())).hexdigest()
        article.url = url
        article.source = source
        try:
            article.title = soup.select('.' + source.article_title_class)[0].text
            article.body = soup.select('.' + source.article_body_class)[0].text
            article.image = soup.select('.' + source.article_image_class)[0]['src']
            article.save()
            return article
        except:
            logger.error('Error while scrapping article ' + url)
            return None
    return None


def join_save_and_unsaved_articles(saved_articles, unsaved_articles):
    if len(saved_articles) == 10:
        return saved_articles
    if len(saved_articles) > 10:
        return saved_articles[0:10]
    while len(saved_articles) < 10 and len(unsaved_articles) > 0:
        index = random.randint(0, len(unsaved_articles) - 1)
        article_link = unsaved_articles[index]
        unsaved_articles.pop(index)
        new_article = scrap_article(article_link[0], article_link[1])
        if new_article is not None:
            saved_articles.append(new_article)
    return saved_articles


def saved_and_unsaved(articles_link):
    logger.info('Searching  in saved articles')
    saved_articles = []
    unsaved_articles = []
    for article in articles_link:
        uuid = hashlib.md5(bytes(article[0].encode())).hexdigest()
        article_saved = Article.objects.filter(uuid=str(uuid))
        if article_saved:
            saved_articles.append(article_saved[0])
        else:
            unsaved_articles.append(article)
    saved_articles.sort(key=lambda article: article.heuristic, reverse=True)
    return saved_articles, unsaved_articles
