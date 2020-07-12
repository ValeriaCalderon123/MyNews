from django.urls import path, include
from apps.api.v1.user.views import UserListCreateAPIView
urlpatterns = [
    path('', UserListCreateAPIView.as_view(), name='list-create'),
    path('admin/', include(("apps.api.v1.user.admin.urls", "admin"))),
]