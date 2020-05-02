#!/bin/bash

echo 'Starting piot-server...'
rm docker-compose.yml
wget https://raw.githubusercontent.com/alexkovalenko/piot/master/piot-server/docker-compose.yml
docker-compose up -d