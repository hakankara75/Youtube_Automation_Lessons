package pages;

import p1.N11_Otomotiv;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class C04_Otomotiv {

    @Test
    public void testName() {
        ReusableMethods.extentReport();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        N11_Otomotiv n11Otomotiv=new N11_Otomotiv();

        // "https://www.n11.com/" adresine git
        Driver.getDriver().get(ConfigReader.getProperty("N11Url"));
        ReusableMethods.extentTestInfo("\"https://www.n11.com/\" adresine gidildi");
        ReusableMethods.uyarilariKapat();
        ReusableMethods.extentTestInfo("Uyarilar kapatildi");
        //giris yap
        ReusableMethods.girisYap();
        ReusableMethods.extentTestInfo("giris yapildi");
        ReusableMethods.threadSleep(2);

        //otomotiv menusu ustune git
        ReusableMethods.threadSleep(2);
        ReusableMethods.uyarilariKapat();
        ReusableMethods.moveToElement(n11Otomotiv.otomotiv);
        ReusableMethods.threadSleep(2);
        ReusableMethods.extentTestInfo("otomotiv menusu ustune gidildi");


        //navigasyon menusunu tiklayin
        n11Otomotiv.navigasyon.click();
        ReusableMethods.extentTestInfo("navigasyon menusunu tiklandi");

        try{
            n11Otomotiv.bilgiKapa.click();}
        catch (Exception e){

        }

        //soldaki menuden multimedya tikla
        ReusableMethods.threadSleep(4);
        WebElement konumaOzel= ReusableMethods.webelementJavaScript("document.querySelector(\"#contentListing > div > div.listingHolder > div.productArea > section > div.header > div.filter-place > div.filter-place-right.filter-place-button-box > div:nth-child(1) > label\")");
        konumaOzel.click();
        ReusableMethods.threadSleep(2);
        ReusableMethods.extentTestInfo("konuma ozel checkbox tiklandi");


        //akilli siralama dropdown ac
        n11Otomotiv.akilliSiralama.click();
        ReusableMethods.extentTestInfo("akilli siralama dropdown acildi");

        //yeni urun secenegini sec
       n11Otomotiv.yeniUrun.click();
        ReusableMethods.extentTestInfo("yeni urun tiklandi");
        ReusableMethods.extentTestInfo("sayfa kapatildi");
        ReusableMethods.extentRaporBitir();
        Driver.closeDriver();


    }

    }
