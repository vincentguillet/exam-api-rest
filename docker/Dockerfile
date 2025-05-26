FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY ../pom.xml .

RUN mvn dependency:go-offline -B
COPY ../src ./src

RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/exam-api-rest-1.0-SNAPSHOT.jar app.jar

COPY docker/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "--", "java", "-jar", "app.jar"]
