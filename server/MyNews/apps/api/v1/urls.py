#Learn more or give us feedback
from django.urls import path
from rest_framework.authtoken import views

from .views import UserListCreateAPIView, AdminUserListCreateAPIView, Logout, AdminUpdateUserAPIView, SourceListAPIView, SourceCreateAPIView

urlpatterns = [
    path('user/', UserListCreateAPIView.as_view(), name='user-list-create'),
    path('user/admin/', AdminUserListCreateAPIView.as_view(), name='user-admin-list-create'),
    path('user/admin/<pk>', AdminUpdateUserAPIView.as_view(), name='user-admin-update-create'),
    path('source/', SourceListAPIView.as_view(), name='login'),
    path('source/add/', SourceCreateAPIView.as_view(), name='login'),
    path('auth/', views.obtain_auth_token, name='login'),
    path('logout/', Logout.as_view(), name='logout'),
]