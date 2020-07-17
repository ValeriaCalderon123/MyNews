from rest_framework import serializers
from .models import Source, Category, SourceCategory, UserCategory
from apps.user.serializers import UserSerializer


class CategorySerializer(serializers.ModelSerializer):
    class Meta:
        model = Category
        fields = '__all__'


class SourceCategoryCreateUpdateSerializer(serializers.ModelSerializer):
    class Meta:
        model = SourceCategory
        fields = '__all__'


class SourceCategoryShowSerializer(serializers.ModelSerializer):
    category = CategorySerializer()
    class Meta:
        model = SourceCategory
        fields = '__all__'


class SourceCreateUpdateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Source
        fields = '__all__'

class SourceShowSerializer(serializers.ModelSerializer):
    categories = SourceCategoryShowSerializer(many=True)
    class Meta:
        model = Source
        fields = '__all__'

class UserCategoryCreateUpdateSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserCategory
        fields = '__all__'


class UserCategoryShowSerializer(UserCategoryCreateUpdateSerializer):
    user = UserSerializer()
    category = CategorySerializer()
