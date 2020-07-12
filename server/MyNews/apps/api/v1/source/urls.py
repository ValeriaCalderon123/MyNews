#Learn more or give us feedback
from django.urls import path, include

from .views import SourceListAPIView, SourceCreateAPIView, CalificateSourceAPIView

urlpatterns = [
    path('', SourceListAPIView.as_view(), name='list'),
    path('add/', SourceCreateAPIView.as_view(), name='add'),
    path('calificate/<pk>', CalificateSourceAPIView.as_view(), name='calificate'),

]