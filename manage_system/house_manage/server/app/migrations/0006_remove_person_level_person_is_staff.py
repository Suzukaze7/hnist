# Generated by Django 4.2.1 on 2023-05-16 10:35

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('app', '0005_alter_houseinfo_image_alter_houseinfo_license'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='person',
            name='level',
        ),
        migrations.AddField(
            model_name='person',
            name='is_staff',
            field=models.BooleanField(default=False),
        ),
    ]
