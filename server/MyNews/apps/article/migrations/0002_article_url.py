# Generated by Django 3.0.3 on 2020-07-09 02:12

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('article', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='article',
            name='url',
            field=models.URLField(default='http://localhost/', max_length=400),
        ),
    ]
