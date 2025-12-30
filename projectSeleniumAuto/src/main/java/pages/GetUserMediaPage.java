package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


@Path("/get-user-media")
public class GetUserMediaPage extends AbsBasePage<GetUserMediaPage> {

    public GetUserMediaPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "start")
    WebElement startB;

    @FindBy(id = "my-video")
    WebElement videoWindow;

    @FindBy(id = "video-device")
    WebElement videoDeviceRow;

    public void clickStart() {
        click(startB);
    }

    public String getFakeVideoDeviceName() {
        waiters.waitForCondition(
                ExpectedConditions.textToBePresentInElement(
                        videoDeviceRow, "fake"));
        return videoDeviceRow.getText().substring(20);
    }
}

