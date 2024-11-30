FROM maven:3.8.5-openjdk-17

WORKDIR /movies

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean
RUN mvn package -DskipTests

FROM openjdk:17-jdk

COPY /target/movie-microservice*.jar /movies/launch-movies.jar

ENTRYPOINT ["java","-jar","/movies/launch-movies.jar"]

EXPOSE 3332