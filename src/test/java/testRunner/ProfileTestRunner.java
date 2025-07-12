package testRunner;

import Config.Setup;
import Pages.LoginPage;
import jdk.jshell.execution.Util;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class ProfileTestRunner extends Setup {

    //@Test(priority = 1)
    public void doLogin() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("lucas.hills@gmail.com", "1234");
        Utils.getToken(driver);
    }
    @BeforeTest
    public void setToken() throws ParseException, IOException, InterruptedException {
        Utils.setAuth(driver);
    }

    @Test(priority =2, description = "to get on profile w/o login")
    public void seeProfile(){
        driver.get("https://dailyfinance.roadtocareer.net/user");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.btnprofileIcon.click();
        loginPage.btnProfileMenuItem.get(0).click();
    }
}
