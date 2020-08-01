"""
This module contents serializers for Article
"""
from rest_framework import serializers
from apps.article.models import Article
from apps.source.serializers import SourceShowSerializer


class ArticleSerializer(serializers.ModelSerializer):
    """
    This class is a serializer for Article model
    """
    source = SourceShowSerializer()

    class Meta:
        """
        This is a Meta class for Article Serializer
        """
        model = Article
        fields = '__all__'

        def get_model(self):
            """
            This method returns de Model
            :return: model
            """
            return self.model

        def get_field(self):
            """
            This method returns fields
            :return: fields
            """
            return self.fields
