import extensions.UIExtensions;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.DragAndDropPage;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtensions.class)
public class DragAndDropPageTest {


    @Inject
    DragAndDropPage dragAndDropPage;

    @Test
    void draggablePanelTest() {
        dragAndDropPage.open();

        var initLocation = dragAndDropPage.getLocation(dragAndDropPage.draggableCard);
        var card = dragAndDropPage.draggableCard;
        var target = dragAndDropPage.targetPanel;

        dragAndDropPage
                .dragAndDropBy(card, 100,0);

        assertThat(dragAndDropPage.getLocation(card))
                .isNotEqualTo(initLocation);

        dragAndDropPage
                .dragAndDropBy(card, 0,100);
        dragAndDropPage
                .dragAndDropBy(card, -100,0);
        dragAndDropPage
                .dragAndDropBy(card,0, -100);

        assertThat(initLocation)
                .isEqualTo(dragAndDropPage.getLocation(card));

        assertThat(card.getLocation())
                .isNotEqualTo(target.getLocation());

        dragAndDropPage
                .dragAndDropTo(card, target);

        assertThat(card.getLocation())
                .isEqualTo(target.getLocation());
    }
}
