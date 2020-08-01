"""
This view is method for API view of Sources in first version
"""
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
    """
    Returns list of sources
    """
    queryset = Source.objects.filter(calification__gte=0)
    serializer_class = SourceShowSerializer


class SourceCreateAPIView(generics.CreateAPIView):
    """
    Interface to create sources
    """
    queryset = Source.objects.all()
    serializer_class = SourceCreateUpdateSerializer
    permission_classes = (IsAuthenticated, AdminAuthenticationPermission)


class CalificateSourceAPIView(APIView):
    """
    Interface to evaluate sources
    """
    permission_classes = (IsAuthenticated,)

    def put(self, request, pk):
        """
        :param request: Request object
        :param pk: primary key of articcle
        :return: Response object
        """
        print(self.request.user, " wants claficate an article")
        article = get_object_or_404(Article, pk=pk)
        calification = request.data.get('calification')
        calification_num = 0
        edit = False
        neutral_evaluation = 0
        if calification.__class__ == int and calification is not neutral_evaluation:
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
