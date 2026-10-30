package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

public class ConsoleLogsPage extends BasePage<ConsoleLogsPage> {

    @Override
    protected String getPagePath() {
        return "/console-logs";
    }

    @Step("Собрать логи консоли браузера")
    public List<LogEntry> getBrowserLogs() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        return logEntries.getAll();
    }
}