#Learn more or give us feedback
from django.urls import path
from .views import SearchAPIView, CategorySearchAPIView

urlpatterns = [
    path('search/<str:key>', SearchAPIView.as_view(), name='search'),
    path('category/<pk>', CategorySearchAPIView.as_view(), name='category'),

]