package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.BackgroundColorAssertion_Page;
import utilities.ConfigReader;

import static org.junit.Assert.assertEquals;
import static utilities.Driver.getDriver;
import static utilities.ReusableMethods.assertMessage;

public class Message_BootStrap_StepDef {
    BackgroundColorAssertion_Page page= new BackgroundColorAssertion_Page();
    @Given("{string} sitesine giris yapilir")
    public void sitesineGirisYapilir(String arg0) {
        getDriver().get(ConfigReader.getProperty(arg0));
    }

    @And("email kutusuna yanlis data girilir")
    public void emailKutusunaYanlisDataGirilir() {
        page.email.sendKeys("hakan@.com");
    }

    @And("devam et butonuna basilir")
    public void devamEtButonunaBasilir() {
        page.devamEt.click();
    }

    @Then("giris yapilamadigi dogrulanir")
    public void girisYapilamadigiDogrulanir() {
        String expected = "\".\", \".com\" adı içinde yanlış bir konumda kullanılmış.";
        String actual =assertMessage(page.email);
        System.out.println("actual = " + actual);

        assertEquals(expected, actual);


    }


}
