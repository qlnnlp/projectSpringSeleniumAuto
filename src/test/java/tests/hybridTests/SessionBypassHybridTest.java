package tests.hybridTests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.baseTests.BaseHybridTest;
import ui.pages.CookiesPage;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Гибридное тестирование (UI + API)")
@Feature("Аутентификация в обход UI")
@Tag("hybrid")
@Tag("smoke")
public class SessionBypassHybridTest extends BaseHybridTest {

    @Test
    @Story("Bypass UI Login via API Token")
    @DisplayName("API -> Cookie: Получение токена через Auth API и инъекция в сессию браузера")
    void shouldBypassLoginByInjectingAuthTokenCookie() {
        // Шаг 1: Быстрое получение токена сессии через API
        String authToken = userService.obtainAuthToken("eve.holt@reqres.in", "cityslicka");
        assertThat(authToken).isNotBlank();

        // Шаг 2: Открытие домена в браузере и инъекция Cookie
        CookiesPage page = new CookiesPage().open(appProperties.ui().baseUrl());

        injectCookie("session-token", authToken);
        injectCookie("user-identity", "qa-automation-lead");

        // Обновляем страницу для применения сессии
        getDriver().navigate().refresh();

        // Шаг 3: Верификация, что сессия успешно внедрена и доступна веб-приложению
        assertThat(page.getCookieValue("session-token"))
                .as("Сессионный токен из API должен присутствовать в cookie браузера")
                .isEqualTo(authToken);

        assertThat(page.getCookieValue("user-identity"))
                .isEqualTo("qa-automation-lead");
    }
}