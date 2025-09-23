package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceClassPathResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportUtility implements ITestListener {


    public ExtentSparkReporter sparkReporter; //UI of the report
    public ExtentReports extent; //populate common info on the report
    public ExtentTest test; // creating test case entries in the report and update status of the test methods

    String repName;

    public void onStart(ITestContext testContext) {

        String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
        repName = "Test-report-" + timestamp + ".html";
         String reportDir="C:\\Users\\WELCOME\\IdeaProjects\\Opencart\\reports\\";
        sparkReporter = new ExtentSparkReporter(reportDir+repName);//specify location
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        sparkReporter.config().setDocumentTitle("Opencart Automation Report"); //Title of report
        sparkReporter.config().setReportName("Opencart Functional testing"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);


        extent.setSystemInfo("Application" , "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module" , "Customers");
        extent.setSystemInfo("User Name",System.getProperty("user.name"));
        extent.setSystemInfo("Environment","QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating system",os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser",browser);

        List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
                if(!includeGroups.isEmpty()){
                    extent.setSystemInfo("Groups" , includeGroups.toString());
                }
    }

    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getTestClass().getName()); // create a new entry in the report
        test.assignCategory(result.getMethod().getGroups()); // to display groups in report
        test.log(Status.PASS, "Test Passed" + result.getName()+" got successfully executed"); // update status p/f/s
        //test.log(Status.PASS, "Test Failed" + result.getName());


    }

    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL,  result.getName()+" got failed");
        test.log(Status.INFO,  result.getThrowable().getMessage());

        try{
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {

        test=extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName()+" skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        extent.flush();
        String reportDir="C:\\Users\\WELCOME\\IdeaProjects\\Opencart\\reports\\";
        //String pathOfExtentReport = "C:\\Users\\WELCOME\\IdeaProjects\\Opencart\\reports\\"+repName;
        File extentReport = new File(reportDir+repName);

        try {
            if(extentReport.exists()){
                Desktop.getDesktop().browse(extentReport.toURI());
            } else {
                System.out.println("Report file not found " );
            }

        } catch (IOException e) {
           e.printStackTrace();
        }

       /* try{
            URL url = new URL("C:\\Users\\WELCOME\\IdeaProjects\\Opencart\\reports\\"+ repName);

            //create the email message
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceClassPathResolver(url));
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthentication(new DefaultAuthenticator("subashcr3@gmail.com","Ronaldo@Idol7"));
            email.setSSLOnConnect(true);
            email.setFrom("subashcr3@gmail.com");
            email.setSubject("Test Results");
            email.setMsg("Please find Attached Report...");
            email.addTo("subashdec131@gmail.com");
            email.attach(url, "extent report", "please check report....");
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        }
    }



