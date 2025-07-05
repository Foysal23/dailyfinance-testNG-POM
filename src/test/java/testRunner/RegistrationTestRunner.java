package testRunner;


import Config.Setup;
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

    @Test
    public void userRegistration() throws InterruptedException, IOException, ParseException {
        RegistrationPage userReg = new RegistrationPage(driver);
        Faker faker = new Faker();
        userReg.btnRegister.get(1).click();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = "1234";
        String phoneNumber = "01721" + Utils.generateRandomNumber(100000,999999);
        String address =faker.address().fullAddress();
        userReg.doRegistration(firstName,lastName,email,password,phoneNumber,address);

        Thread.sleep(3000); //for some weird reason selenium was not able to catch the toast message until I added this with 3000ms
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast-body")));
        String successMessageActual = driver.findElement(By.className("Toastify__toast-body")).getText();
        System.out.println(successMessageActual);
        String successMessageExpected = "successfully";
        Assert.assertTrue(successMessageActual.contains(successMessageExpected));

        JSONObject userObj = new JSONObject();
        userObj.put("firstName",firstName);
        userObj.put("lastName",lastName);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phoneNumber);
        userObj.put("address",address);

        Utils.saveUserInfo("./src/test/resources/users.json",userObj);
    }
}
