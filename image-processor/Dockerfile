FROM maven:3.8.5-openjdk-17

WORKDIR /images

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean
RUN mvn package -DskipTests

FROM openjdk:17-jdk

COPY /target/image-processor*.jar /images/launch-images.jar

ENTRYPOINT ["java","-jar","/images/launch-images.jar"]

EXPOSE 3331