import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.LoginFormPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class LoginFormPageTest {

    @Inject
    LoginFormPage loginFormPage;

    @Test
    void loginFormSuccessTest() {
        loginFormPage
                .open()
                .successLogin();
        assertThat(loginFormPage.getAlertSuccessText())
                .isEqualTo("Login successful");
    }

    @Test
    void loginFormFailedTest() {
        loginFormPage
                .open()
                .failedLogin();
        assertThat(loginFormPage.getAlertInvalidText())
                .isEqualTo("Invalid credentials");
    }
}
