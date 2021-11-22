FROM openjdk:11-slim as build

MAINTAINER example.com

COPY target/message-0.0.1-SNAPSHOT.jar message-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/message-0.0.1-SNAPSHOT.jar"]