package tests.uiTests;

import tests.baseTests.BaseUiTest;
import ui.pages.MainPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Главная страница (Хаб навигации)")
public class MainPageTest extends BaseUiTest {

    @Test
    @Story("Отображение ключевых элементов страницы")
    @DisplayName("Проверка структуры главной страницы: шапка, контент, ссылки и футер")
    void shouldDisplayAllMainPageElementsCorrectly() {
        MainPage mainPage = new MainPage().open(appProperties.ui().baseUrl());

        assertThat(mainPage.getTitle())
                .isEqualTo("Hands-On Selenium WebDriver with Java");

        assertThat(mainPage.getHeader().getTitleText())
                .isEqualToIgnoringCase("Practice site");

        assertThat(mainPage.getHeader().isLogoDisplayed())
                .as("Логотип должен отображаться")
                .isTrue();

        assertThat(mainPage.getHeader().getLogoSrc())
                .contains("img/hands-on-icon.png");

        assertThat(mainPage.getHeader().getLogoHref())
                .isEqualTo("https://github.com/bonigarcia/selenium-webdriver-java");

        assertThat(mainPage.getLeadDescription())
                .contains("collection of sample web pages to be tested with Selenium WebDriver");

        assertThat(mainPage.getBookUrl())
                .isEqualTo("https://www.oreilly.com/library/view/hands-on-selenium-webdriver/9781098109998/");

        assertThat(mainPage.getGitHubUrl())
                .isEqualTo("https://github.com/bonigarcia/selenium-webdriver-java");

        assertThat(mainPage.getChaptersCount())
                .as("На главной странице должно быть 6 карточек с главами")
                .isEqualTo(6);

        assertThat(mainPage.getFooter().getCopyrightText())
                .contains("Boni García");

        assertThat(mainPage.getFooter().getAuthorLink())
                .isEqualTo("https://bonigarcia.dev/");
    }
}