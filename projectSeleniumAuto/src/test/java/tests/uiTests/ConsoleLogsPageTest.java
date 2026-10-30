package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.ConsoleLogsPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Логирование консоли браузера")
public class ConsoleLogsPageTest extends BaseUiTest {

    @Test
    @DisplayName("Проверка наличия логов в консоли браузера")
    void shouldCaptureConsoleLogs() {
        ConsoleLogsPage page = new ConsoleLogsPage().open(appProperties.ui().baseUrl());
        List<LogEntry> logs = page.getBrowserLogs();

        assertThat(logs)
                .as("Страница console-logs генерирует сообщения в консоль")
                .isNotEmpty();
    }
}