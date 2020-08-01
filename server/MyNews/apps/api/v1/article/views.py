"""
THis module has views to get articles
"""
from django.shortcuts import get_object_or_404
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from etl.search import Search
from etl.category import ExtractionByCategory
from apps.article.serializers import ArticleSerializer
from apps.source.models import Source, Category

class SearchAPIView(APIView):
    """
    Search arcticles y variois sources
    """
    permission_classes = (IsAuthenticated,)

    def get(self, request, *args, **kwargs):
        """
        Search by key
        :param request: Request object
        :param args: Arguments
        :param kwargs: Key arguments
        :return: Response object
        """
        print(args.__class__, request, self.__class__)
        key = kwargs['key']
        search = Search(Source.objects.filter(calification__gte=0))
        search_results = search.search(key)
        return Response(ArticleSerializer(search_results, many=True).data)


class CategorySearchAPIView(APIView):
    """
    Search by Category
    """
    permission_classes = (IsAuthenticated,)

    def get(self, request, *args, **kwargs):
        """
        Search by category
        :param request: Request object
        :param args: Arguments
        :param kwargs: Key arguments
        :return: Response object
        """
        print(args.__class__, request, self.__class__)
        primary_key = kwargs['pk']
        if primary_key.isdigit():
            category = get_object_or_404(Category, pk=primary_key)
            search_results = ExtractionByCategory().extract(category)
            return Response(ArticleSerializer(search_results, many=True).data)
        return Response({'detail': 'Indice no valido'}, status=status.HTTP_400_BAD_REQUEST)
