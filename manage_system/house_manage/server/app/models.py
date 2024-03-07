from django.db import models
from django.conf import settings


class Person(models.Model):
    user = models.OneToOneField(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
    is_staff = models.BooleanField(default=False)
    name = models.CharField(max_length=5)
    gender = models.CharField(max_length=1, choices=[("男", "男"), ("男", "女")])
    work_num = models.CharField(max_length=15, null=True, blank=True)
    post = models.CharField(max_length=5, null=True, blank=True)
    is_out = models.CharField(
        max_length=1, null=True, blank=True, choices=[("是", "是"), ("否", "否")]
    )
    solve_num = models.SmallIntegerField(null=True, blank=True)


class HouseInfo(models.Model):
    person = models.ForeignKey("Person", on_delete=models.CASCADE)
    address = models.CharField(max_length=40)
    is_live = models.CharField(max_length=1)
    square = models.SmallIntegerField()
    image = models.ImageField(upload_to="image/")
    license = models.ImageField(upload_to="license/")
    is_audit = models.BooleanField(default=False)
