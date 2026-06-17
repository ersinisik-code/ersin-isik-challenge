FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Yıldız (*) işareti sayesinde target altındaki jar dosyası adı ne olursa olsun otomatik yakalanır:
COPY --from=builder /app/target/*-with-dependencies.jar ./app.jar

RUN mkdir ./output
ENTRYPOINT ["java", "-jar", "app.jar"]