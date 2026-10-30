package ui.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HeaderComponent extends BaseComponent {

    private final By titleText = By.cssSelector("div.col.col-10 > h5");
    private final By logoImage = By.className("img-fluid");
    private final By logoLink = By.cssSelector("div.col.col-2 > a");

    @Step("Получить заголовок в шапке сайта")
    public String getTitleText() {
        return getText(titleText);
    }

    @Step("Проверить видимость логотипа")
    public boolean isLogoDisplayed() {
        return isDisplayed(logoImage);
    }

    @Step("Получить путь к логотипу (src)")
    public String getLogoSrc() {
        return find(logoImage).getDomAttribute("src");
    }

    @Step("Получить ссылку с логотипа (href)")
    public String getLogoHref() {
        return find(logoLink).getDomAttribute("href");
    }
}