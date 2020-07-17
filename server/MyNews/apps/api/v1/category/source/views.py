from rest_framework import generics
from rest_framework.permissions import IsAuthenticated
from apps.source.serializers import SourceCategoryShowSerializer, SourceCategoryCreateUpdateSerializer
from apps.source.models import SourceCategory
from permissions.permissions import AdminAuthenticationPermission


class SourceCategoryListView(generics.ListCreateAPIView):
    queryset = SourceCategory.objects.all()
    serializer_class = SourceCategoryShowSerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)


class SourceCategoryCreateView(generics.CreateAPIView):
    queryset = SourceCategory.objects.all()
    serializer_class = SourceCategoryCreateUpdateSerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)
