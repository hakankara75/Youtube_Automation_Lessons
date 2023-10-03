package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.CicekSepeti_Page;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CicekSepeti_StefDef {
    CicekSepeti_Page data=new CicekSepeti_Page();

    @Given("{string} sitesine gidilir")
    public void sitesineGidilir(String arg0) {
        Driver.getDriver().get("https://www.ciceksepeti.com");
    }
    @When("hediye linki tiklanir")
    public void hediye_linki_tiklanir() {

        data.hediye.click();
      ReusableMethods.bekle(25);
    }

    @When("dogum gunu linki tiklanir")
    public void dogumGunuLinkiTiklanir() {
        data.cerez.click();
        data.dogumGunu.click();
        data.uyari.click();
    }


    @Then("dogum gunu sayfasinda oldugu dogrulanir")
    public void dogumGunuSayfasindaOlduguDogrulanir() {
        String expectedTitle= "Doğum Günü Hediyeleri (1000'den Fazla Hediye Önerisi)";
        String actualTitle= Driver.getDriver().getTitle();
        assertEquals(expectedTitle, Driver.getDriver().getTitle());

    }


    @When("renk drop down tiklanir")
    public void renkDropDownTiklanir() {

        
    }

    @Then("sirasiyla renkler secilir")
    public void sirasiylaRenklerSecilir(DataTable dataTable) {
        data.renkler.click();
        List<String> bilgiler = dataTable.asList();
        for (int i = 1; i < bilgiler.size(); i++) {
            System.out.println("bilgiler.get(i) = " + bilgiler.get(i));
        switch (bilgiler.get(i)) {


            case "Bej":
                data.bej.click();
                System.out.println("data.bej.getAttribute(\"textContent\") = " + data.bej.getAttribute("textContent"));
                System.out.println("data.bej.getText() = " + data.bej.getText());
                break;
            case "Beyaz ":
                data.beyaz.click();
                break;
            case "Çok Renkli ":
                data.cokRenkli.click();
                break;
            case "Gri ":
                data.gri.click();
                break;
            case "Kahverengi ":
                data.kahverengi.click();
                break;
            default:
                break;

        }
        Driver.getDriver().get("https://www.ciceksepeti.com/dogum-gunu-hediyeleri");
            ReusableMethods.bekle(5);
            data.renkler.click();
        }
        Driver.closeDriver();
    }
}
