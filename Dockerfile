# syntax=docker/dockerfile:1.7

# --- Builder stage: compile and package the Spring Boot application ---
FROM maven:3.9.8-eclipse-temurin-21 AS builder
WORKDIR /workspace

# Copy pom first for better dependency caching.
COPY pom.xml .
RUN mvn -B -ntp dependency:go-offline

# Copy source and build the runnable jar.
COPY src ./src
RUN mvn -B -ntp clean package -DskipTests

# --- Runtime stage: minimal JRE image and non-root execution ---
FROM eclipse-temurin:21-jre-jammy AS runtime

# Create a dedicated unprivileged user/group.
RUN groupadd --system --gid 10001 app && useradd --system --uid 10001 --gid 10001 app

WORKDIR /app
COPY --from=builder /workspace/target/track-day-legends-api-*.jar /app/app.jar
RUN chown -R app:app /app

USER app:app
EXPOSE 8080

# JVM sizing tuned for containerized environments.
ENTRYPOINT ["java","-XX:InitialRAMPercentage=50.0","-XX:MaxRAMPercentage=75.0","-jar","/app/app.jar"]
