package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GetUserMediaPage extends BasePage<GetUserMediaPage> {

    private final By startBtn = By.id("start");
    private final By videoDeviceRow = By.id("video-device");

    @Override
    protected String getPagePath() {
        return "/get-user-media";
    }

    @Step("Запустить трансляцию медиа-потока")
    public GetUserMediaPage startMediaStream() {
        click(startBtn);
        return this;
    }

    public String getDeviceName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(videoDeviceRow)).getText().trim();
    }
}