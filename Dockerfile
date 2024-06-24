# Stage 1: Build stage with Maven
FROM maven:3.8.4-openjdk-8 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package -DskipTests

# Stage 2: Production-ready stage with Alpine OpenJDK
FROM openjdk:8-jdk-alpine

# Create a non-root user
RUN addgroup -S spring && adduser -S spring -G spring

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage to the image
COPY --from=build /app/target/*.jar app.jar

# Copy the application properties file from the build context to the image
COPY src/main/resources/application.properties /config/application.properties

# Change ownership of the application JAR and config directory
RUN chown -R spring:spring /app/app.jar /config

# Switch to the non-root user
USER spring:spring

# Define the command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]

