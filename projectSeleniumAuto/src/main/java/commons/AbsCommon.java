package commons;

import commons.waiters.Waiters;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static java.lang.invoke.MethodHandles.lookup;

public abstract class AbsCommon {

     @Inject
    protected WebDriver driver;
     protected Waiters waiters;

    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        this.waiters = new Waiters(driver);
        PageFactory.initElements(driver,this);
    }

    static protected Logger log =  LogManager.getLogger(lookup().lookupClass());
}
