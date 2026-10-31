# Этап 1: Кэширование зависимостей Maven
FROM maven:3.9.9-eclipse-temurin-17-alpine AS dependency-cache
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

# Этап 2: Финальный образ для запуска тестов
FROM maven:3.9.9-eclipse-temurin-17-alpine AS runner
WORKDIR /app

COPY --from=dependency-cache /root/.m2 /root/.m2
COPY pom.xml .
COPY src ./src

# Запускаем только фазу test без clean, чтобы не удалять точку монтирования volume
ENTRYPOINT ["mvn", "test"]