package stepDefinitions;

import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import pages.ShadowRoot_Page;
import utilities.ConfigReader;
import utilities.Driver;


public class ShadowRoot_StepDef {

    ShadowRoot_Page teknosaPage=new ShadowRoot_Page();
    @Given("teknosa sayfasına gider")
    public void teknosa_sayfasına_gider() {
        Driver.getDriver().get(ConfigReader.getProperty("teknosa"));
        teknosaPage.kampanya.click();
        SearchContext searchContext = Driver.getDriver().findElement(By.xpath("//efilli-layout-dynamic")).getShadowRoot();

        WebElement shadowElement = searchContext.findElement(By.cssSelector("div[data-name='Accept Button']"));

        shadowElement.click();
    }

}
