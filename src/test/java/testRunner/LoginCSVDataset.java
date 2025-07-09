package testRunner;

import Config.LoginDataSet;
import Config.Setup;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginCSVDataset extends Setup {

    @Test(dataProvider = "LoginCSVData", dataProviderClass = LoginDataSet.class)
    public void doLogin(String email,String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(email,password);
    }
}
