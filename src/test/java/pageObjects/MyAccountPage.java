package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {

        super(driver);

    }

@FindBy(xpath = "//h2[text()='My Orders']")
public WebElement lblOrders;

@FindBy(xpath = "//*[@id=\"column-right\"]/div/a[13]")
public WebElement logout;

    public boolean isMyAccountPresent() {
        try {
            return (lblOrders.isDisplayed());
        }
    catch (Exception e) {
        return false;}
    }

    public void clickLogout() {
        logout.click();
    }
}
