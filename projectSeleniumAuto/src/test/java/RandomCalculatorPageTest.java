import extensions.UIExtensions;
import io.github.artsok.RepeatedIfExceptionsTest;
import io.github.bonigarcia.seljup.SingleSession;
import jakarta.inject.Inject;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.RandomCalculatorPage;
import static org.assertj.core.api.Assertions.assertThat;

@SingleSession
@ExtendWith({UIExtensions.class})
public class RandomCalculatorPageTest {


    @Inject
    RandomCalculatorPage randomCalculatorPage;


    @RepeatedIfExceptionsTest(repeats = 6)
    void RandomCalculatorTest() {
        randomCalculatorPage.open();

        randomCalculatorPage.performSimpleOperationInt
                (1, 2, randomCalculatorPage::pressPlus);
        assertThat(randomCalculatorPage.getScreenNumber(3))
            .isEqualTo(3);

    }
}
