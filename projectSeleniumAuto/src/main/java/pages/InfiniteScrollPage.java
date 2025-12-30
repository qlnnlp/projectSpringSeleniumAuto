package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;



import java.util.List;

@Path("/infinite-scroll")
public class InfiniteScrollPage extends AbsBasePage <InfiniteScrollPage> {

    public InfiniteScrollPage(WebDriver driver) {
        super(driver);
    }

    // Locators and methods

  By pLocator = By.tagName("p");
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String script = "arguments[0].scrollIntoView();";


    public void scrollParagraphs(int fullScrolls) throws RuntimeException {

        for (int i = 0; i < fullScrolls; i++) {
            List<WebElement> paragraphs = waiters
                    .getWaiter
                            (ExpectedConditions
                                    .numberOfElementsToBeMoreThan(pLocator, 0));

            if (paragraphs.isEmpty()) {
                log.error("paragraphs amount: {}", paragraphs.size());
                throw new RuntimeException("List is empty");
            }

            var initParagraphsNumber = paragraphs.size();
            var lastParagraph = find(By.xpath((String.format("//p[%d]", initParagraphsNumber))));

            js.executeScript(script, lastParagraph);
            waiters.getWaiter
                    (ExpectedConditions
                            .numberOfElementsToBeMoreThan(pLocator, initParagraphsNumber));
        }
    }


}

