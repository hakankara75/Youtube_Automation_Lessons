package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class Enuygun_Page {
    public Enuygun_Page(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//a[@class='nav-link active']")
    public WebElement ucakBiletiBtn;
    @FindBy(xpath = "(//div[@class='suggestion_item'])[1]")
    public WebElement neredenNereyeList;
    @FindBy(xpath = "//input[@id='OriginInput']")
    public WebElement neredenInput;

    @FindBy(xpath = "//input[@id='DestinationInput']")
    public WebElement nereyeInput;
    @FindBy(xpath = "//input[@id='oneWayCheckbox']")
    public WebElement tekYonCheckBox;
    @FindBy(css = "td[aria-label='Selected. Cuma, 2 Şubat 2024']")
    public WebElement seciliTarih;
    @FindBy(css = "path[d='M8.59 16.59L13.17 12 8.59 7.41 10 6l6 6-6 6-1.41-1.41z']")
    public WebElement ok;
    @FindBy(id = "DepartureDate")
    public WebElement departureDate;
    @FindBy(xpath = "//td[@class='CalendarDay CalendarDay_1 CalendarDay__default CalendarDay__default_2']")
    public List<WebElement> departureDate2;
    @FindBy(xpath = "//input[@id='ReturnDate']")
    public WebElement returnDate;
    @FindBy(css = "button[data-testid='formSubmitButton']")
    public WebElement ucuzBiletBul;
    @FindBy(xpath = "//div[@class='CalendarDay__content']")
    public List<WebElement> calendarDayBtn;
}
