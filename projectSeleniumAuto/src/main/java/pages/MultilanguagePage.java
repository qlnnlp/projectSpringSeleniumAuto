package pages;


import annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

@Path("/multilanguage")
public class MultilanguagePage extends AbsBasePage<MultilanguagePage> {

    public MultilanguagePage(WebDriver driver) {
        super(driver);
    }

    // Selectors and methods

    @FindBy(tagName = "body")
    WebElement body;


     List<String> getBundleEStoList() {
        ResourceBundle strings = ResourceBundle.getBundle
                ("strings", Locale.forLanguageTag("es-ES"));
        List<String> listES = new ArrayList<>();
        Enumeration<String> keys = strings.getKeys();


        while (keys.hasMoreElements()) {
            var key = keys.nextElement();
            var value = strings.getString(key);
            listES.add(value);
        }
        return listES;
    }


     String getBodyText() {
        waiters.waitForCondition(
                ExpectedConditions.visibilityOf(body));
        return body.getText();
    }

    public boolean isBundleInBodyText() {
         var bodyText = getBodyText();
         var bundleList = getBundleEStoList();
         for (String str : bundleList) {
             if (!bodyText.contains(str)) {
                 log.error("{} is not in Body", str);
                 return false;
             }
         }
         return true;
    }
}
