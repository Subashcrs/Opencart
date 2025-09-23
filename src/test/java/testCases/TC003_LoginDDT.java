package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is valid - login success - test pass - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail - logout
Data is invalid -- login failed - test pass
 */
public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class, groups = "DataDriven")//  getting data provider from different class
    public void verify_loginDDT(String email , String pwd , String exp){


        logger.info("Verify login DDT");
try {
    //home page
    HomePage hp = new HomePage(driver);
    hp.clickMyaccount();
    hp.loginButton();


    //login
    LoginPage loginPage = new LoginPage(driver);
    loginPage.setEmail(email);
    loginPage.setPassword(pwd);
    loginPage.clickLogin();


    //MyAccount
    MyAccountPage myAcc = new MyAccountPage(driver);
    boolean target = myAcc.isMyAccountPresent();
    System.out.println(target);
    if (exp.equalsIgnoreCase("Valid")) {
        /* Data is valid - login success - test pass - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail - logout
Data is invalid -- login failed - test pass
 */
        if (target == true) {
            myAcc.clickLogout();
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false,"Expected login to succeed, but it failed.");
        }
    }

    if (exp.equalsIgnoreCase("Invalid")) {
        if (target==true) {
            myAcc.clickLogout();
            Assert.assertTrue(false,"Expected login to succeed, but it failed.");

        } else {
            Assert.assertTrue(true);
        }
    }
}catch (Exception e){

    Assert.fail();
}finally {
    logger.info("finished login DDT");
}

    }

}
