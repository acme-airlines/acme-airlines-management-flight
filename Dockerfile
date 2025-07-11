# Stage 1: Compilar el JAR con Maven (Java 17 con OpenJDK)
FROM maven:3.8.5-openjdk-17-slim AS build

WORKDIR /app
COPY settings.xml /root/.m2/settings.xml

# Copiar pom.xml y descargar dependencias (caché)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests -B

# Usa una imagen base de JDK para ejecutar el JAR
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY --from=build /app/target/flight.management-0.0.1-SNAPSHOT.jar flight.management-ms.jar

EXPOSE 8002

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "flight.management-ms.jar"]