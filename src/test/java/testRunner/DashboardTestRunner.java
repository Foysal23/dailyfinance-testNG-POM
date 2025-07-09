package testRunner;

import Config.Setup;
import Pages.DashboardPage;
import Pages.LoginPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DashboardTestRunner extends Setup {

    @BeforeTest
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
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
