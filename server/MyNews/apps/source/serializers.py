from rest_framework import serializers
from .models import Source, Category, SourceCategory


class SourceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Source
        fields = '__all__'


class CategorySerializer(serializers.ModelSerializer):
    class Meta:
        model = Category
        fields = '__all__'


class SourceCategoryCreateUpdateSerializer(serializers.ModelSerializer):
    class Meta:
        model = SourceCategory
        fields = '__all__'


class SourceCategoryShowSerializer(SourceCategoryCreateUpdateSerializer):
    source = SourceSerializer()
    category = CategorySerializer()
