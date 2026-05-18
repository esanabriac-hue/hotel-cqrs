FROM eclipse-temurin:21-jre-alpine
# Asegúrate de que el nombre del JAR coincida con el que generó Maven
COPY target/reservation-query-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app.jar"]
