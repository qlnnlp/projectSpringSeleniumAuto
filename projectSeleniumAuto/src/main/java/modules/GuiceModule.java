package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import factory.BrowserType;
import factory.WebDriverFactory;

import options.ChromeOptionsConfigurer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pages.*;


public class GuiceModule extends AbstractModule {

    private final WebDriver driver;
private final Logger log = LogManager.getLogger(GuiceModule.class);


    public GuiceModule() {

        // TODO: сделать гибким
        ChromeOptionsConfigurer cfg = new ChromeOptionsConfigurer
                (true, true, true, true, true);

        try {
            driver = new WebDriverFactory()
                    .create
                            ((BrowserType.valueOf
                                    (System.getProperty("browser.name", "CHROME"))),
                                    cfg.getOptions());
        } catch (Exception e) {
            log.error("Failed to initialize WebDriver for browser {}"
                    , System.getProperty("browser.name", "CHROME"));
            throw new RuntimeException(e);
        }
    }

    @Singleton
    @Provides
    public WebDriver getDriver() {
        return this.driver;
    }

    @Provides
    @Singleton
    public MainPage getMainPage() {
        return new MainPage(driver);
    }

    @Provides
    @Singleton
    public WebFormPage getWebFormPage() {
        return new WebFormPage(driver);
    }

    @Provides
    @Singleton
    public NavigationPage getNavigationPage() { return new NavigationPage(driver); }

    @Provides
    @Singleton
    public DropdownMenuPage getDropdownMenuPage() { return new DropdownMenuPage(driver); }

    @Provides
    @Singleton
    public MouseOverPage getMouseOverPage() { return new MouseOverPage(driver); }

    @Provides
    @Singleton
    public DragAndDropPage getDragAndDropPage() { return new DragAndDropPage(driver); }

    @Provides
    @Singleton
    public DrawInCanvasPage getDrawInCanvasPage() { return new DrawInCanvasPage(driver); }

    @Provides
    @Singleton
    public LoadingImagesPage getLoadingImagesPage() { return new LoadingImagesPage(driver); }

    @Provides
    @Singleton
    public SlowCalculatorPage getSlowCalculatorPage() { return new SlowCalculatorPage(driver); }

    @Provides
    @Singleton
    public LongPage getLongPage() { return new LongPage(driver); }

    @Provides
    @Singleton
    public InfiniteScrollPage getInfiniteScrollPage() { return  new InfiniteScrollPage(driver); }

    @Provides
    @Singleton
    public ShadowDOMPage getShadowDOMPage() { return  new ShadowDOMPage(driver); }

    @Provides
    @Singleton
    public CookiesPage getCookiesPage() { return new CookiesPage(driver); }

    @Provides
    @Singleton
    public FramesPage getFramesPage() { return new FramesPage(driver); }

    @Provides
    @Singleton
    public IFramePage getIFramePage() { return new IFramePage(driver); }

    @Provides
    @Singleton
    public DialogBoxesPage getDialogBoxesPage() { return new DialogBoxesPage(driver); }

    @Provides
    @Singleton
    public WebStoragePage getWebStoragePage() { return new WebStoragePage(driver); }

    @Provides
    @Singleton
    public GeolocationPage getGeolocationPage() { return new GeolocationPage(driver); }

    @Provides
    @Singleton
    public NotificationsPage getNotificationsPage() { return new NotificationsPage(driver); }

    @Provides
    @Singleton
    public GetUserMediaPage getGetUserMediaPage() { return new GetUserMediaPage(driver); }

    @Provides
    @Singleton
    public MultilanguagePage getMultilanguagePage() { return new MultilanguagePage(driver); }

    @Provides
    @Singleton
    public ConsoleLogsPage getConsoleLogsPage() { return new ConsoleLogsPage(driver); }

    @Provides
    @Singleton
    public LoginFormPage getLoginFormPage() { return  new LoginFormPage(driver); }

    @Provides
    @Singleton
    public SlowLoginFormPage getSlowLoginFormPage() { return new SlowLoginFormPage(driver); }

    @Provides
    @Singleton
    public RandomCalculatorPage getRandomCalculatorPage() { return new RandomCalculatorPage(driver); }

    @Provides
    @Singleton
    public DownloadFilesPage getDownloadFilesPage() { return new DownloadFilesPage(driver); }

    @Provides
    @Singleton
    public DataTypesPage getDataTypesPage() { return  new DataTypesPage(driver); }
}


