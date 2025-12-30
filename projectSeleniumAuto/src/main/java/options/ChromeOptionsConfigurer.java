package options;

import interfaces.BrowserOptionsConfigurer;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class ChromeOptionsConfigurer implements BrowserOptionsConfigurer {

    private final boolean enableGeoLoc;
    private final boolean enableNotify;
    private final boolean enableUserMedia;
    private final boolean enableLangES;
    private final boolean enableLogGathering;

    public ChromeOptionsConfigurer(
            boolean enableGeoLoc, boolean enableNotify, boolean enableUserMedia, boolean enableLangES, boolean enableLogGathering) {
        this.enableGeoLoc = enableGeoLoc;
        this.enableNotify = enableNotify;
        this.enableUserMedia = enableUserMedia;
        this.enableLangES = enableLangES;
        this.enableLogGathering = enableLogGathering;
    }

    ChromeOptions options = new ChromeOptions();

    @Override
    public Object getOptions() {
        Map<String, Object> prefs = new HashMap<>();

        if (enableGeoLoc) {
            prefs.put("profile.default_content_setting_values.geolocation", 1);
        }
        if (enableNotify) {
            prefs.put("profile.default_content_setting_values.notifications", 1);
        }
        if (enableUserMedia) {
            options.addArguments("--use-fake-ui-for-media-stream");
            options.addArguments("--use-fake-device-for-media-stream");
        }
        if (enableLangES) {
            prefs.put("intl.accept_languages", "es-ES");
        }
        if (enableLogGathering) {
            LoggingPreferences logs = new LoggingPreferences();
            logs.enable(LogType.BROWSER, Level.ALL);
            options.setCapability("goog:loggingPrefs", logs);
        }
        if(!prefs.isEmpty()) {
            options.setExperimentalOption("prefs", prefs);
        }

        return options;
    }

}

