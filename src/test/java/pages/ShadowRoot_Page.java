package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ShadowRoot_Page {

    public ShadowRoot_Page(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(id = "ins-editable-button-1580496494")
    public WebElement kampanya;
    @FindBy(xpath = "//div[@id='ash-plc']/following-sibling::efilli-layout-dynamic")
    public WebElement shadowRoot;
    @FindBy(css = "px-16px py-8px cursor-pointer rounded-4px bg-[#f58220] text-white grow text-center font-600 col-span-full md:col-span-1 lt-md:row-start-3")
    public WebElement cerez;
}
