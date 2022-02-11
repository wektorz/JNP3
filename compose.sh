#!/bin/bash

set -e

cd favouritesService
mvn clean compile install
cd ..

cd loginService
mvn clean compile install
cd ..


cd shoppingCartService
mvn clean compile install
cd ..


cd productsService
mvn clean compile install
cd ..

cd dockerCompose
sudo docker-compose up --build
