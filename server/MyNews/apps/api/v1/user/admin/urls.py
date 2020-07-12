from django.urls import path
from rest_framework.authtoken import views

from apps.api.v1.user.admin.views import AdminUserListCreateAPIView, AdminUpdateUserAPIView

urlpatterns = [
    path('', AdminUserListCreateAPIView.as_view(), name='list-create'),
    path('<pk>', AdminUpdateUserAPIView.as_view(), name='update-create'),

]