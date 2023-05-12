package utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.N11_Login;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class ReusableMethods {
    //TestBase class'ından Obje oluşturmanın önüne geçilmesi için abstract yapılabilir
    //Orn: TestBase base = new TestBase()
    //Bu class'a extends ettiğimiz test classlarından ulaşabiliriz

    protected static ExtentReports extentReports; //Raporlamayı başlatır
    protected static ExtentHtmlReporter extentHtmlReporter;//Raporu HTML formatında düzenler
    public static ExtentTest extentTest;//Tüm test aşamalarında extentTest objesi ile bilgi ekleriz

    public static void extentReport() {

        extentReports = new ExtentReports();
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/reports/extentReport_" + tarih + ".html";
        extentHtmlReporter = new ExtentHtmlReporter(dosyaYolu);
        extentReports.attachReporter(extentHtmlReporter);


        extentReports.setSystemInfo("Browser", "Chrome");
        extentReports.setSystemInfo("Tester", "Hakan");
        extentHtmlReporter.config().setDocumentTitle("Extent Report");
        extentHtmlReporter.config().setReportName("Smoke Test Raporu");
        extentTest = extentReports.createTest("ExtentTest", "Test Raporu");

    }

    public static void extentTestInfo(String message) {
        if (extentTest != null) {
            extentTest.info(message);
        }
    }

    public static void extentRaporBitir() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }


    //HARD WAIT METHOD
    public static void bekle(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //siteye giris yap

    public static void girisYap() {
        N11_Login n11 = new N11_Login();
        WebElement girisYapLinki = Driver.getDriver().findElement(By.xpath("//a[@title='Giriş Yap']"));
        JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();
        executor.executeScript("arguments[0].click();", girisYapLinki);

        n11.emailGiris.sendKeys(ConfigReader.getProperty("email"), Keys.TAB);
        n11.sifreGiris.sendKeys(ConfigReader.getProperty("password"), Keys.TAB);
        ReusableMethods.arrowDown();

        girisYapLinki = Driver.getDriver().findElement(By.xpath("//div[@id='loginButton']"));
        executor = (JavascriptExecutor) Driver.getDriver();
        executor.executeScript("arguments[0].click();", girisYapLinki);
    }
    //uyarilari kapat

    public static void uyarilariKapat() {
        N11_Login n11 = new N11_Login();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        int sayac = 0;
        while (sayac < 4) {
            try {
                ExpectedConditions.visibilityOfElementLocated((By) n11.tamam);
                n11.tamam.click();
                ReusableMethods.bekle(4);

//WebElement marka = (WebElement) js.executeScript("return document.querySelector(/'#dengage-push-perm-slide > div > div.dn-slide-body > div > button.dn-slide-deny-btn/')");
//clickByJavaScript(marka);
                ExpectedConditions.visibilityOfElementLocated((By) n11.dahaSonra);
                n11.dahaSonra.click();
                ReusableMethods.bekle(2);
            } catch (Exception e) {

            }
            sayac++;
        }
    }

    //Alert ACCEPT
    public static void alertAccept() {
        Driver.getDriver().switchTo().alert().accept();
    }

    //Alert DISMISS
    public static void alertDismiss() {
        Driver.getDriver().switchTo().alert().dismiss();
    }

    //Alert getText()
    public static void alertText() {
        Driver.getDriver().switchTo().alert().getText();
    }

    //Alert promptBox
    public static void alertprompt(String text) {
        Driver.getDriver().switchTo().alert().sendKeys(text);
    }

    //DropDown VisibleText
    /*
        Select select2 = new Select(gun);
        select2.selectByVisibleText("7");
        //ddmVisibleText(gun,"7"); --> Yukarıdaki kullanım yerine sadece method ile handle edebilirim
     */
    public static void ddmVisibleText(WebElement ddm, String secenek) {
        Select select = new Select(ddm);
        select.selectByVisibleText(secenek);
    }

    //DropDown Index
    public static void ddmIndex(WebElement ddm, int index) {
        Select select = new Select(ddm);
        select.selectByIndex(index);
    }

    //DropDown Value
    public static void ddmValue(WebElement ddm, String secenek) {
        Select select = new Select(ddm);
        select.selectByValue(secenek);
    }

    //SwitchToWindow1
    public static void switchToWindow1(int sayi) {
        List<String> tumWindowHandles = new ArrayList<String>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tumWindowHandles.get(sayi));
    }

    //SwitchToWindow2
    public static void switchToWindow2(int sayi) {
        Driver.getDriver().switchTo().window(Driver.getDriver().getWindowHandles().toArray()[sayi].toString());
    }

    //EXPLICIT WAIT METHODS
    //Visible Wait
    public static void visibleWait(WebElement element, int sayi) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //VisibleElementLocator Wait
    public static WebElement visibleWait(By locator, int sayi) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Alert Wait
    public static void alertWait(int sayi) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    //Tüm Sayfa ScreenShot
    public static void tumSayfaScreenShoot() {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/screenshot" + tarih + ".png";
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        try {
            FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //WebElement ScreenShot
    public static void webElementScreenShoot(WebElement element) {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/webElementScreenshot" + tarih + ".png";
        try {
            FileUtils.copyFile(element.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //mause element ustunde bekletilir
    public static void moveToElement(WebElement webElement) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(webElement).perform();
    }

    //bu metot ile herhangi bir webelemente JavascriptExecutor kullanarak tiklayabilirim
    public static void clickByJavaScript(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", webElement);

    }

    //elementin ustune JavascriptExecutor ile goturur
    public static void scrollIntoViewByJavaScript(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();//Casting
        jse.executeScript("arguments[0].scrollIntoView(true);", webElement);

    }

    //bu metot JavascriptExecutor ile sayfayi en alta kaydirabilirim
    public static void scrollEndByJavaScript() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    //bu metot JavascriptExecutor ile sayfayi en yukari kaydirabilirim
    public static void scrollTopByJavaScript() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
    }

    //bu kod locati alinan elemana kadar sayfayi asagi goturur
    public static void scrollToElementWithString(String str) {
        WebElement bottom = Driver.getDriver().findElement(By.xpath(str));
        Actions actions = new Actions(Driver.getDriver());
        actions.scrollToElement(bottom).perform();

    }

    //bu kod locati alinan elemana kadar sayfayi asagi goturur
    public static void scrollToElementWithWebElement(WebElement element) {
        WebElement bottom = element;
        Actions actions = new Actions(Driver.getDriver());
        actions.scrollToElement(bottom).perform();

    }

    public static void threadSleep(int sleep) {
        try {
            Thread.sleep(sleep * 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void assertTextContainsAssertTrue(String str, String atr) {
        assertTrue(str.contains(atr));
    }

    public static void switchAlertAccept() {
        Driver.getDriver().switchTo().alert().accept();
    }

    public static void switchAlertDismiss() {
        Driver.getDriver().switchTo().alert().dismiss();
    }

    public static void switchAlertSendKey(String str) {
        Driver.getDriver().switchTo().alert().sendKeys(str);
    }

    public static String findByXpathString(String str) {
        String location = Driver.getDriver().findElement(By.xpath(str)).getText();
        return location;
    }


    public static WebElement findByIdWebelement(String str) {
        WebElement w = Driver.getDriver().findElement(By.id(str));
        return w;
    }

    public static void pageDown() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    public static void pageUp() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_UP).perform();
    }

    public static void arrowDown() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.ARROW_DOWN).perform();
    }

    public static void arrowUp() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.ARROW_UP).perform();
    }


    public static void assertDisplayedWebelement(WebElement a) {
        assertTrue(a.isDisplayed());
    }

    //search boxa sendkeys gonderir
    public static void typeWithJavaScript(WebElement webElement, String str) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('value', '" + str + "')", webElement);
    }

    //bu metot ile attribute degerleri ile texti alabilirim
    public static void getValueByJavaScript(String id, String attributeName) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String string = js.executeScript("return document.getElementById('" + id + "')." + attributeName + "").toString();
        System.out.println(string);
        //        NOT: document.querySelector("p").value;  -> TAG KULLANILABILIR
//             document.querySelector(".example").value; -> CSS DEGERI KULLANILABILIR
//             document.querySelector("#example").value; -> CSS DEGERI KULLANILABILIR
    }

}