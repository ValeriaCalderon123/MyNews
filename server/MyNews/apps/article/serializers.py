from rest_framework import serializers

from apps.article.models import Article
from apps.source.serializers import SourceShowSerializer


class ArticleSerializer(serializers.ModelSerializer):
    source = SourceShowSerializer()

    class Meta:
        model = Article
        fields = '__all__'
