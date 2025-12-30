import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.InfiniteScrollPage;

@ExtendWith(UIExtensions.class)
public class InfiniteScrollPageTest {

    @Inject
    InfiniteScrollPage infiniteScrollPage;

    @Test
    void infiniteScrollTest() {
        infiniteScrollPage.open()
                .scrollParagraphs(10);

    }
}
