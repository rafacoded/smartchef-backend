# ---------- BUILD STAGE ----------
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiamos primero lo mínimo para aprovechar caché de dependencias
COPY pom.xml ./
COPY .mvn/ .mvn/
COPY mvnw ./

# En Linux, a veces el wrapper no viene con permiso de ejecución
RUN chmod +x mvnw

# Descarga dependencias (cache-friendly)
RUN ./mvnw -q -DskipTests dependency:go-offline

# Ahora sí, el código
COPY src/ src/

# Compila y genera el jar
RUN ./mvnw -q -DskipTests package


# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# (Opcional) Usuario no-root por seguridad
RUN useradd -m appuser
USER appuser

# Copiamos el jar resultante (Spring Boot suele generar target/*.jar)
COPY --from=build /app/target/*.jar /app/app.jar

# Render define PORT; exponemos 8080 por convención (Render realmente usa el env PORT)
EXPOSE 8080

# Arranque: respeta el puerto que Render asigne
# (Si tu app ya respeta PORT por config propia, igual no molesta)
ENTRYPOINT ["sh","-c","java -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
