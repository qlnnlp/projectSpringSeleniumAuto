package pages;

import annotations.Path;
import commons.AbsCommon;
import exceptions.PathForThePageNotFounded;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public abstract class AbsBasePage<T> extends AbsCommon {

    private String baseUrl = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }


    public T pageTitleShouldBe(String expectedTitle) {
        String actualTitle = driver.getTitle(); // Получение текущего заголовка страницы
        assertThat(actualTitle)
                .as("Page title should be {}", expectedTitle)
                .isEqualTo(expectedTitle);
        return (T) this;
    }

    public String getPath() {

        Class<? extends AbsBasePage> clazz = this.getClass();

        if (clazz.isAnnotationPresent(Path.class)) {
            Path path = clazz.getDeclaredAnnotation(Path.class);
            return path.value().startsWith("/") ? path.value() : "/" + path.value();
        }
        throw new PathForThePageNotFounded();
    }

    public T open() {
        baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        driver.get(baseUrl + getPath());
        return (T) this;
    }

    public WebElement find(By locator) {
       waiters.waitForElmntPrsntByLocator(locator);
       return driver.findElement(locator);
    }

    public void click(WebElement element) {
        waiters.waitForCondition(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void type(WebElement element, String text) {
        waiters.waitForCondition(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public boolean isDisplayed(WebElement element) {
       return waiters.waitForCondition(ExpectedConditions.visibilityOf(element));
    }

    public boolean clickOrReturnFalse(WebElement element) {
        try {
            waiters.waitForCondition(ExpectedConditions.elementToBeClickable(element));
            element.click();
            return true;
        } catch (ElementClickInterceptedException e) {
            log.info("Element {} can't be clicked", element);
            return false;
        } catch (ElementNotInteractableException e) {
            log.info("Element {} is not interactable", element);
            return false;
        }
    }

    public void clickCheckbox(WebElement checkbox, boolean stateToCheck) {
        waiters.waitForCondition(ExpectedConditions.elementToBeClickable(checkbox));
        if (checkbox.isSelected() != stateToCheck) {
            checkbox.click();
            log.info("Checkbox {} set to {}", checkbox, stateToCheck);
        } else {
            log.info("Checkbox {} already in desired state {}", checkbox, stateToCheck);
        }
    }
    public void clickRadioButton(WebElement radioButton) {
        waiters.waitForCondition(ExpectedConditions.elementToBeClickable(radioButton));
        if (!radioButton.isSelected()) {
            radioButton.click();
            log.info("Radio button {} selected", radioButton);
        }
    }

    public void selectDropdownByText(WebElement dropdownElement, String visibleText) {
        waiters.waitForCondition(ExpectedConditions.elementToBeClickable(dropdownElement));
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(visibleText);
        log.info("Dropdown {} selected option with text '{}'", dropdownElement, visibleText);
    }

    public void waitForElementToDisappear(By locator, long timeoutSeconds, long pollingSeconds) {
        waiters.fluentWait( d -> {
            List<WebElement> elements = d.findElements(locator);
            return elements.isEmpty() || !elements.get(0).isDisplayed();
        }, timeoutSeconds, pollingSeconds);
        log.info("Waiting for element {} to disappear", locator);
    }

}
















