FROM openjdk:8
ADD build/libs/spring-boot-app.jar spring-boot-app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "spring-boot-app.jar"]