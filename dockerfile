# ---------- FASE DE BUILD ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos primero el pom para cachear dependencias
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# Copiamos el resto del código
COPY src ./src

# Compilamos el proyecto
RUN mvn -q -DskipTests package

# ---------- FASE DE RUNTIME ----------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiamos el jar generado
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto de tu aplicación
EXPOSE 8081

# Ejecutamos la aplicación
ENTRYPOINT ["java","-jar","/app/app.jar"]
