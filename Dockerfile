# Use Eclipse Temurin JDK for running the application
FROM eclipse-temurin:21
LABEL authors="Thompson"

WORKDIR /app

# Copy the pre-built JAR file into the container
COPY build/libs/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
