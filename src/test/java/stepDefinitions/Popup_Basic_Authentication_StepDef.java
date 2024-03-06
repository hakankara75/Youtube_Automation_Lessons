package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.message.ReusableMessage;
import pages.ShadowRoot_Page;
import utilities.ReusableMethods;

import static org.junit.Assert.assertEquals;
import static utilities.Driver.getDriver;

public class Popup_Basic_Authentication_StepDef {
    ShadowRoot_Page page=new ShadowRoot_Page();
    @Given("kullanici heroku sayfasina gider")
    public void kullaniciHerokuSayfasinaGider() {
        getDriver().get("https://the-internet.herokuapp.com/");

    }

    @And("basic auth linkini tiklar")
    public void basicAuthLinkiniTiklar() {
        page.basicAuth.click();
        ReusableMethods.bekle(2);
    }

    @Then("sayfaya giris yaptigini dogrular")
    public void sayfayaGirisYaptiginiDogrular() {
        String popupUrl= "the-internet.herokuapp.com/basic_auth";
        String username="admin";
        String password="admin";
        String expectedMessage="Congratulations! You must have the proper credentials.";

        getDriver().get("https://"+username+":"+password+"@"+popupUrl);

        String actualMessage=page.herokuMessage.getText().trim();
        ReusableMethods.bekle(2);
        assertEquals(expectedMessage, actualMessage);

    }


}
