package testRunner;

import Config.Setup;
import Pages.LoginPage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {

    private static final Logger log = LoggerFactory.getLogger(LoginTestRunner.class);

    @Test(priority = 1, description = "Admin login with wrong creds")
    public void loginWithWrongCred(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("wrongemail@gmail.com","wrongpass");
        String errorMessageActual = driver.findElement(By.tagName("p")).getText();
        String errorMessageExpected = "Invalid email or password";
        Assert.assertTrue(errorMessageActual.contains(errorMessageExpected));

        clearCreds();
    }

    @Test(priority = 2, description = "Admin login with right creds" )
    public void adminLogin(){
        LoginPage loginpage = new LoginPage(driver);
        loginpage.doLogin("admin@test.com", "admin123");
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "Admin Dashboard";
        Assert.assertTrue(headerActual.contains(headerExpected));
        loginpage.doLogOut();
    }

    @Test(priority = 3, description = "new user can login with correct cred")
    public void userLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONParser parser= new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));

        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email = (String) userObj.get("email");
        String password = (String) userObj.get("password");

        loginPage.doLogin(email,password);
        Assert.assertTrue(loginPage.btnprofileIcon.isDisplayed());
        loginPage.doLogOut();
    }

    public void clearCreds(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.txtEmail.sendKeys(Keys.CONTROL,"a");
        loginPage.txtEmail.sendKeys(Keys.BACK_SPACE);

        loginPage.txtPassword.sendKeys(Keys.CONTROL,"a");
        loginPage.txtPassword.sendKeys(Keys.BACK_SPACE);
    }
}
