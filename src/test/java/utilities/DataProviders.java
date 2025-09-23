package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="LoginData")
    public String[][] getData() throws IOException {
        String path="C:\\Users\\WELCOME\\IdeaProjects\\Opencart\\testData\\Cart.xlsx"; //taking xl file from testData

        ExcelUtilities xlutil = new ExcelUtilities(path); // creating an object for XLUtility

        int totalrows=xlutil.getRowNum("Sheet1");
        int totalcols=xlutil.getCellCount("Sheet1",1);

        String  logindata[][] = new String[totalrows][totalcols]; //created for two dimension array which can store

        for(int i=1;i<=totalrows;i++){ //1 // read the data from xl storing in the two dime array
            for(int j=0;j<totalcols;j++){ //0 i is row j is col
                logindata[i-1][j] = xlutil.getcellData("Sheet1",i,j);  //1,0

            }
        }
        return logindata; //returning two dime array

    }

    //data provider 2

    //data provider 3

    //data provider 4
}
