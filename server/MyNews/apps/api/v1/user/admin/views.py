from django.contrib.auth.models import User
from rest_framework import generics, status
from rest_framework.permissions import IsAuthenticated
from rest_framework.views import APIView
from django.shortcuts import get_object_or_404
from apps.user.serializers import SuperUserSerializer
from permissions.permissions import AdminAuthenticationPermission
from rest_framework.response import Response


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
