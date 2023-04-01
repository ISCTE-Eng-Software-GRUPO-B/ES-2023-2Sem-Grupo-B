# Base image
FROM openjdk:17-jdk-slim

# Copy Java application to container
COPY . /usr/src/app
WORKDIR /usr/src/app

# Run Spring application
CMD ["sh", "-c", "java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:4040 -jar target/CalendarApp-0.0.1-SNAPSHOT.jar"]
