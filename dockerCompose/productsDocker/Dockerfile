FROM openjdk:17
ARG FILENAME=productsService-0.0.1-SNAPSHOT.jar
COPY ${FILENAME} /
ENTRYPOINT ["java", "-jar", "${FILENAME}"]