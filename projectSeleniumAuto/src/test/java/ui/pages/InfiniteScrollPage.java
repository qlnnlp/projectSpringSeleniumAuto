package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InfiniteScrollPage extends BasePage<InfiniteScrollPage> {

    private final By paragraphLocator = By.tagName("p");

    @Override
    protected String getPagePath() {
        return "/infinite-scroll";
    }

    @Step("Получить текущее количество подгруженных параграфов")
    public int getParagraphsCount() {
        return driver.findElements(paragraphLocator).size();
    }

    @Step("Выполнить скролл {scrollIterations} раз для подгрузки контента")
    public InfiniteScrollPage scrollDown(int scrollIterations) {
        for (int i = 0; i < scrollIterations; i++) {
            List<WebElement> paragraphs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(paragraphLocator, 0));
            int countBeforeScroll = paragraphs.size();

            WebElement lastParagraph = paragraphs.get(countBeforeScroll - 1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lastParagraph);

            // Ожидаем появление новых элементов в DOM после скролла
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(paragraphLocator, countBeforeScroll));
        }
        return this;
    }
}