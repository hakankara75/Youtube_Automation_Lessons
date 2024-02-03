package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BackgroundColorAssertion_Page {

    public BackgroundColorAssertion_Page(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "(//span[@class='new-badge'])[1]")
    public WebElement yeni;
    @FindBy(id = "lot_contact_attributes_email")
    public WebElement email;
    @FindBy(xpath = "//button[contains(@data-stat, new-lot-submit)]")
    public WebElement devamEt;
}
