FROM openjdk:17-jdk-slim
COPY target/*.jar AwsDemo.jar
ENTRYPOINT ["java","-jar","/AwsDemo.jar"]