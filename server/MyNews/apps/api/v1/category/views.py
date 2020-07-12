from rest_framework import generics, status
from rest_framework.permissions import IsAuthenticated
from apps.source.serializers import CategorySerializer
from apps.source.models import  Category
from permissions.permissions import AdminAuthenticationPermission


class CategoryListView(generics.ListAPIView):
    queryset = Category.objects.all()
    serializer_class = CategorySerializer
    permission_classes = (IsAuthenticated,)


class CategoryCreateView(generics.CreateAPIView):
    queryset = Category.objects.all()
    serializer_class = CategorySerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)


class CategoryRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Category.objects.all()
    serializer_class = CategorySerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)
