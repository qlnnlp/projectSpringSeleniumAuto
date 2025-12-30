import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CookiesPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class CookiesPageTest {

    @Inject
    CookiesPage cookiesPage;

    @Test
    void cookiesTest() {
        cookiesPage.open();

        var cookies = cookiesPage.getCookies();
        var userNameName = cookiesPage.getCookieName("username");
        var usernameValue = cookiesPage.getCookieValue("username");
        var usernamePath = cookiesPage.getCookiePath("username");
        var dateValue = cookiesPage.getCookieValue("date");
        var datePath = cookiesPage.getCookiePath("date");

        assertThat(cookies)
                .hasSize(2);
        assertThat(usernameValue)
                .isEqualTo("John Doe");
        assertThat(usernamePath)
                .isEqualTo("/");
        assertThat(dateValue)
                .isEqualTo("10/07/2018");
        assertThat(datePath)
                .isEqualTo("/");

        cookiesPage.addNewCookie("new-cookie-key", "new-cookie-value");
        var newCookiesAmount = cookiesPage.getCookies();

        assertThat(newCookiesAmount)
                .hasSize(3);

        var newCookieValue = cookiesPage.getCookieValue("new-cookie-key");

        assertThat(newCookieValue)
                .isEqualTo("new-cookie-value");


        cookiesPage.addNewCookie(userNameName, "new-value");
        var editedCookieName = cookiesPage.getCookieName("username");
        var newValue = cookiesPage.getCookieValue(editedCookieName);


        assertThat(newValue)
                .isNotEqualTo(usernameValue);
        assertThat(newValue)
                .isEqualTo("new-value");


        cookiesPage.deleteCookie("username");

        var amountAfterDeleting = cookiesPage.getCookies();
        assertThat(amountAfterDeleting)
                .hasSize(2);

    }
}
