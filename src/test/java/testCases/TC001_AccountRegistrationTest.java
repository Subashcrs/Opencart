package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AccountRegistration;
import pageObjects.HomePage;
import testBase.BaseClass;

import java.time.Duration;
import java.util.Random;

public class TC001_AccountRegistrationTest extends BaseClass {


    @Test(groups = "Master")
    public void verify_account_registration()
    {
        logger.info("Verifying Account Registration Test");

try {
    HomePage homePage = new HomePage(driver);
    homePage.clickMyaccount();
    logger.info("clicked on My Account");
    homePage.clickRegister();
    logger.info("clicked on Register button");

    AccountRegistration accReg = new AccountRegistration(driver);
    logger.info("Providing Account Registration Details");
    accReg.setFirstName(randomString().toUpperCase());
    accReg.setLastName(randomString().toUpperCase());
    accReg.setEmail(randomString() + "@gmail.com");
    accReg.setTelephone(randomNumber());

    String pswd = randomAlpNum();

    accReg.setPassword(pswd);
    accReg.setConfirmPassword(pswd);

    accReg.setCheckbox();
    accReg.setButtonContinue();
    logger.info("validating expected message");
    String confmsg = accReg.getConfirmartion();
if(confmsg.equals("Your Account Has Been Created!")){
    Assert.assertTrue(true);

    }else{
        logger.error("Test Failed..");
        logger.debug("Debug logs..");
        Assert.assertTrue(false);
    }
    //Assert.assertEquals(confmsg, "Your Account Has Been Created!");
}catch (Exception e){

    Assert.fail();
}

logger.info("completed Verifying Account Registration Test");
    }

}
