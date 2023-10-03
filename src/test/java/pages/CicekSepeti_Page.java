package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CicekSepeti_Page {
    public CicekSepeti_Page(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//a[@href='/dogum-gunu-hediyeleri']/span[@class='main-menu__text js-main-menu-text']")
    public WebElement dogumGunu;
    @FindBy(xpath = "//div[@class='item-extra']/a")
    public WebElement hediye;
    @FindBy(xpath = "(//button[@class='ab-message-button'])[2]")
    public WebElement uyari;
    @FindBy(xpath = "(//a[@class='dropdown-toggle btn filter__button'])[4]")
    public WebElement renkler;
    @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement cerez;
    @FindBy(xpath = "//label[text()='Bej ']")
    public WebElement bej;
    @FindBy(xpath = "//label[text()='Beyaz ']")
    public WebElement beyaz;
    @FindBy(xpath = "//label[text()='Çok Renkli ']")
    public WebElement cokRenkli;
    @FindBy(xpath = "//label[text()='Gri ']")
    public WebElement gri;
    @FindBy(xpath = "//label[text()='Kahverengi ']")
    public WebElement kahverengi;
}
