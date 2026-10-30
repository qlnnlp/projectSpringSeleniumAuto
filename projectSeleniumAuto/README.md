# Hybrid UI & API Test Automation Framework

![Java 17](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen?logo=springboot)
![Selenium](https://img.shields.io/badge/Selenium-4.29.0-green?logo=selenium)
![REST Assured](https://img.shields.io/badge/REST%20Assured-5.5.1-blue)
![WireMock](https://img.shields.io/badge/WireMock-3.10.0-blueviolet)
![JUnit 5](https://img.shields.io/badge/JUnit-5.11.4-red?logo=junit5)
![Allure](https://img.shields.io/badge/Allure-2.29.0-yellow)

Промышленный гибридный фреймворк для автоматизации тестирования Web UI и REST API. Построен на стеке **Java 17 + Spring Boot 3**, ориентирован на надежность, высокую скорость выполнения и полную изоляцию тестовых сред.

---

## 🏛 Архитектурные решения и паттерны

* **Spring Test Context & ConfigurationProperties**:
    * Иерархическое управление окружением (`application.yml`, `application-real.yml`) через Java 17 `record AppProperties`.
    * Инверсия управления (IoC) для внедрения API-клиентов и сервисов через Spring `@Autowired`.
* **Потокобезопасный WebDriver Lifecycle**:
    * `DriverManager` на базе `ThreadLocal<WebDriver>` с обязательным `remove()` для предотвращения утечек памяти.
    * Создание драйвера через `DriverFactory` (поддержка Chrome, Firefox, Edge, Headless и Selenoid Grid) с использованием встроенного Selenium Manager.
    * Управление жизненным циклом браузера через JUnit 5 `UiTestExtension` (`beforeEach` / `afterEach`) со съемкой скриншотов в Allure при падениях.
* **Автономный API-слой и заглушки**:
    * Интеграция с **WireMock Standalone** по умолчанию: исключает падения по сетевым причинам и HTTP 429 (Too Many Requests).
    * Выделение низкоуровневых клиентов (`UserApiClient`, `AuthApiClient`) и сервисного слоя бизнес-степов (`UserService`).
    * Иммутабельные Java 17 `record` для моделей DTO с глобальным игнорированием неизвестных полей в Jackson.
* **Component Object Pattern (UI)**:
    * Разделение страниц на крупные Page Objects (`BasePage`) и независимые блоки интерфейса (`HeaderComponent`, `FooterComponent`, `ModalComponent`, `AlertComponent` на базе `BaseComponent`).
    * Полный отказ от `Thread.sleep` в пользу гибких явных ожиданий `WebDriverWait`.
* **Гибридные сценарии (Bypass Setup)**:
    * Быстрая подготовка и генерация тестовых сущностей через API с последующей валидацией в UI.
    * Авторизация в обход веб-форм через инъекцию токенов в cookies браузера (`SessionBypassHybridTest`).

---

## 📂 Структура проекта

```text
src/test/
├── java/
│   ├── api/
│   │   ├── clients/       # REST API клиенты (UserApiClient, AuthApiClient)
│   │   ├── services/      # Бизнес-сервисы (UserService)
│   │   └── specs/         # Фабрика спецификаций REST Assured
│   ├── config/            # Spring конфигурация и свойства (AppProperties, WireMockConfig)
│   ├── core/
│   │   ├── driver/        # Фабрика и потокобезопасный менеджер WebDriver
│   │   └── extensions/    # JUnit 5 расширения (скриншоты, Allure attachments)
│   ├── models/            # Java 17 Records (DTO моделей API и форм UI)
│   ├── tests/
│   │   ├── apiTests/      # Изолированные API тесты
│   │   ├── uiTests/       # UI тесты (Selenium)
│   │   ├── hybridTests/   # Сквозные гибридные тесты (UI + API)
│   │   └── baseTests/     # Базовые классы (BaseTest, BaseUiTest, BaseApiTest, BaseHybridTest)
│   └── ui/
│       ├── components/    # Переиспользуемые веб-компоненты (Header, Modal, Alert...)
│       └── pages/         # Page Objects
└── resources/             # Конфигурационные файлы YAML, стабы и локализация