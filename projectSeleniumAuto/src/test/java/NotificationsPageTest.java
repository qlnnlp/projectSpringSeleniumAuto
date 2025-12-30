import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.NotificationsPage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class NotificationsPageTest {

    @Inject
    NotificationsPage notificationsPage;

    @Test
    void setNotificationsTest() {
        notificationsPage.open()
                .clickNotifyB();

var title = notificationsPage.getNotificationTitleAfterClick();
        assertThat(title)
                .isEqualTo("This is a notification");

    }
}
