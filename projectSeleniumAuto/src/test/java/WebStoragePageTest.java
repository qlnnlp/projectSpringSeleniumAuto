import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.WebStoragePage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class WebStoragePageTest {



    @Inject
    WebStoragePage webStoragePage;

    @Test
    void WebStorageTest() {
        webStoragePage.open();

        var localSSize =  webStoragePage.getLocalStorageSize();
        assertThat(localSSize)
                .isEqualTo(0);

        var sessionStorageSize = webStoragePage.getSessionStorageSize();
        assertThat(sessionStorageSize)
                .isEqualTo(2);

        webStoragePage.addElementToStorageSession("new element", "value");
        var newSessionSize = webStoragePage.getSessionStorageSize();
        assertThat(newSessionSize)
                .isEqualTo(3);

        var storageHasNewElement = webStoragePage.getSessionStorageKeySet().contains("new element");
        assertThat(storageHasNewElement)
                .isTrue();
    }
}
