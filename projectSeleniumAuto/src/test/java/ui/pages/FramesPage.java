package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FramesPage extends BasePage<FramesPage> {

    private final By frameLocator = By.name("frame-body");
    private final By paragraphs = By.tagName("p");

    @Override
    protected String getPagePath() {
        return "/frames";
    }

    @Step("Переключиться во фрейм и получить тексты параграфов")
    public List<String> getParagraphsFromFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
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