# Pokemon-API

Welcome to Pokemon-API - An Open API for Java-Spring-Docker labs.

---

| Information | Version |
|:------------|:---------:|
|This API provides Kanto's Pokemons only with number, name and image.| 1.0 |

---

## Installation

### Local build

In order to run the local version, it is necessary to build the jar.
With the command bellow, a .jar file will be created inside target folder.

    mvn package

To be able to run the API in a container, it is first required to create an image.
The command bellow will build a image using the provided dockerfile.

    docker build -t pokemon-api .

To start setting up the containers, we will create a network where the containers will be running.
This step is necessary so we can have an isolated network for the containers and they can comunicate with each other.

    docker network create pokemon-net

Before we run the API, we will set up the database, to run within the network that we've just created.
With command bellow, it will start a container running postgres 12.

    docker run -d -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin123 --net=pokemon-net --name=pokemon-db postgres:12

Now we are ready to run the Pokemon-API container that connects to a database.
To do so, simply execute the following command:

    docker run -d -p 80:8080 --net=pokemon-net -e DB_HOST=pokemon-db -e DB_PORT=5432 -e DB_USER=admin -e DB_PASS=admin123 --name=pokemon-api pokemon-api

## Stack

<a href="https://www.java.com/" title="Java"><img src="https://github.com/get-icon/geticon/raw/master/icons/java.svg" alt="Java" width="42px" height="42px"></a>
<a href="https://spring.io/" title="Spring"><img src="https://github.com/get-icon/geticon/raw/master/icons/spring.svg" alt="Spring" width="42px" height="42px"></a>
<a href="https://www.postgresql.org/" title="PostgreSQL"><img src="https://github.com/get-icon/geticon/raw/master/icons/postgresql.svg" alt="PostgreSQL" width="42px" height="42px"></a>
<a href="https://www.docker.com/" title="docker"><img src="https://github.com/get-icon/geticon/raw/master/icons/docker-icon.svg" alt="docker" width="42px" height="42px"></a>