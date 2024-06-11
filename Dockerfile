# Use a base image that contains Java and Maven
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Download the dependencies
RUN mvn dependency:go-offline -B

# Copy the rest of the project files
COPY src ./src

# Package the application
RUN mvn package -DskipTests

# Use a lightweight base image for running the application
FROM openjdk:17-jdk-alpine

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]