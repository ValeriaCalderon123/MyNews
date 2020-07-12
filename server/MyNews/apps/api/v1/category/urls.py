#Learn more or give us feedback
from django.urls import path, include
from rest_framework.authtoken import views

from .views import CategoryListView, \
    CategoryCreateView, CategoryRetrieveUpdateDestroyView
urlpatterns = [
    path('', CategoryListView.as_view(), name='list'),
    path('add/', CategoryCreateView.as_view(), name='add'),
    path('<pk>', CategoryRetrieveUpdateDestroyView.as_view(), name='retrieve-destroy'),
    path('source/', include(("apps.api.v1.category.source.urls", "source"))),
    path('user/', include(("apps.api.v1.category.user.urls", "user"))),
]