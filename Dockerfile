# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="sarose301@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8888

# The application's jar file
ARG JAR_FILE=target/batchjob.jar

# Add the application's jar to the container
ADD ${JAR_FILE} batchjob.jar

ENTRYPOINT ["java", "-jar", "batchjob.jar"]
