# Usa una imagen con Maven y Java 17
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia los archivos del proyecto
COPY . .

# Construye el JAR sin ejecutar tests
RUN mvn clean package -DskipTests

# Usa una imagen más ligera con Java para la ejecución
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia el JAR compilado desde la imagen anterior
COPY --from=build /app/boot/target/*.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]