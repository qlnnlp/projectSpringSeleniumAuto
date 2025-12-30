import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SlowLoginFormPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class SlowLoginFormPageTest {

    @Inject
    SlowLoginFormPage slowLoginFormPage;

    @Test
    void slowLoginFormSuccessTest() {
        slowLoginFormPage
                .open()
                .successLogin();
        assertThat(slowLoginFormPage.getAlertSuccessText())
                .isEqualTo("Login successful");
    }

    @Test
    void slowLoginFormFailedTest() {
        slowLoginFormPage
                .open()
                .failedLogin();
        assertThat(slowLoginFormPage.getAlertInvalidText())
                .isEqualTo("Invalid credentials");
    }

}
