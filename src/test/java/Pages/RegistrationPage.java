package Pages;

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

    public void doRegistration(String firstname, String lastname, String email, String password, String phonenumber, String address){
        txtFirstName.sendKeys(firstname);
        txtLastName.sendKeys(lastname);
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        txtPhoneNumber.sendKeys(phonenumber);
        txtAddress.sendKeys(address);
        rbGender.get(0).click();
        chkAcceptTerms.click();
        btnSubmitReg.click();
    }   
}
