package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import pages.ShadowRoot_Page;
import utilities.ConfigReader;
import utilities.Driver;

import static utilities.Driver.getDriver;


public class ShadowRoot_StepDef {

    ShadowRoot_Page teknosaPage=new ShadowRoot_Page();
    @Given("teknosa sayfasına gider")
    public void teknosa_sayfasına_gider() {
        getDriver().get(ConfigReader.getProperty("teknosa"));
        teknosaPage.kampanya.click();


        /*asagidaki kod ile shadow root yapisina sahip tag'in locatini searchContext objesine atiyoruz. Boylece elementin
        Shadow Dom try yapisina ulasiliyor
                    */
        SearchContext searchContext = getDriver().findElement(By.xpath("//efilli-layout-dynamic")).getShadowRoot();



        /*
        asagidaki kod click islemi yapilmak istenen elementi bulmak icin searchContext icindeki Shadow Dom
        icine bir element locate verilerek aranir ve bir webelemente atanir.
         */
        WebElement shadowElement = searchContext.findElement(By.cssSelector("div[data-name='Accept Button']"));

        shadowElement.click();
    }


}
