# reservation-command-service/Dockerfile
FROM eclipse-temurin:21-jdk-jammy
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]
