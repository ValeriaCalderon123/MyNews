"""
This module is for extract article from Web
"""
import hashlib
import logging
import re
import random
import bs4
import requests
from django.core.exceptions import ValidationError

from apps.article.models import Article
from apps.source.models import Source

logger = logging.getLogger(__name__)

is_well_formed_url = re.compile(r'^https?://.+/.+$')
is_root_path = re.compile(r'^/.+$')


def build_link(host, link):
    """
    This method is for build corret URLS
    :param host: host del link
    :param link: link to correct
    :return: link corrected
    """
    logger.info('Building link %s', link)
    if is_well_formed_url.match(link):
        return link
    if is_root_path.match(link):
        return '{host}{uri}'.format(host=host, uri=link[1:len(link)])
    return '{host}/{uri}'.format(host=host, uri=link)


def scrap_article(url: str, source: Source):
    """
    This method extract article from web
    :param url: URL of article
    :param source:  Source of Article
    :return: Article
    """
    logger.info('Scrapping %s', url)
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
        except IndexError:
            logger.error('Error while scrapping article %s', url)
            return None
        except ValidationError:
            logger.error('Error while validating article %s', url)
            return None
    return None


def join_save_and_unsaved_articles(saved_articles, unsaved_articles):
    """
    Joins artciles and and saved articles
    :param saved_articles: article saved
    :param unsaved_articles: link of article unsaved
    :return: List from all articles
    """
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
    """
    Separate articles in saved and unsaved
    :param articles_link: articles to show
    :return:list of articles saved and list of articles no saved
    """
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
