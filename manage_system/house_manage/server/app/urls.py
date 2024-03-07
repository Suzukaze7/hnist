from django.urls import path, include
from . import views

urlpatterns = [
    path("", views.index),
    path("login/", views.login),
    path("register/", views.register),
    path("user/", include([
        path("", views.get_user),
        path("modify/", views.modify_user)
    ])),
    path(
        "info/",
        include(
            [
                path("", views.get_info),
                path("audit/<int:id>/", views.info_audit),
                path("delete/<int:id>/", views.info_delete),
                path("submit/", views.info_submit),
            ]
        ),
    ),
]
