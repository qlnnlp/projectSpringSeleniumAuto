package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Path("/navigation1")
public class NavigationPage extends AbsBasePage<NavigationPage> {

    public NavigationPage(WebDriver driver){
        super(driver);
    }

    //Selectors and methods

    @FindBy(className = "display-6")
    public WebElement navigationExampleRow;

    @FindBy(className = "lead")
    public WebElement leadMainText;

    @FindBy(linkText = "Back to index")
    public WebElement backToIndexHref;

    @FindBy(className = "pagination")
    public WebElement paginationRow;

    public String getPageLink(int index) throws IllegalArgumentException {

        var rowListWithElements = paginationRow.findElements(By.className("page-link"));
        if(index >= rowListWithElements.size()) {
            log.error("index {} is out of bounds for the List: {}", index, rowListWithElements.size());
            throw new IllegalArgumentException("index is out of bounds for the List");
        }
        return rowListWithElements.get(index).getDomAttribute("href");
    }

    public WebElement getPageItem(String txt) {
        var rowListWithElements = paginationRow.findElements(By.className("page-link"));
        for (var e : rowListWithElements) {
            if(e.getText().equals(txt))
                return e;
        }
        log.error("txt {} not found in class page-link", txt);
        return null;
    }
}
