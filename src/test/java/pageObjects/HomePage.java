package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;

public class HomePage extends BasePage {


public HomePage(WebDriver driver) {

    super(driver);
}

@FindBy(xpath = "//a[normalize-space()=\"My Account\"]")
    WebElement lnkMyaccount;

@FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;

@FindBy(xpath = "//a[text()='Login']")
 WebElement lnkLogin;

public void clickMyaccount() {
    lnkMyaccount.click();
}

public void clickRegister() {
    lnkRegister.click();
}

public void loginButton(){ lnkLogin.click();

}
}
