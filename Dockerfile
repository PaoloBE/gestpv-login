# Build stage
FROM maven:3.8.7-eclipse-temurin-17-alpine AS build
COPY pom.xml /app/
COPY Dockerfile /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package -DskipTests

# Run stage
FROM openjdk:17-alpine
EXPOSE 8581
COPY --from=build /app/Dockerfile Dockerfile
COPY --from=build /app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar","/app/app.jar"]