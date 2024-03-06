package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.netty.util.internal.ReflectionUtil;
import pages.Hidden_Dropdown_Page;
import utilities.ConfigReader;
import utilities.ReusableMethods;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utilities.Driver.getDriver;

public class Hidden_Dropdown_StepDef {

    Hidden_Dropdown_Page page=new Hidden_Dropdown_Page();
    @Given("gratis sitesine gidilir")
    public void gratisSitesineGidilir() {
        getDriver().get(ConfigReader.getProperty("gratis"));
    }

    @When("searchboxa {string} girilir")
    public void searchboxaGirilir(String arg0) {
        page.gratisSearchBox.sendKeys(arg0);

    }

    @Then("Listenin ilk sirasinda {string} gorundugu dogrulanir")
    public void listeninIlkSirasindaGorunduguDogrulanir(String arg0) {
        String expected=arg0;
        System.out.println("expected = " + expected);
        String actual=ReusableMethods.getTextWithJavaScriptXpath("(//a[@class='has-media']/div)[1]");
        System.out.println("actual = " + actual);
        assertTrue(actual.contains(expected));
    }

    @Given("demoqa sitesine gidilir")
    public void demoqaSitesineGidilir() {
        getDriver().get(ConfigReader.getProperty("demoqa"));
    }

    @When("State and City den {string} secilir")
    public void stateAndCityDenSecilir(String arg0) {
        ReusableMethods.scrollToElementWithWebElement(page.advertising);
        ReusableMethods.visibleWait(page.advertising, 5);
        page.stateCity.click();
    }

    @Then("{string} secildigi dogrulanir")
    public void secildigiDogrulanir(String arg0) {
        String expected=arg0;
        page.stateCityList.get(1).click();
        ReusableMethods.scrollToElementWithWebElement(page.advertising);
        ReusableMethods.visibleWait(page.advertising, 5);
        String actual= page.stateCity.getText();

        assertEquals(expected, actual);
    }
}
