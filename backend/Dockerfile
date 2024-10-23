FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/back-bio-spring-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_bio.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_bio.jar"]
