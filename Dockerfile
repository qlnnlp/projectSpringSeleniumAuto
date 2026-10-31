# Этап 1: Кэширование зависимостей Maven
FROM maven:3.9.9-eclipse-temurin-17-alpine AS dependency-cache
WORKDIR /app

# Копируем только дескриптор сборки для прогрева локального репозитория .m2
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Этап 2: Финальный образ для запуска тестов
FROM maven:3.9.9-eclipse-temurin-17-alpine AS runner
WORKDIR /app

# Переносим скачанные зависимости из кэширующего слоя
COPY --from=dependency-cache /root/.m2 /root/.m2
COPY pom.xml .
COPY src ./src

# Точка входа по умолчанию: запуск тестов через Maven
ENTRYPOINT ["mvn", "clean", "test"]