FROM maven:3.8-jdk-8 as builder
COPY . /app
WORKDIR /app
RUN mvn -B package

FROM openjdk:8-slim
COPY --from=builder /app/target/apirest-0.0.1-SNAPSHOT.jar /
CMD ["java", "-jar", "apirest-0.0.1-SNAPSHOT.jar"]