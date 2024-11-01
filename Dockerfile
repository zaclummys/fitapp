FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/fitapp-0.0.1-SNAPSHOT.jar /app/fitapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/fitapp.jar"]