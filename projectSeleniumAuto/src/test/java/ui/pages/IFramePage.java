package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class IFramePage extends BasePage<IFramePage> {

    private final By iframeLocator = By.cssSelector("#my-iframe, iframe");
    private final By paragraphs = By.tagName("p");

    @Override
    protected String getPagePath() {
        return "/iframes";
    }

    @Step("Переключиться в IFrame и получить тексты параграфов")
    public List<String> getParagraphsFromIFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLocator));
        try {
            return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(paragraphs, 0))
                    .stream()
                    .map(WebElement::getText)
                    .toList();
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}