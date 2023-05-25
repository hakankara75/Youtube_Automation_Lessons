package p1;

import utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class N11_Otomotiv {
    public N11_Otomotiv() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath = "(//a[contains(@title,'Otomotiv')])[1]")
    public WebElement otomotiv;
    @FindBy(xpath = "//a[@class='subCatMenuItem' and @title='Ses Sistemleri & Navigasyon']")
    public WebElement navigasyon;
    @FindBy(xpath = "//label[@for='local-special-checkbox']")
    public WebElement konumaOzel;

    @FindBy(xpath = "//div[contains(@class,'selected-item') or descendant::span[contains(text(),'Sırala:') and contains(text(),'Akıllı Sıralama')]]")
    public WebElement akilliSiralama;
    @FindBy(xpath = "//div[contains(@class,'item') and @data-value='NEWEST']")
    public WebElement yeniUrun;
    @FindBy(xpath = "//span[@class='closeBtn']")
    public WebElement bilgiKapa;
}
