# ========= ETAPA 1 =========
# Compilar el proyecto

FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests


# ========= ETAPA 2 =========
# Ejecutar la aplicación

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]