import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.DrawInCanvasPage;

@ExtendWith(UIExtensions.class)
public class DrawInCanvasTest {

    @Inject
    DrawInCanvasPage drawInCanvasPage;

    private final Logger log = LoggerFactory.getLogger(DrawInCanvasTest.class);

    @Test
    void canvasTest() {
        drawInCanvasPage.open()
                .drawACircle(drawInCanvasPage.canvas);
        drawInCanvasPage
                .drawALine(drawInCanvasPage.canvas);

        drawInCanvasPage
                .drawALine(drawInCanvasPage.canvas, 10,25,25);

        try {
            drawInCanvasPage
                    .drawALine(drawInCanvasPage.canvas, 10,100,100);
            Assertions.fail("Must catch the MoveTargetOutOfBoundsException");
        } catch (MoveTargetOutOfBoundsException e) {
            log.info("Negative test to catch the MoveTargetOutOfBoundsException - SUCCESS");
        }
    }
}
