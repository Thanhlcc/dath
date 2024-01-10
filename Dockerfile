FROM eclipse-temurin:17-jdk-jammy as base
WORKDIR /app
# Load the configuration for maven build tool
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# Install requrired dependencies
RUN ./mvnw dependency:resolve
COPY src ./src

