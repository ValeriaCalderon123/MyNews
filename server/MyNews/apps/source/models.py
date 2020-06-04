from django.db import models

# Create your models here.

class Source (models.Model):

    name = models.CharField(max_length=50, null=False)
    host = models.CharField(max_length=100, null=False)
    search_url = models.CharField(max_length=200, null=False)
    homepage_articles_link = models.CharField(max_length=100, null=False)
    search_articles_link = models.CharField(max_length=100, null=False)
    category_articles_link = models.CharField(max_length=100, null=False)
    category_class = models.CharField(max_length=100, null=False)
    article_title_class = models.CharField(max_length=100, null=False)
    article_body_class = models.CharField(max_length=100, null=False)
    article_image_class = models.CharField(max_length=100)
    calification = models.IntegerField(default=100)