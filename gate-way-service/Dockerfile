FROM openjdk:17-alpine
ADD target/gate*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]