#!/bin/bash

set -e

cd favouritesService
mvn clean && mvn compile && mvn install
cd ..

cd loginService
mvn clean && mvn compile && mvn install
cd ..


cd favouritesService
mvn clean && mvn compile && mvn install
cd ..


#cd productsService
#mvn clean && mvn compile && mvn install
#cd ..

cd dockerCompose
sudo docker-compose up --build
