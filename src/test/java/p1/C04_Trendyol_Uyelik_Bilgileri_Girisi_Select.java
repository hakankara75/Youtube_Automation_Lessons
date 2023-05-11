package p1;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;
import com.github.javafaker.Faker;



public class C04_Trendyol_Uyelik_Bilgileri_Girisi_Select extends TestBase {
    @Test
    public void name() {
        JavascriptExecutor js = (JavascriptExecutor) driver;


        extentTest = extentReports.createTest("ExtentTest", "Trendyol Uyelik Test Raporu");
        //   "https://www.trendyol.com/" sitesine git
        driver.get("https://www.trendyol.com/");
        extentTest.info("Trendyol sayfasına gidildi");
        try {
            driver.findElement(By.id("Rating-Review")).click();
            driver.findElement(By.id("rejectAllButton")).click();
        } catch (Exception e) {

        }

        //Giris menusu ustune git
        moveToElement("//p[text()='Giriş Yap']");
        extentTest.info("Giris menusu ustune gidildi ");

        //menunun acildigini dogrula
        WebElement menu = findXpathWebelement("//div[@class='login-button']");
        assertTrueIsDisplayed(menu);
        menu.click();
        extentTest.info("menunun acildigi dogrulandi");

        //uyelik bilgilerini gir
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("ffffffff@gmail.com");
        webElementSendKeys("//input[@id='login-password-input']", "bbbbbbb");
        threadSleep(3);
        findByXpathClick("//button[@class='q-primary q-fluid q-button-medium q-button submit']");
        extentTest.info("Uyelik bilgileri girildi");

        //supermarket menusu ustune git
        threadSleep(2);
        moveToElement("//a[@href='/butik/liste/16/supermarket']");
        extentTest.info("supermarket menusu ustune gidildi");

        //kedi mamasini tikla
        findByXpathClick("//a[@href='/kedi-mamasi-x-c103588']");
        extentTest.info("kedi mamasi tiklandi");

        //altinci urunden 5 tane sepete ekle
        scrollToElement("(//div[@class='add-to-bs-tx'])[6]");
        for (int i = 1; i <6 ; i++) {
            threadSleep(5);
            findByXpathClick("(//div[@class='add-to-bs-tx'])[6]");
        }

        extentTest.info("altinci urunden 5 tane sepete eklendi");

        //sepete tikla
        scrollTopByJavaScript();
        findByXpathClick("//a[@class='link account-basket']");
        extentTest.info("sepete tiklandi");

        //sepette eklenen urunden 5 tane oldugunu dogrula
        WebElement firsProd=driver.findElement(By.xpath("//input[@value='5']"));
        String getValue= firsProd.getAttribute("value");
        System.out.println(getValue);
        asserTextContainsAssertTrue("5", getValue);
        extentTest.info("isepette eklenen urunden 5 tane oldugunu dogrulandi");

        //sepeti onayla
        findByXpathClick("(//a[@href='/sepetim/odeme'])[1]");

        //yeni adres ekle
        findByXpathClick("(//div[@class='p-add-address-box ty-p-flex-center'])[1]");
        Faker faker=new Faker();
        webElementSendKeys("//input[@name='name']", String.valueOf(faker.name().firstName()));
        webElementSendKeys("//input[@name='surname']", String.valueOf(faker.name().lastName()));
        WebElement phone=findXpathWebelement("//input[@name='phone']");

        Actions actions=new Actions(driver);
        actions.sendKeys(phone, "543321130", Keys.ENTER).perform();

        //il sec
        WebElement dropdownElement = driver.findElement(By.name("cityId"));
        dropdownElement.click();
        WebElement optionElement = driver.findElement(By.xpath("//div[@class='ty-select-option' and text()='Adıyaman']"));
        optionElement.click();
        threadSleep(3);

        //ilce sec
        WebElement ilceElement = driver.findElement(By.name("districtId"));
        ilceElement.click();
        WebElement optionIlce = driver.findElement(By.xpath("//div[@class='ty-select-option' and text()='Besni']"));
        optionIlce.click();
        threadSleep(3);

        //mahalle sec
        WebElement mahelleElement = driver.findElement(By.name("neighborhoodId"));
        mahelleElement.click();
        WebElement optionMahalle = driver.findElement(By.xpath("//div[@class='ty-select-option' and text()='Abımıstık Mah (Çakırhüyük Köyü)']"));
        optionMahalle.click();
        threadSleep(3);


        //adres gir
        WebElement adres=findXpathWebelement("//textarea[@name='addressLine']");

        actions.sendKeys(adres, "sdfgşldjfgsdf", Keys.TAB).sendKeys("Hakan", Keys.TAB).perform();
        findByXpathClick("//button[@type='submit']");

        //Tamam butonuna bas
        findByXpathClick("//div[@class='otp-success-button']");

        //Kaydet ve Devam Et butonuna bas
        String odemeSayfasi=driver.getWindowHandle();
        findByXpathClick("(//button[@class='ty-primary-btn ty-btn-large'])[1]");

        //bir onceki sayfaya don
        driver.navigate().back();

        //dondugun sayfanin sepetim/odeme sayfasi olmadinigini dogrula
        String sepet=driver.getWindowHandle();
        assertTrueEquals(odemeSayfasi,sepet);

        //kapat
}
}