"""
This module contents the serializers class for User Model
"""
from rest_framework import serializers
from django.contrib.auth.models import User


class UserSerializer(serializers.ModelSerializer):
    """
    This class is serializer for User instances
    """

    class Meta:
        """
         This class is the Meta Class for UserSerializer
         """
        model = User
        fields = ('id', 'username', 'first_name', 'last_name', 'email', 'password', 'is_superuser')

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

    def create(self, validated_data):
        user = super(UserSerializer, self).create(validated_data)
        user.set_password(validated_data['password'])
        user.save()
        return user


class SuperUserSerializer(UserSerializer):
    """
    This class is serializer for users who are super users
    """

    def create(self, validated_data):
        user = super(SuperUserSerializer, self).create(validated_data)
        user.is_superuser = True
        user.save()
        return user
