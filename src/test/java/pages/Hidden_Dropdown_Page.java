package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class Hidden_Dropdown_Page {
    public Hidden_Dropdown_Page() {
        PageFactory.initElements(Driver.getDriver(), this);

    }
    @FindBy(xpath = "(//input[@class='search-input'])[1]")
    public WebElement gratisSearchBox;
    @FindBy(xpath = "//a[@class='has-media']/div")
    public List<WebElement> selectedProductList;
    @FindBy(xpath = "(//div[@class=' css-1hwfws3'])[1]")
    public WebElement stateCity;
    @FindBy(xpath = "//div[@class=' css-26l3qy-menu']//div")
    public List<WebElement> stateCityList;
    @FindBy(xpath = "//div[contains(@style,'border: 1px solid')]")
    public WebElement advertising;
}
