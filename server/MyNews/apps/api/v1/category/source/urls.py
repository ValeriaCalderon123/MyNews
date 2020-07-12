#Learn more or give us feedback
from django.urls import path
from .views import  SourceCategoryListView, SourceCategoryCreateView

urlpatterns = [
    path('add/', SourceCategoryCreateView.as_view(), name='add'),
    path('', SourceCategoryListView.as_view(), name='list'),
]