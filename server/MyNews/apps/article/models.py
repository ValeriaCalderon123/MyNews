from django.db import models
from apps.source.models import Source
import nltk
from nltk.corpus import stopwords

stop_words = set(stopwords.words('spanish'))


# Create your models here.
class Article(models.Model):
    uuid = models.UUIDField(primary_key=True)
    title = models.CharField(max_length=30000, null=False)
    body = models.CharField(max_length=3000000, null=False)
    url = models.URLField(max_length=40000)
    source = models.ForeignKey(Source, on_delete=models.CASCADE, default=None, related_name='articles')
    image = models.URLField(max_length=40000, default=None)

    @property
    def n_tokens_title(self):
        return self.n_tokens(self.title)

    @property
    def n_tokens_body(self):
        return self.n_tokens(self.body)

    def n_tokens(self, value):
        tokens = nltk.word_tokenize(value)
        tokens = list(filter(lambda token: token.isalpha(), tokens))
        tokens = list(map(lambda token: token.lower(), tokens))
        tokens = list(filter(lambda word: word not in stop_words, tokens))
        return len(tokens)

    @property
    def heuristic(self):
        return (self.n_tokens_body + self.n_tokens_title) * self.source.calification / 200

    def __str__(self):
        return self.title