FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/authentication-service-0.0.1-SNAPSHOT.jar /app/authentication-service.jar

EXPOSE 8092

CMD ["java", "-jar", "/app/authentication-service.jar"]