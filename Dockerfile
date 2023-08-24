FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/stocka-spring-0.0.1-SNAPSHOT.jar stocka-spring.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","stocka-spring.jar"]