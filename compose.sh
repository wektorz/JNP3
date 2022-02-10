#!/bin/bash

cd favouritesService
mvn compile
mvn install
cd ..

cd loginService
mvn compile
mvn install
cd ..


cd favouritesService
mvn compile
mvn install
cd ..


#cd productsService
#mvn compile
#mvn install
#cd ..

cd dockerCompose
sudo docker-compose up --build
