from django.shortcuts import get_object_or_404
from rest_framework import generics, status
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework.views import APIView
from apps.source.serializers import SourceSerializer, CategorySerializer, \
    SourceCategoryShowSerializer, SourceCategoryCreateUpdateSerializer
from apps.source.models import Source, Category, SourceCategory
from apps.user.serializers import UserSerializer, SuperUserSerializer
from django.contrib.auth.models import User
from permissions.permissions import AdminAuthenticationPermission


class UserListCreateAPIView(generics.ListCreateAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer


class AdminUserListCreateAPIView(generics.ListCreateAPIView):
    queryset = User.objects.filter(is_superuser=True)
    serializer_class = SuperUserSerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)


class AdminUpdateUserAPIView(APIView):
    queryset = User.objects.all()
    serializer_class = SuperUserSerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)

    def put(self, request, pk):
        user = get_object_or_404(User.objects.all(), id=pk)
        user.is_superuser = True
        user.save()
        return Response(self.serializer_class(user).data, status=status.HTTP_200_OK)


class Logout(APIView):

    def get(self, request, format=None):
        request.user.auth_token.delete()
        return Response(status=status.HTTP_200_OK)


class SourceListAPIView(generics.ListAPIView):
    queryset = Source.objects.all()
    serializer_class = SourceSerializer


class SourceCreateAPIView(generics.CreateAPIView):
    queryset = Source.objects.all()
    serializer_class = SourceSerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)


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


class SourceCategoryListView(generics.ListCreateAPIView):
    queryset = SourceCategory.objects.all()
    serializer_class = SourceCategoryShowSerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)


class SourceCategoryCreateView(generics.CreateAPIView):
    queryset = SourceCategory.objects.all()
    serializer_class = SourceCategoryCreateUpdateSerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)
