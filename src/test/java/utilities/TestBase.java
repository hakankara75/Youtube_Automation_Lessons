package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//TestBase class'indan obje olusturmanin onune gecilmesi icin abstract yapilabilir
//Orn: TestBase base=new TestBase();
//Bu class'a extend ettigimiz test classlarinda ulasabiliriz.

 //Extent Report icin: Raporlamayı başlatır
//Extent Report icinÇ Raporu HTML formatında düzenler
//Extent Report icin: Tüm test aşamalarında extentTest objesi ile bilgi ekleriz

public abstract class TestBase {


    protected static WebDriver driver;
    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;
    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //Extent Report icin asagisi
        //----------------------------------------------------------------------------------------
        extentReports = new ExtentReports();
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/reports/extentReport_" + tarih + ".html";
        ExtentSparkReporter extentHtmlReporter = new ExtentSparkReporter("TestOutput/reports/extentReport.html");
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);

        // Rapor bilgileri
        extentReports.setSystemInfo("Browser", "Chrome");
        extentReports.setSystemInfo("Tester", "Hakan");

        // ExtentTest nesnesi oluştur
        extentTest = extentReports.createTest("ExtentTest", "Test Raporu");

    }

    @After
    public void tearDown() throws Exception {
        extentReports.flush(); //Extent Report icin
        Thread.sleep(3000);
        driver.quit();
    }

    public static void threadSleep(int sleep) {
        try {
            Thread.sleep(sleep * 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement findXpathWebelement(String str) {
        WebElement w = driver.findElement(By.xpath(str));
        return w;
    }

    public void asserTextContainsAssertTrue(String str, String atr) {
        assertTrue(str.contains(atr));
    }

    public void switchAlertAccept() {
        driver.switchTo().alert().accept();
    }

    public void switchAlertDismiss() {
        driver.switchTo().alert().dismiss();
    }

    public void switchAlertSendKey(String str) {
        driver.switchTo().alert().sendKeys(str);
    }

    public String findByXpathString(String str) {
        String location = driver.findElement(By.xpath(str)).getText();
        return location;
    }

    public void findByXpathClick(String str) {
        driver.findElement(By.xpath(str)).click();
    }

    public void findByIdClick(String str) {
        driver.findElement(By.id(str)).click();
    }

    public WebElement findByIdWebelement(String str) {
        WebElement w = driver.findElement(By.id(str));
        return w;
    }

    public void pageDown() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    public void pageUp() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_UP).perform();
    }

    public void arrowDown() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).perform();
    }

    public void arrowUp() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_UP).perform();
    }

    public void assertDisplayedWebelement(WebElement a) {
        assertTrue(a.isDisplayed());
    }

    public void scrollToElement(String str) {
        WebElement bottom = driver.findElement(By.xpath(str));
        Actions actions = new Actions(driver);
        actions.scrollToElement(bottom).perform();
        //bu kod locati alinan elemana kadar sayfayi asagi goturur
    }

    public static void tumSayfaScreenShoot() {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/screenshot" + tarih + ".png";
        TakesScreenshot ts = (TakesScreenshot) driver;
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

    public static void switchToWindow(int sayi) {
        List<String> tumWindowHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tumWindowHandles.get(sayi));
    }

    //SwitchToWindow2
    public static void window(int sayi) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[sayi].toString());
    }
    //bu metot ile herhangi bir webelemente JavascriptExecutor kullanarak tiklayabilirim
    public void clickByJavaScript(WebElement webElement){
        JavascriptExecutor jse= (JavascriptExecutor) driver;

        jse.executeScript("arguments[0].click();", webElement);

    }

    public void scrollIntoViewByJavaScript(WebElement webElement){
        JavascriptExecutor jse=(JavascriptExecutor) driver;//Casting
        jse.executeScript("arguments[0].scrollIntoView(true);",webElement);

    }

    //bu metot ile sayfayi en alta kaydirabilirim
    public void scrollEndByJavaScript(){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    //bu metot ile sayfayi en yukari kaydirabilirim
    public void scrollTopByJavaScript(){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
    }

    public void typeWithJavaScript(WebElement webElement, String str){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '"+str+"')", webElement);
    }

    //bu metot ile attribute degerleri ile texti alabilirim
    public String getValueByJavaScript(String id, String attributeName){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        String string= js.executeScript("return document.getElementById('"+id+"')."+attributeName+"").toString();
        System.out.println(string);
        return string;
        //        NOT: document.querySelector("p").value;  -> TAG KULLANILABILIR
//             document.querySelector(".example").value; -> CSS DEGERI KULLANILABILIR
//             document.querySelector("#example").value; -> CSS DEGERI KULLANILABILIR
    }
    public void moveToElement(String str){
        WebElement webElement = driver.findElement(By.xpath(str));
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).perform();


    }
    public void assertTrueIsDisplayed(WebElement webElement){
        Assert.assertTrue(webElement.isDisplayed());
    }

    public void assertTrueEquals(String str, String str1){
        Assert.assertTrue(str.equals(str1));
    }
    public void assertTrueIsSelected(WebElement webElement){
        Assert.assertTrue(webElement.isSelected());
    }

    public void assertTrueIsEnabled(WebElement webElement){
        Assert.assertTrue(webElement.isEnabled());
    }

    public void webElementSendKeys(String xPath, String sendKeys){
        driver.findElement(By.xpath(xPath)).sendKeys(sendKeys);

    }

}

