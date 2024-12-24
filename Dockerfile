FROM eclipse-temurin:17-jdk-jammy
VOLUME /tmp
COPY build/libs/*-SNAPSHOT.jar app.jar
ENV USE_POST 8080
ENV USE_PROFILE server
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${USE_PROFILE}", "-Dserver.port=${USE_POST}", "/app.jar"]