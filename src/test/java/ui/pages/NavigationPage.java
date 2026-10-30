package ui.pages;

import ui.components.PaginationComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationPage extends BasePage<NavigationPage> {

    private final By titleHeader = By.className("display-6");
    private final By leadText = By.className("lead");
    private final By backToIndexLink = By.linkText("Back to index");

    private final PaginationComponent pagination = new PaginationComponent();

    @Override
    protected String getPagePath() {
        return "/navigation1";
    }

    public PaginationComponent getPagination() {
        return pagination;
    }

    public String getTitleHeaderText() {
        return getText(titleHeader);
    }

    public String getLeadText() {
        return getText(leadText);
    }

    public String getBackToIndexHref() {
        return find(backToIndexLink).getDomAttribute("href");
    }

    @Step("Вернуться на главную страницу через ссылку")
    public MainPage returnToMain() {
        click(backToIndexLink);
        return new MainPage();
    }
}