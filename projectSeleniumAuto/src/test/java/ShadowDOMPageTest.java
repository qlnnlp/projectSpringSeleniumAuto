import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import pages.ShadowDOMPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class ShadowDOMPageTest {

    @Inject
    ShadowDOMPage shadowDOMPage;

    @Test
    void ShadowDOMTest() {
        shadowDOMPage.open();
        var textElement = shadowDOMPage.getElementFromSR
                (By.cssSelector("p")).getText();

        assertThat(textElement)
                .isEqualTo("Hello Shadow DOM");
    }
}
