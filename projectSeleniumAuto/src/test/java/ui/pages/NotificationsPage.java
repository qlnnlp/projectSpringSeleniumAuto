package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class NotificationsPage extends BasePage<NotificationsPage> {

    private final By notifyBtn = By.id("notify-me");

    @Override
    protected String getPagePath() {
        return "/notifications";
    }

    @Step("Получить заголовок нативного браузерного уведомления (Web Notification)")
    public String triggerAndGetNotificationTitle() {
        // Ожидаем, что кнопка прогрузилась и доступна
        findClickable(notifyBtn);

        // Перехватываем вызов new window.Notification и возвращаем его title через callback
        String script = """
            const callback = arguments[arguments.length - 1];
            const OldNotify = window.Notification;
            function newNotification(title, options) {
                callback(title);
                return new OldNotify(title, options);
            }
            newNotification.requestPermission = OldNotify.requestPermission.bind(OldNotify);
            Object.defineProperty(newNotification, 'permission', {
                get: function() {
                    return OldNotify.permission;
                }
            });
            window.Notification = newNotification;
            document.getElementById('notify-me').click();
        """;

        return (String) ((JavascriptExecutor) driver).executeAsyncScript(script);
    }
}