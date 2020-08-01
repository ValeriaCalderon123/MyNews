#!/bin/bash

testing(){
	echo "Testing $1"
	/home/nelson/anaconda3/envs/MyNews/bin/pylint $1
}

#Fuentes y categorias
testing "apps/api/v1/article/views.py"
testing "apps/api/v1/category/views.py"
testing "apps/source/serializers.py"
testing "apps/api/v1/source/views.py"
testing "apps/source/models.py"

# Articulos
testing "etl/search.py"
testing "etl/extraction.py"
testing "apps/article/serializers.py"
testing "apps/article/models.py"

# Usuarios
testing "apps/user/serializers.py"
testing "apps/api/v1/user/views.py"
testing "apps/api/v1/auth/views.py"
testing "apps/api/v1/auth/urls.py"
