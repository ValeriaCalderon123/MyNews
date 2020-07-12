#Learn more or give us feedback
from django.urls import path, include

urlpatterns = [
    path('user/', include(("apps.api.v1.user.urls", "user"))),
    path('source/', include(("apps.api.v1.source.urls", "source"))),
    path('auth/', include(("apps.api.v1.auth.urls", "auth"))),
    path('category/', include(("apps.api.v1.category.urls", "category"))),
    path('article/', include(("apps.api.v1.article.urls", "article"))),
]