package ui.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class FooterComponent extends BaseComponent {

    private final By footerContainer = By.cssSelector("body > footer");
    private final By footerLink = By.cssSelector("body > footer a");

    @Step("Получить текст копирайта футера")
    public String getCopyrightText() {
        return getText(footerContainer);
    }

    @Step("Получить ссылку автора из футера")
    public String getAuthorLink() {
        return find(footerLink).getDomAttribute("href");
    }
}