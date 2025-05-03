# Usa una imagen oficial de Java 17
FROM eclipse-temurin:17-jdk

# Crea el directorio de trabajo dentro del contenedor
WORKDIR /app

CMD echo "SPRING_DATASOURCE_URL is $DB_USER"

# Copia el archivo .jar ya construido al contenedor
COPY boot/deploy/com-mypersonalbook-economy-boot-0.0.1-SNAPSHOT.jar app.jar

# Expón el puerto en el que correrá la app (ajústalo si usas otro)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]