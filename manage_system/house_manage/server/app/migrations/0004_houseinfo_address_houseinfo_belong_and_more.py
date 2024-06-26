# Generated by Django 4.2.1 on 2023-05-16 09:11

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('app', '0003_person_level_alter_person_is_out_alter_person_post_and_more'),
    ]

    operations = [
        migrations.AddField(
            model_name='houseinfo',
            name='address',
            field=models.CharField(default=0, max_length=40),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='houseinfo',
            name='belong',
            field=models.ForeignKey(default=0, on_delete=django.db.models.deletion.CASCADE, to='app.person'),
            preserve_default=False,
        ),
        migrations.AlterField(
            model_name='houseinfo',
            name='image',
            field=models.ImageField(upload_to='media/image/'),
        ),
        migrations.AlterField(
            model_name='houseinfo',
            name='is_live',
            field=models.CharField(max_length=1),
        ),
        migrations.AlterField(
            model_name='houseinfo',
            name='license',
            field=models.ImageField(upload_to='media/license/'),
        ),
    ]
