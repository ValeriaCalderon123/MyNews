"""
Serializers for Source and Category
"""
from rest_framework import serializers
from apps.user.serializers import UserSerializer
from .models import Source, Category, SourceCategory, UserCategory


class CategorySerializer(serializers.ModelSerializer):
    """
    Serializer for Category
    """

    class Meta:
        """
        Meta class for CategorySerializer
        """
        model = Category
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


class SourceCategoryCreateUpdateSerializer(serializers.ModelSerializer):
    """
    Serializer for relation between Source and Category
    """

    class Meta:
        """
        Meta class for SourceCategoryCreateUpdateSerializer
        """
        model = SourceCategory
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


class SourceCategoryShowSerializer(serializers.ModelSerializer):
    """
    Serializer for show relation between Source and Category
    """
    category = CategorySerializer()

    class Meta:
        """
        Meta class for SourceCategoryShowSerializer
        """

        model = SourceCategory
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


class SourceCreateUpdateSerializer(serializers.ModelSerializer):
    """
    Serializer for Source
    """
    class Meta:
        """
        Meta class for SourceCreateUpdateSerializer
        """
        model = Source
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


class SourceShowSerializer(serializers.ModelSerializer):
    """
    Serializer for show Source
    """
    categories = SourceCategoryShowSerializer(many=True)

    class Meta:
        """
        Meta class for SourceShowSerializer
        """
        model = Source
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


class UserCategoryCreateUpdateSerializer(serializers.ModelSerializer):
    """
    Serializer for relation between User and Category
    """
    class Meta:
        """
        Meta class for UserCategoryCreateUpdateSerializer
        """
        model = UserCategory
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


class UserCategoryShowSerializer(UserCategoryCreateUpdateSerializer):
    """
    Serializer for show relation between User and Category
    """
    user = UserSerializer()
    category = CategorySerializer()
