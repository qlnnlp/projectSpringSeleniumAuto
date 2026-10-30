package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class ShadowDOMPage extends BasePage<ShadowDOMPage> {

    private final By shadowHost = By.id("content");

    @Override
    protected String getPagePath() {
        return "/shadow-dom";
    }

    @Step("Получить текст параграфа из Shadow Root")
    public String getShadowParagraphText() {
        WebElement host = find(shadowHost);
        SearchContext shadowRoot = host.getShadowRoot();
        return shadowRoot.findElement(By.cssSelector("p")).getText().trim();
    }
}