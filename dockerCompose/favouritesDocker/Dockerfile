FROM openjdk:17
ARG FILENAME=favouritesService-0.0.1-SNAPSHOT.jar
COPY ${FILENAME} /
ENTRYPOINT ["java", "-jar", "${FILENAME}"]