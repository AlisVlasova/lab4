FROM openjdk:17-jdk-alpine
COPY build/libs/demo-0.0.1-SNAPSHOT.jar.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]