package p1;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.sql.Driver;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class C05_JavaScript_Locate extends TestBase {
    @Test
    public void name() throws InterruptedException {
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

        //giris yap linkini tikla
        WebElement elmn = (WebElement) js.executeScript("return document.querySelector(\"#account-navigation-container > div > div.account-nav-item.user-login-container > div.new-login-dropdown > div > div.login-button\")");
        js.executeScript("arguments[0].click();", elmn);
        extentTest.info("giris yap linki tiklandi");

        //uyelik bilgilerini gir
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("hakanbatirhan@gmail.com");
        webElementSendKeys("//input[@id='login-password-input']", ",533Burak");
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


        //Proplan marka mamayi sec
        Thread.sleep(3000);
        WebElement proplan = (WebElement) js.executeScript("return document.querySelector(\"#sticky-aggregations > div > div:nth-child(2) > div.fltrs > div > div > div:nth-child(1) > div > a\")");
        js.executeScript("arguments[0].click();", proplan);

        //Proplan marka mamayi secildigini dogrula
//        Boolean isChecked = (Boolean) js.executeScript("return arguments[0].checked;", proplan);
//        assertTrue(isChecked);

        //en alt menude vivense markasina git
        WebElement element = (WebElement) js.executeScript("return document.querySelector(\"#popular-brand-category-container > div > div > div.popularBrands__wrapper__brands > ul > li:nth-child(3) > a\")");
        js.executeScript("arguments[0].scrollIntoView();", element);

        //"Bellona" linkinin gorundugunu dogrula
        WebElement check = (WebElement) js.executeScript("return document.querySelector(\"#popular-brand-category-container > div > div > div.popularBrands__wrapper__brands > ul > li:nth-child(8) > a\")");
        Boolean isVisible = (Boolean) js.executeScript("return arguments[0].offsetParent !== null;", check);
        assertTrue(isVisible);

        //sayfanin ustune gel
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

        driver.close();



    }
}