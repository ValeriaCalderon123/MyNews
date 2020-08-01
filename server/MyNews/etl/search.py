"""
This module search a key word in sources list
"""

import re
import logging
import requests
import bs4
from apps.source.models import Source
from etl import extraction

logger = logging.getLogger(__name__)

is_well_formed_url = re.compile(r'^https?://.+/.+$')
is_root_path = re.compile(r'^/.+$')


def search_in_one_source(source: Source, search_key: str):
    """
    Search in one source
    :param source: Source to search
    :param search_key: keyword to search
    :return: results from a source in link form
    """
    logger.info('Searching in %s', source.host)
    search_url = source.search_url + search_key
    response = requests.get(search_url)
    top_articles = []
    if response.status_code == 200:
        soup = bs4.BeautifulSoup(response.text, 'html.parser')
        articles_links = soup.select('.' + source.search_articles_link)
        if articles_links:
            try:
                articles = [article.find('a')['href'] for article in articles_links]
            except (KeyError, TypeError):
                logger.error('Error while scrapping results.')
            for i in range(min(10, len(articles))):
                top_articles.append([extraction.build_link(source.host, articles[i]), source])
    return top_articles


class Search:
    """
    This class search in sources
    """

    def __init__(self, sourceList):
        """
        Build Seach instance
        :param sourceList: Source list where this instance cans search
        """
        self.__source_list__ = sourceList

    def search(self, search_key):
        """
        Search ins sources
        :param search_key: Articles list results
        :return:
        """
        articles_link = self.collect_results(search_key)
        saved_articles, unsaved_articles = extraction.saved_and_unsaved(articles_link)
        return extraction.join_save_and_unsaved_articles(saved_articles, unsaved_articles)

    def collect_results(self, search_key):
        """
        Gets results from sources
        :param search_key: key word to search in sources
        :return: links of results
        """
        logger.info('Collecting results to %s', search_key)
        articles_link = []
        for source in self.__source_list__:
            articles_link = articles_link + search_in_one_source(source, search_key)
        return articles_link
