FROM openjdk:24-alpine

WORKDIR /app

COPY target/shortener-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]