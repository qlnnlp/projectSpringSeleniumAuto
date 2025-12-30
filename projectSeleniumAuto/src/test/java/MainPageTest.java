import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class MainPageTest {

    @Inject
    MainPage mainPage;

    @Test
    void testMainPageIsOpened() {
        mainPage
                .open()
                .pageTitleShouldBe("Hands-On Selenium WebDriver with Java");

        assertThat(mainPage.practiceSite.getText()
                .equalsIgnoreCase("Practice site"))
                .isTrue();

        assertThat(mainPage.imgFluid.isDisplayed())
                .as("Проверка видимости")
                .isTrue();

        assertThat(mainPage.imgFluid.getDomAttribute("src")
                        .equalsIgnoreCase("img/hands-on-icon.png"))
                .as("Проверка атрибута src")
                .isTrue();

        assertThat(mainPage.imgFluidLink.getDomAttribute("href"))
                .isEqualTo("https://github.com/bonigarcia/selenium-webdriver-java");


        assertThat(mainPage.leadInfoWithHTexts.getText())
                .isEqualToIgnoringCase(mainPage.leadText);

        assertThat(mainPage.leadBookLink.getDomAttribute("href"))
                .isEqualToIgnoringCase(mainPage.leadBookValue);

        assertThat(mainPage.leadGitHubLink.getDomAttribute("href"))
                .isEqualToIgnoringCase(mainPage.leadGitHubValue);

        assertThat(mainPage.upperRowCards.findElements(By.className("card-body")))
                .hasSize(3);

        assertThat(mainPage.lowerRowCards.findElements(By.className("card-body")))
                .hasSize(3);

        assertThat(mainPage.chapter3Card.getText())
                .isEqualToIgnoringCase(mainPage.chapter3CardText);

        assertThat(mainPage.chapter4Card.getText())
                .isEqualToIgnoringCase(mainPage.chapter4CardText);

        assertThat(mainPage.chapter5Card.getText())
                .isEqualToIgnoringCase(mainPage.chapter5CardText);

        assertThat(mainPage.chapter7Card.getText())
                .isEqualToIgnoringCase(mainPage.chapter7CardText);

        assertThat(mainPage.chapter8Card.getText())
                .isEqualToIgnoringCase(mainPage.chapter8CardText);

        assertThat(mainPage.chapter9Card.getText())
                .isEqualTo(mainPage.chapter9CardText);


        assertThat(mainPage.footer.getText())
                .isEqualTo(mainPage.footerCopyRight);

        assertThat(mainPage.footer.findElement(By.tagName("a"))
                .getDomAttribute("href"))
                .isEqualTo(mainPage.footerLink);

    }
}



