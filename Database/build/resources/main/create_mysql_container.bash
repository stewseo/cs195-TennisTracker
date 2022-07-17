#!/bin/bash

docker run --name jooq-dockerized -p 3306:3306 -v $(pwd)/migration:/docker-entrypoint-initdb.d -e MYSQL_ROOT_PASSWORD=sesame -d mysql:8.0