from django.shortcuts import get_object_or_404
from rest_framework import generics, status
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework.views import APIView
from apps.source.serializers import SourceSerializer, CategorySerializer, \
    SourceCategoryShowSerializer, SourceCategoryCreateUpdateSerializer, UserCategoryCreateUpdateSerializer, \
    UserCategoryShowSerializer
from apps.source.models import Source, Category, SourceCategory, UserCategory
from apps.user.serializers import UserSerializer, SuperUserSerializer
from django.contrib.auth.models import User
from permissions.permissions import AdminAuthenticationPermission
from etl.Search import Search
from apps.article.serializers import ArticleSerializer


class SourceListAPIView(generics.ListAPIView):
    queryset = Source.objects.filter(calification__gte=0)
    serializer_class = SourceSerializer


class SourceCreateAPIView(generics.CreateAPIView):
    queryset = Source.objects.all()
    serializer_class = SourceSerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)


class CalificateSourceAPIView(APIView):
    permission_classes = (IsAuthenticated,)

    def put(self, request, pk):
        if pk.isdigit():
            source= get_object_or_404(Source, pk=pk)
            calification = request.data.get('calification')
            calification_num = 0
            edit = False
            if calification.__class__ == int and calification is not  0:
                calification_num = calification/abs(calification)
                edit = True
            elif calification.__class__ == str and calification.isdigit() :
                edit = True
                if int(calification )> 0:
                    calification_num = 1
                elif (calification) < 0:
                    calification_num = -1
            if edit:
                source.calification = source.calification + calification_num
                source.save()
                return Response(SourceSerializer(source).data)
        return Response({'detail': 'Bad Request'}, status=  status.HTTP_400_BAD_REQUEST)
