FROM eclipse-temurin:25-jre-noble
WORKDIR /app
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/* \
    && curl -L -o app.jar https://github.com
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
