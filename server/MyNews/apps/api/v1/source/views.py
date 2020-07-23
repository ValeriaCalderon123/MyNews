from django.shortcuts import get_object_or_404
from rest_framework import generics, status
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework.views import APIView

from apps.article.models import Article
from apps.source.serializers import SourceCreateUpdateSerializer, SourceShowSerializer
from apps.source.models import Source
from permissions.permissions import AdminAuthenticationPermission


class SourceListAPIView(generics.ListAPIView):
    queryset = Source.objects.filter(calification__gte=0)
    serializer_class = SourceShowSerializer


class SourceCreateAPIView(generics.CreateAPIView):
    queryset = Source.objects.all()
    serializer_class = SourceCreateUpdateSerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)


class CalificateSourceAPIView(APIView):
    permission_classes = (IsAuthenticated,)

    def put(self, request, pk):
        article = get_object_or_404(Article, pk=pk)
        calification = request.data.get('calification')
        calification_num = 0
        edit = False
        if calification.__class__ == int and calification is not 0:
            calification_num = calification / abs(calification)
            edit = True
        elif calification.__class__ == str and calification.isdigit():
            edit = True
            if int(calification) > 0:
                calification_num = 1
            elif (calification) < 0:
                calification_num = -1
        if edit:
            article.source.calification = article.source.calification + calification_num
            article.source.save()
            return Response(SourceShowSerializer(article.source).data)
        return Response({'detail': 'Bad Request'}, status=status.HTTP_400_BAD_REQUEST)
