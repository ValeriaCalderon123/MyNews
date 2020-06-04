from rest_framework import permissions
from rest_framework.templatetags import rest_framework


class AdminAuthenticationPermission(permissions.BasePermission):
    #ADMIN_ONLY_AUTH_CLASSES = [rest_framework.authentication.BasicAuthentication, rest_framework.authentication.SessionAuthentication]

    def has_permission(self, request, view):
        user = request.user
        if user: #and user.is_authenticated():
            return user.is_superuser
        return False