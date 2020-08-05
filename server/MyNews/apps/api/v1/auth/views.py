"""
This module is for actions that involves user authenticated
"""
from rest_framework import status, generics
from rest_framework.authentication import TokenAuthentication
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework.views import APIView

from apps.user.serializers import UserSerializer


class Logout(APIView):
    """
    This method serves for close session
    """
    permission_classes = (IsAuthenticated,)

    def get(self, request):
        """
        Close session from User
        :param request:Request from HTTPClient
        :return: HTTPResponse in status 200
        """
        print(self.request.user, " wants close session")
        request.user.auth_token.delete()
        return Response(status=status.HTTP_200_OK)


class CurrentUserAPIView(generics.RetrieveUpdateAPIView):
    """
    This class is for get the user athenticated for API VIEW
    """
    permission_classes = (IsAuthenticated,)
    authentication_class = (TokenAuthentication,)
    serializer_class = UserSerializer

    def get_object(self):
        """
        This method is for get get instance of current user authenticated
        :return: user athenticated
        """
        return self.request.user
