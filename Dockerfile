FROM eclipse-temurin:21
LABEL maintainer="Igor.silvabrito@outlook.com"
WORKDIR /app
COPY target/security-0.0.1-SNAPSHOT.jar /app/security.jar
ENTRYPOINT ["java", "-jar", "security.jar"]