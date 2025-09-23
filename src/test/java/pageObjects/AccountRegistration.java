package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountRegistration extends BasePage {


    public  AccountRegistration(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "input-firstname")
    WebElement firstName;

    @FindBy(id = "input-lastname")
    WebElement lastName;

    @FindBy(id = "input-email")
    WebElement email;

    @FindBy(id = "input-telephone")
    WebElement telephone;

    @FindBy(id="input-password")
    WebElement password;

    @FindBy(id = "input-confirm")
    WebElement confirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
     WebElement checkBox;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement buttonContinue;

    @FindBy(xpath = "//*[@id='content']/h1")
    WebElement msgConfirmartion;

    public void setFirstName(String fn){
        firstName.sendKeys(fn);
    }

    public void setLastName(String ln){
        lastName.sendKeys(ln);
    }

    public void setEmail(String mail){
        email.sendKeys(mail);
    }

    public void setTelephone(String tp){
        telephone.sendKeys(tp);
    }
    public void setPassword(String pwd){
        password.sendKeys(pwd);
    }
    public void setConfirmPassword(String pwd){
        confirmPassword.sendKeys(pwd);
    }

    public void setCheckbox(){
        checkBox.click();
    }

    public void setButtonContinue(){
        buttonContinue.click();
    }

    public String getConfirmartion(){
        try{
            return(msgConfirmartion.getText());
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
