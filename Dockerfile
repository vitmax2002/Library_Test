FROM openjdk:latest

ADD target/library_test.jar library_test.jar

ENTRYPOINT ["java","-jar","/library_test.jar"]