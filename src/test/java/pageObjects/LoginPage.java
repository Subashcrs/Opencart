package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }

@FindBy(xpath = "//input[contains(@id,'input-email')]")
public WebElement txtEmail;

@FindBy(xpath = "//input[contains(@id,'input-password')]")
public WebElement txtPassword;

@FindBy(xpath = "//input[contains(@value,'Login')]")
public WebElement btnLogin;

public void setPassword(String password){
    txtPassword.sendKeys(password);
}

public void setEmail(String email){
    txtEmail.sendKeys(email);
}

public void clickLogin(){
    btnLogin.click();
}

}
