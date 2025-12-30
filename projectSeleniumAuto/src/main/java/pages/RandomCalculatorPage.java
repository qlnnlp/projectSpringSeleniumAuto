package pages;

import annotations.Path;
import interfaces.PressAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Path("/random-calculator")
public class RandomCalculatorPage extends AbsBasePage<RandomCalculatorPage>{

    public RandomCalculatorPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "screen")
    WebElement screen;

    @FindBy(xpath = "//span[contains(@class, 'btn btn-outline-primary') and contains(text(), 0)]")
    WebElement button0;

    @FindBy(xpath = "//span[contains(@class, 'btn btn-outline-primary') and contains(text(), 1)]")
    WebElement button1;

    @FindBy(xpath = "//span[contains(@class, 'btn btn-outline-primary') and contains(text(), 2)]")
    WebElement button2;

    @FindBy(xpath = "//span[contains(@class, 'btn btn-outline-primary') and contains(text(), 3)]")
    WebElement button3;

    @FindBy(xpath = "//span[contains(@class, 'btn btn-outline-primary') and contains(text(), 4)]")
    WebElement button4;

    @FindBy(xpath = "//span[contains(@class, 'btn btn-outline-primary') and contains(text(), 5)]")
    WebElement button5;

    @FindBy(xpath = "//span[contains(@class, 'btn btn-outline-primary') and contains(text(), 6)]")
    WebElement button6;

    @FindBy(xpath = "//span[contains(@class, 'btn btn-outline-primary') and contains(text(), 7)]")
    WebElement button7;

    @FindBy(xpath = "//span[contains(@class, 'btn btn-outline-primary') and contains(text(), 8)]")
    WebElement button8;

    @FindBy(xpath = "//span[contains(@class, 'btn btn-outline-primary') and contains(text(), 9)]")
    WebElement button9;

    @FindBy(xpath = "//span[contains(@class, 'btn btn-outline-primary') and contains(text(), '.')]")
    WebElement buttonDot;

    @FindBy(xpath = "//span[contains(@class, 'btn btn-outline-warning') and contains(text(), '=')]")
    WebElement buttonEqual;

    @FindBy(xpath = "//span[contains(@class, 'clear btn btn-outline-danger') and contains(text(), C)]")
    WebElement clearC;

    @FindBy(xpath = "//span[contains(@class, 'operator btn btn-outline-success') and contains(text(), '+')]")
    WebElement operatorPlus;

    @FindBy(xpath = "//span[contains(@class, 'operator btn btn-outline-success') and contains(text(), '-')]")
    WebElement operatorMinus;

    @FindBy(xpath = "//span[contains(@class, 'operator btn btn-outline-success') and contains(text(), '÷')]")
    WebElement operatorDivide;

    @FindBy(xpath = "//span[contains(@class, 'operator btn btn-outline-success') and contains(text(), 'x')]")
    WebElement operatorMultiply;




    public void  pressEqual() {
        buttonEqual.click();
    }

    public void pressPlus() {
        operatorPlus.click();
    }

    public void pressMinus() {
        operatorMinus.click();
    }

    public void pressDivide() {
        operatorDivide.click();
    }

    public void pressMultiply() {
        operatorMultiply.click();

    }

    public void pressClear() {
        clearC.click();
    }

    public void performSimpleOperationInt(int x, int y, PressAction action) {
        switch (x) {
            case 0:
                button0.click();
                break;
            case 1:
                button1.click();
                break;
            case 2:
                button2.click();
                break;
            case 3:
                button3.click();
                break;
            case 4:
                button4.click();
                break;
            case 5:
                button5.click();
                break;
            case 6:
                button6.click();
                break;
            case 7:
                button7.click();
                break;
            case 8:
                button8.click();
                break;
            case 9:
                button9.click();
                break;
        }

        action.execute();

        switch (y) {
            case 0:
                button0.click();
                break;
            case 1:
                button1.click();
                break;
            case 2:
                button2.click();
                break;
            case 3:
                button3.click();
                break;
            case 4:
                button4.click();
                break;
            case 5:
                button5.click();
                break;
            case 6:
                button6.click();
                break;
            case 7:
                button7.click();
                break;
            case 8:
                button8.click();
                break;
            case 9:
                button9.click();
                break;
        }

        pressEqual();
    }

    public int getScreenNumber(int expectedResult) {
        waiters.fluentWait
                (ExpectedConditions.textToBePresentInElement(screen, String.valueOf(expectedResult)),
                        3, 1);
        return Integer.parseInt(screen.getText());
    }

}
