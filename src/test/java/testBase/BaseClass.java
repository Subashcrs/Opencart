package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger; //log4j
    public Properties pro;

    @BeforeClass(groups = {"Sanity","Master","DataDriven","Regression"})
    @Parameters({"browser"})
    public void setup(String br) throws IOException {
        //Loading config.properties
        FileInputStream file = new FileInputStream("C:\\Users\\WELCOME\\IdeaProjects\\Opencart\\src\\src\\test\\resources\\config.properties");
        pro = new Properties();
        pro.load(file);
        logger= LogManager.getLogger(this.getClass());
        switch(br.toLowerCase()){
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    System.setProperty("webdriver.edge.driver","C:\\Users\\WELCOME\\Downloads\\msedgedriver.exe");
                    driver = new EdgeDriver();
                    break;
                default:
                System.out.println("Invalid Browser"); return;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.navigate().to(pro.getProperty("appUrl"));
    }

    @AfterClass(groups = {"Sanity","Master","DataDriven","Regression"})
    public void teardown(){
        driver.quit();
    }
    public String randomString(){
        String generatedstring = RandomStringUtils.randomAlphabetic(8);
        return generatedstring;
    }

    public String randomNumber(){
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public String randomAlpNum(){
        String generatedstring = RandomStringUtils.randomAlphabetic(3);
        String generatedNumber = RandomStringUtils.randomNumeric(5);
        return (generatedstring+"@"+generatedNumber);
    }

    public String captureScreen(String tname) throws IOException {

        String timestamp = new SimpleDateFormat("yyyMMddHHmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);


        String targetFilePath = "C:\\Users\\WELCOME\\IdeaProjects\\Opencart\\screenshots\\"+ tname + " " + timestamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}
