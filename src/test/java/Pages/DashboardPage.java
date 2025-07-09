package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    @FindBy(className="add-cost-button")
    public WebElement btnAddCost;

    @FindBy(id="itemName")
    public WebElement txtItemName;

    @FindBy(id="amount")
    public WebElement txtAmount;

    @FindBy(css="[type=submit]")
    public WebElement btnSubmit;

    public DashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void addCost(String itemName, String amount){
        txtItemName.sendKeys(itemName);
        txtAmount.sendKeys(amount);
        btnSubmit.click();
    }

}
