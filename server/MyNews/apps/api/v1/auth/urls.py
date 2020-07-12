#Learn more or give us feedback
from django.urls import path, include
from rest_framework.authtoken import views

from .views import Logout, AuthCategory

urlpatterns = [
    path('', views.obtain_auth_token, name='login'),
    path('logout/', Logout.as_view(), name='logout'),
    path('category/', AuthCategory.as_view(), name='category'),
]