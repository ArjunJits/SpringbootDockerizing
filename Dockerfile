# Step 1: Use an official Java runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the jar file from your target folder to the container
# Replace "myapp-0.0.1-SNAPSHOT.jar" with your actual file name
COPY target/myapp-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Step 5: Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]