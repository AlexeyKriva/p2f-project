FROM maven:3.8.5-openjdk-17

WORKDIR /auth

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean
RUN mvn package -DskipTests

FROM openjdk:17-jdk

COPY /target/auth-microservice*.jar /auth/launch-auth.jar

ENTRYPOINT ["java","-jar","/auth/launch-auth.jar"]

EXPOSE 3334