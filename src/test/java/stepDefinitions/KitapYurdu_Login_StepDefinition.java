package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.Login_KitapYurdu;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class KitapYurdu_Login_StepDefinition {

    Login_KitapYurdu locate= new Login_KitapYurdu();

    @Then("giris yap linkini tiklar")
    public void girisYapLinkiniTiklar() {
        locate.girisYap.click();
    }

    @And("eposta kutusuna mailini girer")
    public void epostaKutusunaMailiniGirer() {
        locate.eposta.sendKeys(ConfigReader.getProperty("epostaHakan"));

    }

    @And("sifre kutusuna sifresini girer")
    public void sifreKutusunaSifresiniGirer() {
        locate.password.sendKeys(ConfigReader.getProperty("sifreHakan"));
    }

    @And("giris yap butonunu tiklar")
    public void girisYapButonunuTiklar() {
        locate.girisYapButonu.click();
    }

    @Given("Kullanici {string} e gider")
    public void kullaniciEGider(String url) {
        Driver.getDriver().get(ConfigReader.getProperty("kitapYurduUrl"));
        ReusableMethods.bekle(2);
    }

}
