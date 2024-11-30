FROM maven:3.8.5-openjdk-17

WORKDIR /nlp

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean
RUN mvn package -DskipTests

FROM openjdk:17-jdk

COPY /target/nlp-microservice*.jar /nlp/launch-nlp.jar

ENTRYPOINT ["java","-jar","/nlp/launch-nlp.jar"]

EXPOSE 3332