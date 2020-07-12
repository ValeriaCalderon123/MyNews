import logging

import bs4
import requests

from apps.source.models import Category, SourceCategory, Source
from etl import extraction

logger = logging.getLogger(__name__)

class ExtractionByCategory:

    def extract(self, category: Category):
        articles_link = self.collect_results(category)
        saved_articles, unsaved_articles = extraction.saved_and_unsaved(articles_link)
        return extraction.join_save_and_unsaved_articles(saved_articles, unsaved_articles)

    def collect_results(self, category: Category):
        sources_categories = SourceCategory.objects.filter(category=category)
        articles_links = []
        for source_category in sources_categories:
            articles_links = articles_links + self.collect_results_by_source(source_category.source, source_category.url)
        return articles_links

    def collect_results_by_source(self, source: Source, url:str):
        response = requests.get(url)
        top_articles = []
        if response.status_code==200:
            soup = bs4.BeautifulSoup(response.text, 'html.parser')
            try:
                articles_links = soup.select('.' + source.category_articles_link)
                articles = [article.find('a')['href'] for article in articles_links]
                for i in range(min(10, len(articles))):
                    top_articles.append([extraction.build_link(source.host, articles[i]), source])

            except:
                logger.error('No se puede extraer de ' + url)
        else:
            logger.error('No se pudo acceder a ' + url)
        return top_articles
