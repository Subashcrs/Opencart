package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

@Test(groups = {"Master","Regression"})
public void verify_login(){
        logger.info("----starting LoginTest-----");
try {
    HomePage hp = new HomePage(driver);
    hp.clickMyaccount();
    hp.loginButton();

    logger.info("----clicked Login page-----");

    LoginPage loginPage = new LoginPage(driver);
    loginPage.setEmail(pro.getProperty("email"));
    loginPage.setPassword(pro.getProperty("pswd"));
    loginPage.clickLogin();

    logger.info("----clicked Login button-----");

    MyAccountPage myAcc = new MyAccountPage(driver);
    boolean target = myAcc.isMyAccountPresent();

    Assert.assertEquals(target, true, "Login failed");
}
catch (Exception ex) {
    Assert.fail();
}
 logger.info("-----end LoginTest-----");

}
}
