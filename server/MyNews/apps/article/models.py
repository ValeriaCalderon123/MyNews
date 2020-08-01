"""
This module contents Article model
"""
import nltk
from django.db import models
from nltk.corpus import stopwords
from apps.source.models import Source

stop_words = set(stopwords.words('spanish'))


def n_tokens(value):
    """
    return the quantite of tokens in a string
    :param value: value to search tokens
    :return: quantite of tokens
    """
    tokens = nltk.word_tokenize(value)
    tokens = list(filter(lambda token: token.isalpha(), tokens))
    tokens = list(map(lambda token: token.lower(), tokens))
    tokens = list(filter(lambda word: word not in stop_words, tokens))
    return len(tokens)


class Article(models.Model):
    """
    This class is tha Article model
    """
    uuid = models.UUIDField(primary_key=True)
    title = models.CharField(max_length=30000, null=False)
    body = models.CharField(max_length=3000000, null=False)
    url = models.URLField(max_length=40000)
    source = models.ForeignKey(Source, on_delete=models.CASCADE,\
                               default=None, related_name='articles')
    image = models.URLField(max_length=40000, default=None)

    @property
    def n_tokens_title(self):
        """
        Get the tokens in the title of Article
        :return: int value
        """
        return n_tokens(self.title)

    @property
    def n_tokens_body(self):
        """
        Get the tokens in the body of Article
        :return: int value
        """
        return n_tokens(self.body)

    @property
    def heuristic(self):
        """
        Returns a number that represents the value of a Article
        :return: int value
        """
        return (self.n_tokens_body + self.n_tokens_title) * self.source.calification / 200

    def __str__(self):
        """
        Return str to a Article instance
        :return: str value
        """
        return str(self.title)
