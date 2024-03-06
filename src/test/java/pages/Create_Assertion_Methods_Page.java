package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

import static org.junit.Assert.*;

public class Create_Assertion_Methods_Page {

    public Create_Assertion_Methods_Page() {
        PageFactory.initElements(Driver.getDriver(), this);

    }
    @FindBy(id = "ui-id-4")
    public WebElement women;
    @FindBy(id = "ui-id-9")
    public WebElement tops;
    @FindBy(id = "ui-id-11")
    public WebElement jackets;
    @FindBy(xpath = "//strong[@class='product name product-item-name']")
    public List<WebElement> products;
    @FindBy(xpath = "//h1/span")
    public WebElement selectedPproduct;
    @FindBy(id = "option-label-size-143-item-167")
    public WebElement size;
    @FindBy(xpath = "//span[@class='price']")
    public WebElement price;
    @FindBy(id = "option-label-color-93-item-50")
    public WebElement blue;
    @FindBy(xpath = "(//span[@class='swatch-attribute-selected-option'])[2]")
    public WebElement selectedBlue;
    @FindBy(id = "product-addtocart-button")
    public WebElement addToCart;
    @FindBy(xpath = "(//span[@class='counter qty'])[1]")
    public WebElement counterNumber;
    @FindBy(xpath = "//span[contains(@data-ui-id,'page-title-wrapper')]")
    public WebElement selectedProductTitle;
    @FindBy(xpath = "//span[@data-role='title']//span[text()='See Details']")
    public WebElement seeDetails;
    @FindBy(xpath = "(//span[@class='swatch-attribute-selected-option'])[1]")
    public WebElement basketSize;
    @FindBy(xpath = "(//span[@class='swatch-attribute-selected-option'])[2]")
    public WebElement basketColor;
    @FindBy(id = "product-price-1396")
    public WebElement basketPrice;

    public void assertEqualsText(String expected, String actual){
        assertEquals(expected,actual);
    }
    public void assertContainsText(String expected, String actual){
        assertTrue(actual.contains(expected));
    }
}
