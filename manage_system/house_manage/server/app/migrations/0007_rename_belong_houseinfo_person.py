# Generated by Django 4.2.1 on 2023-05-16 11:33

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('app', '0006_remove_person_level_person_is_staff'),
    ]

    operations = [
        migrations.RenameField(
            model_name='houseinfo',
            old_name='belong',
            new_name='person',
        ),
    ]
