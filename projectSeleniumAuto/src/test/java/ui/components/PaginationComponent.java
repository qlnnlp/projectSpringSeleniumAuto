package ui.components;

import core.driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaginationComponent {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By paginationContainer = By.className("pagination");

    public PaginationComponent() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Перейти на страницу пагинации номер {pageNumber}")
    public void clickPageNumber(int pageNumber) {
        By pageLocator = By.xpath("//ul[contains(@class,'pagination')]//a[contains(@class,'page-link') and normalize-space()='%d']".formatted(pageNumber));
        wait.until(ExpectedConditions.elementToBeClickable(pageLocator)).click();
    }

    @Step("Нажать кнопку 'Previous'")
    public void clickPrevious() {
        By prevLocator = By.xpath("//ul[contains(@class,'pagination')]//a[contains(@class,'page-link') and normalize-space()='Previous']");
        wait.until(ExpectedConditions.elementToBeClickable(prevLocator)).click();
    }

    @Step("Нажать кнопку 'Next'")
    public void clickNext() {
        By nextLocator = By.xpath("//ul[contains(@class,'pagination')]//a[contains(@class,'page-link') and normalize-space()='Next']");
        wait.until(ExpectedConditions.elementToBeClickable(nextLocator)).click();
    }

    public boolean isPageDisabled(String pageText) {
        By itemParentLocator = By.xpath("//ul[contains(@class,'pagination')]//li[contains(@class,'page-item') and .//a[normalize-space()='%s']]".formatted(pageText));
        WebElement item = driver.findElement(itemParentLocator);
        String classAttribute = item.getDomAttribute("class");
        return classAttribute != null && classAttribute.contains("disabled");
    }

    public String getPageHref(int pageIndex) {
        return driver.findElements(By.cssSelector("ul.pagination a.page-link"))
                .get(pageIndex)
                .getDomAttribute("href");
    }
}