package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.LoginFormPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Аутентификация пользователей")
public class LoginFormPageTest extends BaseUiTest {

    @Test
    @DisplayName("Успешная авторизация с валидными данными")
    @Description("Проверка успешного логина пользователя с отображением success alert")
    void shouldLoginSuccessfullyWithValidCredentials() {
        LoginFormPage loginPage = new LoginFormPage()
                .open(appProperties.ui().baseUrl())
                .loginAs("user", "user");

        assertThat(loginPage.getAlert().getSuccessMessage())
                .as("Текст сообщения об успешной авторизации")
                .isEqualTo("Login successful");
    }

    @Test
    @DisplayName("Ошибка авторизации при неверных учетных данных")
    @Description("Проверка отклонения входа и появления сообщения об ошибке")
    void shouldShowErrorWithInvalidCredentials() {
        LoginFormPage loginPage = new LoginFormPage()
                .open(appProperties.ui().baseUrl())
                .loginAs("bad-user", "invalid-pass");

        assertThat(loginPage.getAlert().getDangerMessage())
                .as("Текст сообщения о неверных учетных данных")
                .isEqualTo("Invalid credentials");
    }
}