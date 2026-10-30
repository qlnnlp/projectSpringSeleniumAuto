package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.NotificationsPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Браузерные уведомления (Web Notifications)")
public class NotificationsPageTest extends BaseUiTest {

    @Test
    @DisplayName("Перехват и проверка заголовка Web Notification")
    void shouldTriggerAndInterceptWebNotification() {
        // Для executeAsyncScript требуется настройка таймаута скриптов, чтобы WebDriver не отвалился по дефолту
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(10));

        NotificationsPage page = new NotificationsPage().open(appProperties.ui().baseUrl());

        String notificationTitle = page.triggerAndGetNotificationTitle();

        assertThat(notificationTitle)
                .as("Заголовок перехваченного уведомления")
                .isEqualTo("This is a notification");
    }
}