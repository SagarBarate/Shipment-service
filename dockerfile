# Use an OpenJDK runtime as a base image; adjust version as needed
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the deployable artifact (e.g. JAR) from the target directory into the container
COPY target/shipment-service-1.0.0.jar app.jar

# Expose the port the application listens on (adjust if different)
EXPOSE 8090

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
