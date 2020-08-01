"""
This module has the urls for the views to User in the first version to API Rest Service
"""
from django.urls import path
from rest_framework.authtoken import views

from .views import Logout

urlpatterns = [
    path('', views.obtain_auth_token, name='login'),
    path('logout/', Logout.as_view(), name='logout'),
]
