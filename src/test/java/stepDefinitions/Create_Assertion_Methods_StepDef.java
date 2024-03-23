package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Create_Assertion_Methods_Page;
import utilities.ReusableMethods;
import static utilities.Driver.getDriver;


public class Create_Assertion_Methods_StepDef {
    Create_Assertion_Methods_Page page=new Create_Assertion_Methods_Page();
    String expectedProduct;
    String expectedSize;
    String expectedColor;
    String expectedPrice;
    @Given("magento sitesine gidilir")
    public void magentoSitesineGidilir() {
        getDriver().get("https://magento.softwaretestingboard.com/");
    }

    @And("women menusu ustune gelinir")
    public void womenMenusuUstuneGelinir() {
        ReusableMethods.moveToElement(page.women);

    }

    @And("tops menusu ustune gelinir")
    public void topsMenusuUstuneGelinir() {
        ReusableMethods.moveToElement(page.tops);
    }

    @And("jackets secilir")
    public void jacketsSecilir() {
        page.jackets.click();
    }

    @And("ilk urunde S beden secilir")
    public void ilkUrundeSBedenSecilir() {
        page.products.get(0).click();
        expectedProduct= page.selectedPproduct.getText();

        ReusableMethods.scrollToElementWithWebElement(page.addToCart);

        page.size.click();
         expectedSize= page.size.getText();
         expectedPrice= page.price.getText();
    }

    @And("ilk urunde mavi renk secilir")
    public void ilkUrundeMaviRenkSecilir() {
        page.blue.click();
        expectedColor= page.selectedBlue.getText();
    }

    @And("add to cart butonuna basilir")
    public void addToCartButonunaBasilir() {
        page.addToCart.click();
    }

    @And("sepet tiklanir")
    public void sepetTiklanir() {
        ReusableMethods.scrollTopByJavaScript();
        ReusableMethods.clickJSElementWithJavaScript("document.querySelector(\"body > div.page-wrapper > header > div.header.content > div.minicart-wrapper > a\")");
    }

    @When("see details tiklanir")
    public void seeDetailsTiklanir() {
//       page.seeDetails.click();
    }

    @Then("alinan urun dogrulanir")
    public void alinanUrunDogrulanir() {
        String actualProduct=page.selectedProductTitle.getText();
        System.out.println("expectedProduct = " + expectedProduct);
        System.out.println("actualProduct = " + actualProduct);
        String actualSize=page.basketSize.getText();
        String actualColor=page.basketColor.getText();
        String actualPrice=page.basketPrice.getText();

        page.assertEqualsText(expectedProduct,actualProduct);
        page.assertEqualsText(expectedColor,actualColor);
        page.assertContainsText(expectedSize,actualSize);
        page.assertEqualsText(expectedPrice,actualPrice);
    }



}
