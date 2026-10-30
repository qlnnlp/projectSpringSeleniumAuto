package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class LongPage extends BasePage<LongPage> {

    private final By firstParagraph = By.cssSelector("p:first-child");
    private final By lastParagraph = By.cssSelector("p:last-child");

    @Override
    protected String getPagePath() {
        return "/long-page";
    }

    @Step("Проскроллить страницу на {pixels} пикселей вниз")
    public LongPage scrollBy(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, arguments[0]);", pixels);
        return this;
    }

    @Step("Проскроллить к первому параграфу")
    public LongPage scrollToFirstParagraph() {
        WebElement element = find(firstParagraph);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'start'});", element);
        return this;
    }

    @Step("Проскроллить к последнему параграфу страницы")
    public LongPage scrollToLastParagraph() {
        WebElement element = find(lastParagraph);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", element);
        return this;
    }

    @Step("Получить текущее значение вертикального скролла (Y)")
    public long getScrollY() {
        Number scrollY = (Number) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset || document.documentElement.scrollTop;");
        return scrollY != null ? scrollY.longValue() : 0;
    }
}