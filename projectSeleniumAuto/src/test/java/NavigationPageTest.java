import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;
import pages.NavigationPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class NavigationPageTest {

    @Inject
    MainPage mainPage;

    @Inject
    NavigationPage navigationPage;

    @Test
    void openNavigationPageFromMain() {

        mainPage.open().click(mainPage.navigation);
        navigationPage.pageTitleShouldBe("Hands-On Selenium WebDriver with Java");
    }

    @Test
    void navigationExampleTest() {

        navigationPage.open();
        assertThat(navigationPage.navigationExampleRow.getText())
                .isEqualTo("Navigation example");

        assertThat(navigationPage.leadMainText.getText())
                .contains("Lorem ipsum");

        assertThat(navigationPage.backToIndexHref.getDomAttribute("href"))
                .isEqualTo("index.html");

        assertThat(navigationPage.getPageLink(1))
                .isEqualTo("navigation1.html");

        navigationPage.getPageItem("3").click();

        var clickStateCheck = navigationPage.
               clickOrReturnFalse(navigationPage.getPageItem("Next"));
        assertThat(clickStateCheck).isFalse();

        var clickPrevStateCheck = navigationPage.
                clickOrReturnFalse(navigationPage.getPageItem("Previous"));
        assertThat(clickPrevStateCheck).isTrue();

        navigationPage.backToIndexHref.click();
        assertThat(mainPage.upperRowCards.isDisplayed())
                .isTrue();

    }
}
