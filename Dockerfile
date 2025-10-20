#builder container
FROM gradle:8.14.3-alpine AS builder

#Sets the working directory for all following commands.
WORKDIR /home/gradle/app

#Copies Gradle configuration files, wrapper scripts, and source code into the container.
COPY settings.gradle.kts build.gradle.kts ./
COPY src src
COPY gradle gradle

#Builds the Spring Boot fat JAR without running the Gradle daemon (reduces build overhead).
RUN gradle bootJar --no-daemon

#Creates a minimal Java runtime environment using Eclipse Temurin JDK 21
FROM eclipse-temurin:21-alpine

#Creates a non-root user (app) for better security.
RUN addgroup -g 1000 app
RUN adduser -G app -D -u 1000 -h /app app

#Switches to the non-root user and sets /app as the working directory.
USER app
WORKDIR /app

#Copies the built JAR file from the builder stage into the runtime container, ensuring proper file ownership.
COPY --from=builder --chown=1000:1000 /home/gradle/app/build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]