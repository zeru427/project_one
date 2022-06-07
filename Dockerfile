FROM openjdk:8
EXPOSE 8080
ADD target/revatureApp.jar demo.jar
ENTRYPOINT [ "java", "-jar" , "/demo.jar"]