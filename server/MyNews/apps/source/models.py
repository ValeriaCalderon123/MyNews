"""
This module contents Source nand Ctegory Model and relation betbeen them
"""
from django.db import models
from django.contrib.auth.models import User


class Source(models.Model):
    """
    This class is source model
    """
    name = models.CharField(max_length=50, null=False)
    host = models.CharField(max_length=100, null=False)
    search_url = models.CharField(max_length=200, null=False)
    homepage_articles_link = models.CharField(max_length=100, null=False)
    search_articles_link = models.CharField(max_length=100, null=False)
    category_articles_link = models.CharField(max_length=100, null=False)
    article_title_class = models.CharField(max_length=100, null=False)
    article_body_class = models.CharField(max_length=100, null=False)
    article_image_class = models.CharField(max_length=100)
    calification = models.IntegerField(default=100)

    def __str__(self):
        return str(self.name)


class Category(models.Model):
    """
    This class is category model
    """
    name = models.CharField(max_length=50)

    def __str__(self):
        return str(self.name)


class SourceCategory(models.Model):
    """
    This class is realation  between Source and Category
    """
    source = models.ForeignKey(Source, on_delete=models.CASCADE, related_name='categories')
    category = models.ForeignKey(Category, on_delete=models.CASCADE, related_name='sources')
    url = models.URLField(max_length=300, null=False)


class UserCategory(models.Model):
    """
    This class is realation  between User and Cateogory
    """
    category = models.ForeignKey(Category, on_delete=models.CASCADE, related_name='user')
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name='categories')
