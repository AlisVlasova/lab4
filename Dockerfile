FROM openjdk:17-jdk-alpine
COPY build/libs/spring_docker.jar /app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000","-jar","/app.jar"]