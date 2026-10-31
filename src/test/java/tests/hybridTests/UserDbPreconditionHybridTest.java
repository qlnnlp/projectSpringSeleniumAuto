package tests.hybridTests;

import db.models.UserDbEntity;
import db.repository.UserDbRepository;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.ui.UserDataForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tests.baseTests.BaseHybridTest;
import ui.pages.DataTypesPage;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Гибридное тестирование (DB + UI)")
@Feature("Интеграция базы данных и веб-интерфейса")
@Tag("hybrid")
@Tag("db")
public class UserDbPreconditionHybridTest extends BaseHybridTest {

    @Autowired
    private UserDbRepository userDbRepository;

    private final Long testUserId = 777L;

    @AfterEach
    void tearDown() {
        // Гарантированная очистка тестовых данных после прогона
        userDbRepository.deleteById(testUserId);
    }

    @Test
    @Story("Подготовка состояния пользователя в БД с последующим заполнением UI-формы")
    @DisplayName("DB -> UI: Загрузка профиля из БД и валидация данных в веб-форме")
    void shouldPreconditionUserInDatabaseAndSubmitViaUi() {
        // Шаг 1: Подготовка изолированного состояния в базе данных
        String uniqueEmail = "lead_architect_%d@testlab.io".formatted(System.currentTimeMillis());
        UserDbEntity seededUser = UserDbEntity.of(
                testUserId,
                uniqueEmail,
                "Alexander",
                "Miagkov"
        );
        userDbRepository.save(seededUser);

        // Шаг 2: Извлечение данных из БД для передачи в UI-модель
        Optional<UserDbEntity> dbUserOpt = userDbRepository.findById(testUserId);
        assertThat(dbUserOpt)
                .as("Предусловие: пользователь обязан присутствовать в БД перед стартом UI сценария")
                .isPresent();

        UserDbEntity dbUser = dbUserOpt.get();

        UserDataForm formFromDb = new UserDataForm(
                dbUser.firstName(),
                dbUser.lastName(),
                faker.address().streetAddress(),
                faker.address().zipCode(),
                faker.address().city(),
                faker.country().name(),
                dbUser.email(),
                faker.phoneNumber().phoneNumber(),
                "QA Automation Architect",
                "Engineering Excellence Corp"
        );

        // Шаг 3: Взаимодействие со страницей через Selenium WebDriver
        DataTypesPage page = new DataTypesPage()
                .open(appProperties.ui().baseUrl());

        page.fillForm(formFromDb)
                .submit();

        // Шаг 4: UI Верификация успешности отправки формы
        assertThat(page.getSuccessAlertsCount())
                .as("Все 10 полей формы, синхронизированные с данными из БД, должны успешно пройти валидацию")
                .isEqualTo(10);
    }
}