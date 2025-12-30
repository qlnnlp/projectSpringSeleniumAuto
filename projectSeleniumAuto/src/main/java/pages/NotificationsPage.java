package pages;

import annotations.Path;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Path("/notifications")
public class NotificationsPage extends AbsBasePage<NotificationsPage>{

    public NotificationsPage(WebDriver driver) {
        super(driver);
    }

    // Selectors and methods

    @FindBy(id = "notify-me")
    WebElement notifyB;

    JavascriptExecutor js = (JavascriptExecutor) driver;

    String script = String.join(
            "\n",
            "const callback = arguments[arguments.length - 1];",
            "const OldNotify = window.Notification;",
            "function newNotification(title, options) {",
            " callback(title);",
            " return new OldNotify(title, options);",
            "}",
            "newNotification.requestPermission = " +
                    "OldNotify.requestPermission.bind(OldNotify);",
            "Object.defineProperty(newNotification, 'permission', {",
            " get: function() {",
            " return OldNotify.permission;",
            " }",
            "});",
            "window.Notification = newNotification;",
            "document.getElementById('notify-me').click();"
            );

    public void clickNotifyB() {
       click(notifyB);
    }

    public String getNotificationTitleAfterClick() {
        return (String) js.executeAsyncScript(script);
    }
}
