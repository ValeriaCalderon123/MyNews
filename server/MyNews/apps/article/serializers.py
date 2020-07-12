from rest_framework import serializers

from apps.article.models import Article
from apps.source.serializers import SourceSerializer


class ArticleSerializer(serializers.ModelSerializer):
    source = SourceSerializer()

    class Meta:
        model = Article
        fields = '__all__'
