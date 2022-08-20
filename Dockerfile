FROM openjdk:17-jdk-alpine

WORKDIR /jar

COPY target/pokedex-api-1.0.jar .

ENTRYPOINT [ "java", "-jar", "pokedex-api-1.0.jar" ]
