package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.Enuygun_Page;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static utilities.Driver.getDriver;

public class Enuygun {
    Enuygun_Page page = new Enuygun_Page();
    @Given("Kullanici anasayfaya gider")
    public void kullanici_anasayfaya_gider() {
        getDriver().get("https://www.enuygun.com/");
        Actions actions = new Actions(getDriver());
        actions.sendKeys(page.neredenInput,"Ankara").build().perform();
        page.neredenNereyeList.click();

        actions.sendKeys(page.nereyeInput,"İstanbul").build().perform();
        page.neredenNereyeList.click();

    }

    @Then("Kullanici gidis ve donus tarihlerini secer")
    public void kullanici_gidis_ve_donus_tarihlerini_secer() {

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
//        js.executeScript("arguments[0].value = arguments[1]", page.departureDate, ConfigReader.getProperty("gidisDate"));
//        ReusableMethods.bekle(2);
//        page.ucuzBiletBul.click();


        page.departureDate.click();
        System.out.println("page.departureDate2.get(0) = " + page.departureDate2.get(0).getAttribute("aria-label"));
        js.executeScript("arguments[0].setAttribute('aria-label', arguments[1])", page.departureDate2.get(0), "Cuma, 19 Oca 2024");
        ReusableMethods.bekle(3);
        page.departureDate2.get(0).click();
        ReusableMethods.bekle(3);
        page.ucuzBiletBul.click();
        ReusableMethods.bekle(3);

    }

}
