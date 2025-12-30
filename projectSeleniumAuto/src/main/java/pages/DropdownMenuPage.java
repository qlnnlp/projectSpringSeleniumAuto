package pages;

import annotations.Path;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

@Path("/dropdown-menu")
public class DropdownMenuPage extends AbsBasePage<DropdownMenuPage> {

    public DropdownMenuPage(WebDriver driver) {
        super(driver);
    }

    //Selectors and methods

    @FindBy(id = "my-dropdown-1")
    public WebElement dropdownLCMenu;

    @FindBy(id = "my-dropdown-2")
    public WebElement dropdownRCMenu;

    @FindBy(id = "my-dropdown-3")
    public WebElement dropdownDoubleCMenu;
    Actions action = new Actions(driver);


   public void selectDrDownItem(String clickType, WebElement locator, String item) {
       try {
           switch (clickType.toLowerCase()) {

               case "left":
                   click(locator);
                   break;

               case "right":
                   var menu2 = driver.findElement(By.id("context-menu-2"));
                   log.info("Перед кликом меню: {}", menu2.getDomAttribute("style"));
                   action.contextClick(locator).build().perform();
                   log.info("После клика меню: {}", menu2.getDomAttribute("style"));
                   break;

               case "double":
                   var menu3 = driver.findElement(By.id("context-menu-3"));
                   log.info("Перед кликом меню: {}", menu3.getDomAttribute("style"));
                   action.doubleClick(locator).build().perform();
                   log.info("После клика меню: {}", menu3.getDomAttribute("style"));
                   break;

               default:
                   log.error("Click type must be one of left, right, double : {}", clickType);
                   throw new IllegalArgumentException("Invalid clickType: " + clickType);
           }
       } catch (ElementNotInteractableException e) {
           log.error("Menu cannot be clicked with {} click", clickType);
           throw new ElementNotInteractableException("Menu cannot be clicked: " + clickType);
       }

       if (dropdownLCMenu.equals(locator)) {
           String itemFinder = "//div[contains(@class, 'dropdown')][button[@id='my-dropdown-1']]//a[contains(@class, 'dropdown-item') and normalize-space(text())='" + item + "']";
           waiters.waitForElmntPrsntByLocator(By.xpath(itemFinder));
           action.moveToElement(find(By.xpath(itemFinder))).click().build().perform();
       } else if (dropdownRCMenu.equals(locator)) {
           String itemFinder = "//ul[@id='context-menu-2']//a[contains(@class, 'dropdown-item') and (text())='"+ item + "']";
           waiters.waitForElmntPrsntByLocator(By.xpath(itemFinder));
           action.moveToElement(find(By.xpath(itemFinder))).click().build().perform();

       } else if (dropdownDoubleCMenu.equals(locator)) {
           String itemFinder = "//ul[@id='context-menu-3']//a[contains(@class, 'dropdown-item') and (text())='"+ item + "']";
           waiters.waitForElmntPrsntByLocator(By.xpath(itemFinder));
           action.moveToElement(find(By.xpath(itemFinder))).click().build().perform();
       } else {
           log.error("Locator link doesn't match the @FindBy locator: {}", locator);
           throw new IllegalArgumentException("Locator: " + locator + " - is not equal to the @FindBy locator");
       }
   }
}
