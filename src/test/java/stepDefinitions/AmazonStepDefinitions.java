package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.tr.Ve;
import org.openqa.selenium.Keys;
import pages.AmazonPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import static org.junit.Assert.assertTrue;

public class AmazonStepDefinitions {
    AmazonPage amazonPage = new AmazonPage();

    @Given("kullanici Amazon sayfasinda")
    public void kullanici_amazon_sayfasinda() {
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
    }

    @Then("kullanici Nutella icin arama yapar")
    public void kullanici_nutella_icin_arama_yapar() {
        amazonPage.aramaKutusu.sendKeys("Nutella", Keys.ENTER);
    }

    @Then("sonuclarin Nutella icerdigini test eder")
    public void sonuclarin_nutella_icerdigini_test_eder() {
        assertTrue(amazonPage.sonucYazisi.getText().contains("Nutella"));
    }

    @Then("sayfayi kapatir")
    public void sayfayi_kapatir() {

        Driver.closeDriver();
    }

    @Then("kullanici Java icin arama yapar")
    public void kullanici_java_icin_arama_yapar() {
        amazonPage.aramaKutusu.sendKeys("Java", Keys.ENTER);
    }
    @Then("sonuclarin Java icerdigini test eder")
    public void sonuclarin_java_icerdigini_test_eder() {
        assertTrue(amazonPage.sonucYazisi.getText().contains("Java"));
    }

    @Then("kullanici iphone icin arama yapar")
    public void kullaniciIphoneIcinAramaYapar() {
        amazonPage.aramaKutusu.sendKeys("iphone", Keys.ENTER);
    }

    @And("sonuclarin iphone icerdigini test eder")
    public void sonuclarinIphoneIcerdiginiTestEder() {
        assertTrue(amazonPage.sonucYazisi.getText().contains("iphone"));
    }

    @Then("kullanici {string} icin arama yapar")
    public void kullaniciIcinAramaYapar(String istenenKelime) {
        amazonPage.aramaKutusu.sendKeys(istenenKelime, Keys.ENTER);
    }

    @And("kullanici {string} icerdigini test eder")
    public void kullaniciIcerdiginiTestEder(String istenenKelime) {
        assertTrue(amazonPage.sonucYazisi.getText().contains(istenenKelime));
    }



    @Then("kullanici {int} sn bekler")
    public void kullaniciSnBekler(int istenenSaniye)  {
        try {
            Thread.sleep(istenenSaniye*1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
    }

    @And("url'in {string} icerdigini test eder")
    public void urlInIcerdiginiTestEder(String istenenKelime) {
        amazonPage.aramaKutusu.sendKeys(istenenKelime, Keys.ENTER);
    }


    @Then("Kullanıcı Sign In menüsünü tıklar")
    public void kullanıcıSignInMenusunuTıklar() {
        amazonPage.signIn.click();
    }

    @And("Kullanıcı email kutusuna mail girer")
    public void kullanıcıEmailKutusunaMailGirer() {
        amazonPage.email.sendKeys(ConfigReader.getProperty("email"));
    }

    @Then("Kullanıcı continue butonunu tıklar")
    public void kullanıcıContinueButonunuTıklar() {
        amazonPage.continueButton.click();

    }

    @And("Kullanıcı password kutusuna password girer")
    public void kullanıcıPasswordKutusunaPasswordGirer() {
        amazonPage.password.sendKeys(ConfigReader.getProperty("password"));
    }

    @Then("Kullanıcı Sign In butonunu tıklar")
    public void kullanıcıSignInButonunuTıklar() {
        amazonPage.signInSubmit.click();
    }


    @Ve("Amazon'un Secimi yazan urunu tiklar")
    public void amazonUnSecimiYazanUrunuTiklar() {
        amazonPage.amazonunSecimi.click();
        ReusableMethods.bekle(3);
    }

    @When("kullanici Başa dön yazisina kadar iner")
    public void kullaniciBasaDonYazisinaKadarIner() {
        ReusableMethods.scrollToElementWithWebElement(amazonPage.backToTop);
    }

    @Then("Başa dön yazisina geldigini dogrular")
    public void basaDonYazisinaGeldiginiDogrular() {
        assertTrue(amazonPage.backToTop.isDisplayed());
    }
}
