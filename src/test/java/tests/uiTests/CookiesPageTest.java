package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.CookiesPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Управление Cookie")
public class CookiesPageTest extends BaseUiTest {

    @Test
    @DisplayName("Добавление, чтение и удаление Cookies")
    void shouldManageCookiesCorrectly() {
        CookiesPage page = new CookiesPage().open(appProperties.ui().baseUrl());

        assertThat(page.getAllCookies()).hasSize(2);
        assertThat(page.getCookieValue("username")).isEqualTo("John Doe");

        page.addCookie("auth-token", "secret-token-123");
        assertThat(page.getAllCookies()).hasSize(3);
        assertThat(page.getCookieValue("auth-token")).isEqualTo("secret-token-123");

        page.deleteCookie("username");
        assertThat(page.getAllCookies()).hasSize(2);
        assertThat(page.getCookie("username")).isEmpty();
    }
}