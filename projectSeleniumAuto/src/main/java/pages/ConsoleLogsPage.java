package pages;


import annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;

@Path("/console-logs")
public class ConsoleLogsPage extends AbsBasePage<ConsoleLogsPage>{
    public ConsoleLogsPage(WebDriver driver) {
        super(driver);
    }

    // Selectors and methods


public void gatherBrowserLogs() {
    LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
    if(!browserLogs.getAll().isEmpty()) {
        browserLogs.forEach(
                l -> log.error("{}", l)
        );
    }
}

}
