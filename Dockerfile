# Use Maven to build the JAR file
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Use the JAR file from the build stage
FROM openjdk:17
COPY --from=build /app/target/acebook-template-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080