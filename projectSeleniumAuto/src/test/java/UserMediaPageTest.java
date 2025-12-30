import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.GetUserMediaPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class UserMediaPageTest {


    @Inject
    GetUserMediaPage userMediaPage;

    @Test
    void userMediaTest() {
        userMediaPage.open()
                .clickStart();

        assertThat(userMediaPage.getFakeVideoDeviceName())
                .isEqualTo("fake_device_0");
    }
}
