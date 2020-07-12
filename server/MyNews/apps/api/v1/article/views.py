from django.shortcuts import get_object_or_404
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework.views import APIView
from apps.source.models import Source, Category
from etl.Search import Search
from etl.category import ExtractionByCategory
from apps.article.serializers import ArticleSerializer
from rest_framework import status


class SearchAPIView(APIView):
    permission_classes = (IsAuthenticated,)

    def get(self, request, *args, **kwargs):
        key = kwargs['key']
        search = Search(Source.objects.filter(calification__gte=0))
        search_results = search.search(key)
        return Response(ArticleSerializer(search_results, many=True).data)

class CategorySearchAPIView(APIView):
    permission_classes = (IsAuthenticated,)

    def get(self, request, *args, **kwargs):
        pk = kwargs['pk']
        if pk.isdigit():
            category = get_object_or_404(Category, pk=pk)
            search_results = ExtractionByCategory().extract(category)
            return Response(ArticleSerializer(search_results, many=True).data)
        else:
            return Response({'detail': 'Indice no valido'}, status=status.HTTP_400_BAD_REQUEST)
