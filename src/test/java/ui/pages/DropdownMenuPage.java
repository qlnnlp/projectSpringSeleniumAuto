package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DropdownMenuPage extends BasePage<DropdownMenuPage> {

    private final By leftClickDropdownBtn = By.id("my-dropdown-1");
    private final By rightClickDropdownBtn = By.id("my-dropdown-2");
    private final By doubleClickDropdownBtn = By.id("my-dropdown-3");

    private final Actions actions;

    public DropdownMenuPage() {
        super();
        this.actions = new Actions(driver);
    }

    @Override
    protected String getPagePath() {
        return "/dropdown-menu";
    }

    @Step("Выбрать пункт '{itemText}' из левого выпадающего меню (Left Click)")
    public DropdownMenuPage selectFromLeftClickDropdown(String itemText) {
        click(leftClickDropdownBtn);
        By itemLocator = By.xpath("//div[contains(@class, 'dropdown')][button[@id='my-dropdown-1']]//a[contains(@class, 'dropdown-item') and normalize-space()='%s']".formatted(itemText));
        find(itemLocator).click();
        return this;
    }

    @Step("Выбрать пункт '{itemText}' из контекстного меню (Right Click)")
    public DropdownMenuPage selectFromContextMenu(String itemText) {
        WebElement btn = find(rightClickDropdownBtn);
        actions.contextClick(btn).perform();

        By itemLocator = By.xpath("//ul[@id='context-menu-2']//a[contains(@class, 'dropdown-item') and normalize-space()='%s']".formatted(itemText));
        find(itemLocator).click();
        return this;
    }

    @Step("Выбрать пункт '{itemText}' из меню по двойному клику (Double Click)")
    public DropdownMenuPage selectFromDoubleClickMenu(String itemText) {
        WebElement btn = find(doubleClickDropdownBtn);
        actions.doubleClick(btn).perform();

        By itemLocator = By.xpath("//ul[@id='context-menu-3']//a[contains(@class, 'dropdown-item') and normalize-space()='%s']".formatted(itemText));
        find(itemLocator).click();
        return this;
    }

    public boolean areAllDropdownButtonsVisible() {
        return isDisplayed(leftClickDropdownBtn)
                && isDisplayed(rightClickDropdownBtn)
                && isDisplayed(doubleClickDropdownBtn);
    }
}