"""
This module contents the views for the Model User to the first version to API Rest Service
"""
from django.contrib.auth.models import User
from rest_framework import generics
from apps.user.serializers import UserSerializer


class UserListCreateAPIView(generics.ListCreateAPIView):
    """
    This class register and list the user instances
    """
    queryset = User.objects.all()
    serializer_class = UserSerializer
