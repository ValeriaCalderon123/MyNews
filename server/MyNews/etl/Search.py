from apps.source.models import Source

import requests
import bs4
import re
import logging

from etl import extraction

logger = logging.getLogger(__name__)

is_well_formed_url = re.compile(r'^https?://.+/.+$')
is_root_path = re.compile(r'^/.+$')


class Search:

    def __init__(self, sourceList):
        self.__sourceList__ = sourceList

    def search(self, search_key):
        articles_link = self.collect_results(search_key)
        saved_articles, unsaved_articles = extraction.saved_and_unsaved(articles_link)
        return extraction.join_save_and_unsaved_articles(saved_articles, unsaved_articles)


    def collect_results(self, search_key):
        logger.info('Collecting results to ' + search_key)
        articles_link = []
        for source in self.__sourceList__:
            articles_link = articles_link + self.searchInOneSource(source, search_key)
        return articles_link

    def searchInOneSource(self, source: Source, search_key: str):
        logger.info('Searching in ' + source.host)
        search_url = source.search_url + search_key
        response = requests.get(search_url)
        top_articles = []
        if response.status_code == 200:
            soup = bs4.BeautifulSoup(response.text, 'html.parser')
            articles_links = soup.select('.' + source.search_articles_link)
            if articles_links:
                try:
                    articles = [article.find('a')['href'] for article in articles_links]
                except:
                    logger.error('Error while scrapping results.')
                for i in range(min(10, len(articles))):
                    top_articles.append([extraction.build_link(source.host, articles[i]), source])
        return top_articles
