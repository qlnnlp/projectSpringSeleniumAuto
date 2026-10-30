package ui.pages;

import ui.components.FooterComponent;
import ui.components.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MainPage extends BasePage<MainPage> {

    private final HeaderComponent header = new HeaderComponent();
    private final FooterComponent footer = new FooterComponent();

    private final By leadText = By.className("lead");
    private final By bookLink = By.cssSelector("div > p > a:nth-child(2)");
    private final By gitHubLink = By.cssSelector("div > p > a:nth-child(3)");

    private final By cardBodies = By.className("card-body");
    private final By webFormLink = By.linkText("Web form");
    private final By navigationLink = By.linkText("Navigation");
    private final By dropdownLink = By.linkText("Dropdown menu");
    private final By dragAndDropLink = By.linkText("Drag and drop");
    private final By loginFormLink = By.linkText("Login form");

    @Override
    protected String getPagePath() {
        return "/";
    }

    public HeaderComponent getHeader() {
        return header;
    }

    public FooterComponent getFooter() {
        return footer;
    }

    @Step("Получить текст лид-блока")
    public String getLeadDescription() {
        return getText(leadText);
    }

    @Step("Получить ссылку на книгу O'Reilly")
    public String getBookUrl() {
        return find(bookLink).getDomAttribute("href");
    }

    @Step("Получить ссылку на GitHub репозиторий")
    public String getGitHubUrl() {
        return find(gitHubLink).getDomAttribute("href");
    }

    @Step("Получить количество карточек глав на странице")
    public int getChaptersCount() {
        return driver.findElements(cardBodies).size();
    }

    @Step("Получить заголовок страницы (title)")
    public String getTitle() {
        return driver.getTitle();
    }

    @Step("Перейти к веб-форме")
    public WebFormPage navigateToWebForm() {
        click(webFormLink);
        return new WebFormPage();
    }

    @Step("Перейти к форме входа")
    public LoginFormPage navigateToLoginForm() {
        click(loginFormLink);
        return new LoginFormPage();
    }
}