package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoadingImagesPage extends BasePage<LoadingImagesPage> {

    @Override
    protected String getPagePath() {
        return "/loading-images";
    }

    @Step("Получить src загруженного изображения по ID: {id}")
    public String getLoadedImageSrc(String id) {
        return find(By.id(id)).getDomAttribute("src");
    }
}