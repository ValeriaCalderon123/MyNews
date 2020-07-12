from rest_framework import generics
from rest_framework.permissions import IsAuthenticated
from apps.source.serializers import UserCategoryCreateUpdateSerializer, \
    UserCategoryShowSerializer
from apps.source.models import UserCategory

class UserCategoryListView(generics.ListCreateAPIView):
    queryset = UserCategory.objects.all()
    serializer_class = UserCategoryShowSerializer
    permission_classes = (IsAuthenticated,)


class UserCategoryRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = UserCategory.objects.all()
    serializer_class = UserCategoryShowSerializer
    permission_classes = (IsAuthenticated, )


class UserCategoryCreateView(generics.CreateAPIView):
    queryset = UserCategory.objects.all()
    serializer_class = UserCategoryCreateUpdateSerializer
    permission_classes = (IsAuthenticated)
