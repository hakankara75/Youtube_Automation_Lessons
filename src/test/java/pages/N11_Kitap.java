package pages;

import utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class N11_Kitap {
    public N11_Kitap() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath = "(//a[@class='itemContainer'])[8]")
    public WebElement kitap;
    @FindBy(xpath = "//img[@src='https://n11scdn.akamaized.net/a1/70/22/06/23/57/10/75/60/32/92/28/43/42/03419188245393918818.png']")
    public WebElement oyun;
    @FindBy(xpath = "//span[@class='selectedText']")
    public WebElement akilliSiralama;
    @FindBy(xpath = "//div[@class='item i4']")
    public WebElement yorumSayisi;
    @FindBy(xpath = "//h3[@class='productName']")
    public WebElement product;
    @FindBy(xpath = "(//div[@class='errorText'])[4]")
    public WebElement sifreHataliMesaji;
    @FindBy(id = "loginForm")
    public WebElement guvenlikKoduGirMesaji;


}
