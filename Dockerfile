# Base Image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy dependencies and the application jar
COPY target/iot-management-api-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
