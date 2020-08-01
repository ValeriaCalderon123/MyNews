"""
This module is for actions that involves user authenticated
"""
from rest_framework import status
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework.views import APIView


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
