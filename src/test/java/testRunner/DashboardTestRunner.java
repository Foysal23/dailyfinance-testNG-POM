package testRunner;

import Config.Setup;
import Pages.DashboardPage;
import Pages.LoginPage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileReader;

public class DashboardTestRunner extends Setup {

    @BeforeTest
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);

        JSONParser parser= new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));

        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email = (String) userObj.get("email");
        String password = (String) userObj.get("password");

        if(System.getProperty("username")!=null && System.getProperty("password")!=null){
            loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        }else {
            loginPage.doLogin(email,password);
        }
    }

    @Test(priority = 1, description = "Add cost of a item")
    public void addCost() throws InterruptedException {

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.btnAddCost.click();
        dashboardPage.addCost("Radiant Way Book 2", "1500");
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
    }
}
