# Generated by Django 4.2.1 on 2023-05-16 12:15

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('app', '0007_rename_belong_houseinfo_person'),
    ]

    operations = [
        migrations.AddField(
            model_name='houseinfo',
            name='is_audit',
            field=models.BooleanField(default=False),
        ),
    ]