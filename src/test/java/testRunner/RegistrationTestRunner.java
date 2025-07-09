package testRunner;


import Config.Setup;
import Config.UserModel;
import Pages.RegistrationPage;
import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {

    @Test(priority = 1, description = "user can register providing all details", groups = "smoke")
    public void userRegistrationByAllFields() throws InterruptedException, IOException, ParseException {

        RegistrationPage userReg = new RegistrationPage(driver);
        Faker faker = new Faker();

        userReg.btnRegister.get(1).click();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = "1234";
        String phoneNumber = "01721" + Utils.generateRandomNumber(100000,999999);
        String address =faker.address().fullAddress();

        UserModel userModel = new UserModel();
        userModel.setFirstname(firstName);
        userModel.setLastname(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phoneNumber);
        userModel.setAddress(address);

        userReg.doRegistration(userModel);

        doRegAssertion();

        JSONObject userObj = new JSONObject();
        userObj.put("firstName",firstName);
        userObj.put("lastName",lastName);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phoneNumber);
        userObj.put("address",address);

        Utils.saveUserInfo("./src/test/resources/users.json",userObj);

    }

    @Test(priority = 2, description = "User can register only by providing mandatory info")
    public void userRegByMandatoryFields() throws IOException, ParseException, InterruptedException {

        Thread.sleep(4000); //used it this time as the toast takes a bit longer to disappear at times
        RegistrationPage userReg = new RegistrationPage(driver);
        Faker faker = new Faker();

        userReg.btnRegister.get(1).click();
        String firstName = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String password = "1234";
        String phoneNumber = "01721" + Utils.generateRandomNumber(100000,999999);

        UserModel userModel = new UserModel();

        userModel.setFirstname(firstName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phoneNumber);
        userReg.doRegistration(userModel);

        doRegAssertion();

        JSONObject userObj = new JSONObject();

        userObj.put("firstName",firstName);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phoneNumber);

        Utils.saveUserInfo("./src/test/resources/users.json",userObj);

    }

    public void doRegAssertion() throws InterruptedException {

        Thread.sleep(3000); //update: my system is slow so had to use it, as it was failing earlier to catch the toast
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast-body")));
        String successMessageActual = driver.findElement(By.className("Toastify__toast-body")).getText();
        System.out.println(successMessageActual);
        String successMessageExpected = "successfully";
        Assert.assertTrue(successMessageActual.contains(successMessageExpected));
    }
}
