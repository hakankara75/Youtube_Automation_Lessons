package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;
import utilities.ReusableMethods;

public class CokSatanKitaplar_Hakan_StepDefinition {


    Select select;
    String selectZaman;
    String ilkKitap = "";
    String ikinciKitap = "";
    String ucuncuKitap = "";

    @Given("kullanici cok satan kitaplar menusu ustune gelir")
    public void kullaniciCokSatanKitaplarMenusuUstuneGelir() {
        WebElement element = ReusableMethods.webelementJavaScript("document.querySelector(\"#mainNav > div.nav-content > ul > li.book.has-menu.active > div.lvl2.js-bookCr > ul > li:nth-child(1)\")");
        ReusableMethods.moveToElementWithAction(element);
    }
    @When("{int} saniye bekleme yapar")
    public void saniye_bekleme_yapar(Integer int1) {
        ReusableMethods.bekle(int1);
    }

    @When("cok satan edebiyat kitaplari linkini tiklar")
    public void cok_satan_edebiyat_kitaplari_linkini_tiklar() {

        WebElement element= ReusableMethods.webelementJavaScript("document.querySelector(\"#mainNav > div.nav-content > ul > li.book.has-menu.active > div.lvl2.js-bookCr > ul > li:nth-child(1) > div > div.open-menu-ct.bookBestSeller > div.subCategories > ul:nth-child(2) > li:nth-child(1) > a > strong\")");

        ReusableMethods.flash(element, Driver.getDriver());
        ReusableMethods.webelementJavaScript("document.querySelector(\"#mainNav > div.nav-content > ul > li.book.has-menu.active > div.lvl2.js-bookCr > ul > li:nth-child(1) > div > div.open-menu-ct.bookBestSeller > div.subCategories > ul:nth-child(2) > li:nth-child(1) > a > strong\")").click();

    }
}

