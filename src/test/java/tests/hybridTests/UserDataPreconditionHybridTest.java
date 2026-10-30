package tests.hybridTests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.api.UserResponse;
import models.ui.UserDataForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.baseTests.BaseHybridTest;
import ui.pages.DataTypesPage;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Гибридное тестирование (UI + API)")
@Feature("Интеграция профилей пользователей")
@Tag("hybrid")
@Tag("regression")
public class UserDataPreconditionHybridTest extends BaseHybridTest {

    @Test
    @Story("Заполнение UI-формы данными, подготовленными через API")
    @DisplayName("API -> UI: Получение пользователя из API и успешная регистрация данных в веб-форме")
    void shouldFetchUserDataFromApiAndSubmitViaUi() {
        // Шаг 1: Подготовка данных через API-слой
        int targetUserId = 2;
        UserResponse.Data apiUser = userService.getUserData(targetUserId);

        assertThat(apiUser).as("Пользователь должен быть получен из API").isNotNull();

        UserDataForm formFromApi = new UserDataForm(
                apiUser.firstName(),
                apiUser.lastName(),
                faker.address().streetAddress(),
                faker.address().zipCode(),
                faker.address().city(),
                faker.country().name(),
                apiUser.email(),
                faker.phoneNumber().phoneNumber(),
                "Lead Automation Architect",
                "QA Engineering Lab"
        );

        // Шаг 2: Взаимодействие с UI с использованием подготовленных данных
        DataTypesPage page = new DataTypesPage()
                .open(appProperties.ui().baseUrl());

        page.fillForm(formFromApi)
                .submit();

        // Шаг 3: Верификация UI состояния
        assertThat(page.getSuccessAlertsCount())
                .as("Все поля формы, заполненные из API-модели, должны успешно пройти валидацию (10 зеленых алертов)")
                .isEqualTo(10);
    }
}