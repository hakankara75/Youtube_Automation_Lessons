package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class Login_KitapYurdu {
    public Login_KitapYurdu(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//a[@href='https://www.kitapyurdu.com/index.php?route=account/login']")
    public WebElement girisYap;
    @FindBy(id = "login-email")
    public WebElement eposta;
    @FindBy(id = "login-password")
    public WebElement password;
    @FindBy(xpath = "//button[@role='button']")
    public WebElement girisYapButonu;


}
