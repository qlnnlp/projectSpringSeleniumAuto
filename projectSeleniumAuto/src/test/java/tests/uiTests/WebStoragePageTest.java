package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.WebStoragePage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Веб-хранилища (Local & Session Storage)")
public class WebStoragePageTest extends BaseUiTest {

    @Test
    @DisplayName("Проверка размеров и добавления ключей в SessionStorage")
    void shouldInteractWithWebStorage() {
        WebStoragePage page = new WebStoragePage().open(appProperties.ui().baseUrl());

        assertThat(page.getLocalStorageSize()).isZero();
        assertThat(page.getSessionStorageSize()).isEqualTo(2);

        page.setSessionStorageItem("custom-session-key", "custom-value");

        assertThat(page.getSessionStorageSize()).isEqualTo(3);
        assertThat(page.getSessionStorageKeys()).contains("custom-session-key");
    }
}