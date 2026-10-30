package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.SlowLoginFormPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Аутентификация с задержкой ответа")
public class SlowLoginFormPageTest extends BaseUiTest {

    @Test
    @Story("Успешная медленная авторизация")
    @DisplayName("Проверка успешного логина при медленном ответе бэкенда")
    void shouldLoginSuccessfullyWithSlowResponse() {
        SlowLoginFormPage page = new SlowLoginFormPage()
                .open(appProperties.ui().baseUrl())
                .loginAs("user", "user");

        assertThat(page.getAlert().getSuccessMessage())
                .as("Сообщение об успешном входе должно появиться после завершения задержки")
                .isEqualTo("Login successful");
    }

    @Test
    @Story("Неуспешная медленная авторизация")
    @DisplayName("Проверка появления ошибки валидации при медленном ответе бэкенда")
    void shouldShowErrorWithInvalidCredentialsAfterDelay() {
        SlowLoginFormPage page = new SlowLoginFormPage()
                .open(appProperties.ui().baseUrl())
                .loginAs("bad-user", "invalid");

        assertThat(page.getAlert().getDangerMessage())
                .as("Сообщение об ошибке должно отобразиться после завершения задержки")
                .isEqualTo("Invalid credentials");
    }
}