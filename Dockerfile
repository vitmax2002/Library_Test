FROM openjdk:latest

ADD target/Library_Test-0.0.1-SNAPSHOT.jar library_test.jar

ENTRYPOINT ["java","-jar","/library_test.jar"]