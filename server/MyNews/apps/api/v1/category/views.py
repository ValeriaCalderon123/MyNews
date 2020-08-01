"""
This module contents  API view for Category
"""
from rest_framework import generics
from rest_framework.permissions import IsAuthenticated
from apps.source.serializers import CategorySerializer
from apps.source.models import  Category
from permissions.permissions import AdminAuthenticationPermission


class CategoryListView(generics.ListAPIView):
    """
    This class shows a list for categories
    """
    queryset = Category.objects.all()
    serializer_class = CategorySerializer
    permission_classes = (IsAuthenticated,)


class CategoryCreateView(generics.CreateAPIView):
    """
    This class creates categories
    """
    queryset = Category.objects.all()
    serializer_class = CategorySerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)


class CategoryRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    """
    This class update and delete categories
    """
    queryset = Category.objects.all()
    serializer_class = CategorySerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)
