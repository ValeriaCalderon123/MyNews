#Learn more or give us feedback
from django.urls import path
from rest_framework.authtoken import views

from .views import UserListCreateAPIView, AdminUserListCreateAPIView, Logout, AdminUpdateUserAPIView, \
    SourceListAPIView, SourceCreateAPIView, CategoryListView, CategoryCreateView, \
    CategoryRetrieveUpdateDestroyView, SourceCategoryListView, SourceCategoryCreateView

urlpatterns = [
    path('user/', UserListCreateAPIView.as_view(), name='user-list-create'),
    path('user/admin/', AdminUserListCreateAPIView.as_view(), name='user-admin-list-create'),
    path('user/admin/<pk>', AdminUpdateUserAPIView.as_view(), name='user-admin-update-create'),
    path('source/', SourceListAPIView.as_view(), name='source-list'),
    path('source/add/', SourceCreateAPIView.as_view(), name='source-add'),
    path('auth/', views.obtain_auth_token, name='login'),
    path('logout/', Logout.as_view(), name='logout'),
    path('category/', CategoryListView.as_view(), name='category-list'),
    path('category/add/', CategoryCreateView.as_view(), name='category-add'),
    path('category/<pk>', CategoryRetrieveUpdateDestroyView.as_view(), name='category-retrieve-destroy'),
    path('source/category/add/', SourceCategoryCreateView.as_view(), name='category-add'),
    path('source/category/', SourceCategoryListView.as_view(), name='category-list'),
]