import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ConsoleLogsPage;

@ExtendWith(UIExtensions.class)
public class ConsoleLogsPageTest {


    @Inject
    ConsoleLogsPage consoleLogsPage;

    @Test
    void setConsoleLogsTest() {
        consoleLogsPage.open()
                .gatherBrowserLogs();
    }
}
