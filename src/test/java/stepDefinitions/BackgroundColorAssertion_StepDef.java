package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.Color;
import pages.BackgroundColorAssertion_Page;
import utilities.ConfigReader;

import static org.junit.Assert.assertEquals;
import static utilities.Driver.getDriver;

public class BackgroundColorAssertion_StepDef {
    BackgroundColorAssertion_Page page= new BackgroundColorAssertion_Page();

    @Given("kullanici Trendyol sitesine gider")
    public void kullaniciTrendyolSitesineGider() {
        getDriver().get(ConfigReader.getProperty("trendyol"));
    }

    @Then("kullanici cok satanlar linki yanindaki yeni butonunun rengini dogrular")
    public void kullaniciCokSatanlarLinkiYanindakiYeniButonununRenginiDogrular() {
            String expectedRgbColorCode= "#dc2e2e";
        System.out.println("expectedRgbColorCode = " + expectedRgbColorCode);

        String backgroundColor= page.yeni.getCssValue("background-color");
        System.out.println("backgroundColor = " + backgroundColor);

        Color color= Color.fromString(backgroundColor);
        System.out.println("color = " + color);
        String actualRgbColorCode= color.asHex();
        System.out.println("actualRgbColorCode = " + actualRgbColorCode);

        assertEquals(expectedRgbColorCode, actualRgbColorCode);


    }
}
