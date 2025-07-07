package Pages;

import Config.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage {

    @FindBy(tagName = "a")
    public List<WebElement> btnRegister;

    @FindBy(id = "firstName")
    WebElement txtFirstName;

    @FindBy(id = "lastName")
    WebElement txtLastName;

    @FindBy(id = "email")
    WebElement txtEmail;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "phoneNumber")
    WebElement txtPhoneNumber;

    @FindBy(id = "address")
    WebElement txtAddress;

    @FindBy(css = "[type=radio]")
    List<WebElement> rbGender;

    @FindBy(css = "[type=checkbox]")
    WebElement chkAcceptTerms;

    @FindBy(id = "register")
    WebElement btnSubmitReg;

    public RegistrationPage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }

    public void doRegistration(UserModel UserModel){
        txtFirstName.sendKeys(UserModel.getFirstname());
        txtLastName.sendKeys(UserModel.getLastname()!=null?UserModel.getLastname():"");
        txtEmail.sendKeys(UserModel.getEmail());
        txtPassword.sendKeys(UserModel.getPassword());
        txtPhoneNumber.sendKeys(UserModel.getPhonenumber());
        txtAddress.sendKeys(UserModel.getAddress()!=null? UserModel.getAddress() : "");
        rbGender.get(0).click();
        chkAcceptTerms.click();
        btnSubmitReg.click();
    }

}
