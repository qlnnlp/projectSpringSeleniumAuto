package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;

public class MouseOverPage extends BasePage<MouseOverPage> {

    public enum ImageCard {
        COMPASS("compass"),
        CALENDAR("calendar"),
        AWARD("award"),
        LANDSCAPE("landscape");

        private final String imageName;

        ImageCard(String imageName) {
            this.imageName = imageName;
        }

        public String getImageName() {
            return imageName;
        }
    }

    @Override
    protected String getPagePath() {
        return "/mouse-over";
    }

    @Step("Навести курсор мыши на изображение {card}")
    public MouseOverPage hoverOver(ImageCard card) {
        By imgLocator = By.xpath("//img[contains(@src, '%s.png')]".formatted(card.getImageName()));
        WebElement element = find(imgLocator);
        new Actions(driver).moveToElement(element).perform();
        return this;
    }

    @Step("Получить текст подписи под изображением {card}")
    public String getCaptionText(ImageCard card) {
        By imgLocator = By.xpath("//img[contains(@src, '%s.png')]".formatted(card.getImageName()));
        return driver.findElement(RelativeLocator.with(By.tagName("div")).near(imgLocator)).getText().trim();
    }
}