package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AmazonPage {
    public AmazonPage() {
        PageFactory.initElements(Driver.getDriver(), this);

    }
    @FindBy (id = "twotabsearchtextbox")
    public WebElement aramaKutusu;
    @FindBy (xpath = "//div[@class='sg-col-14-of-20 sg-col-18-of-24 sg-col s-breadcrumb sg-col-10-of-16 sg-col-6-of-12']")
    public WebElement sonucYazisi;
    @FindBy (id = "nav-link-accountList-nav-line-1")
    public WebElement signIn;
    @FindBy (id = "ap_email")
    public WebElement email;
    @FindBy (id = "continue")
    public WebElement continueButton;
    @FindBy (id = "ap_password")
    public WebElement password;
    @FindBy (id = "signInSubmit")
    public WebElement signInSubmit;
    @FindBy (xpath = "//*[@class='a-link-normal']//following::img[1]")
    public WebElement image;
    @FindBy (xpath = "//*[@class='a-link-normal']//following::img[2]")
    public WebElement image2;
    @FindBy (xpath = "//*[@class='a-link-normal']//following::img[3]")
    public WebElement image3;
    @FindBy (xpath = "//*[@class='a-link-normal']//following::img[4]")
    public WebElement image4;
    @FindBy (xpath = "//*[@class='a-link-normal']//following::img[5]")
    public WebElement image5;
    @FindBy (xpath = "//span[text()='Amazon’un ']")
    public WebElement amazonunSecimi;
}
