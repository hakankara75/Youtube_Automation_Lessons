package utilities;

import p1.N11_LoginIn;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import pages.N11_Login;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class ReusableMethods {
    //TestBase class'ından Obje oluşturmanın önüne geçilmesi için abstract yapılabilir
    //Orn: TestBase base = new TestBase()
    //Bu class'a extends ettiğimiz test classlarından ulaşabiliriz

    protected static ExtentReports extentReports; //Raporlamayı başlatır
    protected static ExtentHtmlReporter extentHtmlReporter;//Raporu HTML formatında düzenler
    public static ExtentTest extentTest;//Tüm test aşamalarında extentTest objesi ile bilgi ekleriz

    /**
     bu metot ile extent rapor olusturulur
     */
    public static void extentReport() {

        extentReports = new ExtentReports();
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/reports/extentReport_" + tarih + ".html";
        extentHtmlReporter = new ExtentHtmlReporter(dosyaYolu);
        extentReports.attachReporter(extentHtmlReporter);

extentHtmlReporter.config().setAutoCreateRelativePathMedia(true);//resim eklemek icin

        extentReports.setSystemInfo("Browser", "Chrome");
        extentReports.setSystemInfo("Tester", "Hakan");
        extentHtmlReporter.config().setDocumentTitle("Extent Report");
        extentHtmlReporter.config().setReportName("Smoke Test Raporu");
        extentTest = extentReports.createTest("ExtentTest", "Test Raporu");

    }
    /**
     bu metot ile extent rapora bilgi girisi saglanir
     @param message
     */
    public static void extentTestInfo(String message) {
        if (extentTest != null) {
            extentTest.info(message);
        }
    }
    /**
     bu metot ile extent rapor bitirilir
     */
    public static void extentRaporBitir() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }


    /**
     bu metot ile n11 sitesine giris yapilir
     */
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

    /**
     bu metot ile n11 sitesindeki urarilar kapatilir
     */
    public static void uyarilariKapat() {
        N11_Login n11 = new N11_Login();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        int sayac = 0;
        while (sayac < 4) {
            try {
                ExpectedConditions.visibilityOfElementLocated((By) n11.tamam);
                n11.tamam.click();
                ReusableMethods.threadSleep(5);

                WebElement element = ReusableMethods.webelementJavaScript("document.querySelector('#dengage-push-perm-slide > div > div.dn-slide-body > div > button.dn-slide-deny-btn')");
                                ReusableMethods.clickByJavaScript(element);
                ReusableMethods.threadSleep(2);
            } catch (Exception e) {

            }
            sayac++;
        }
    }

    /**
     bu metot ile alert deki metin alinir
     */
    public static void alertText() {
        Driver.getDriver().switchTo().alert().getText();
    }

    //DropDown VisibleText
    /*
        Select select2 = new Select(gun);
        select2.selectByVisibleText("7");
        //ddmVisibleText(gun,"7"); --> Yukarıdaki kullanım yerine sadece method ile handle edebilirim
     */

    /** Bu metot xpath ile alinan locate sendkey gonderir
     * @param xPath buraya elementin xpath locati verilecek
     * @param sendKeys buraya elemente dongerilecek metin girilecek
     */
    public void webElementSendKeys(String xPath, String sendKeys){
        Driver.getDriver().findElement(By.xpath(xPath)).sendKeys(sendKeys);

    }

    /**
     bu metot ile dropdown menude yazan bir text secilir
     @param ddm girilmesi gereken menunun locatidir
     @param secenek dropdown menunde yazan gorun textin string halidir
     */
    public static void ddmVisibleText(WebElement ddm, String secenek) {
        Select select = new Select(ddm);
        select.selectByVisibleText(secenek);
    }

    /**
     bu metot ile dropdown menudeki seceneklerden birisi index ile secilir
     @param ddm girilmesi gereken menunun locatidir
     @param index dropdown menunde yazan index numarasidir
     */
    public static void ddmIndex(WebElement ddm, int index) {
        Select select = new Select(ddm);
        select.selectByIndex(index);
    }
    /**
     bu metot ile dropdown menudeki valued seceneklerinden birisi strin gonderilerek secilir
     @paramType ddm girilmesi gereken menunun locatidir
     @paramType secenek dropdown menunde yazan ve girilmesi gereken value nun string halidir
     */
    public static void ddmValue(WebElement ddm, String secenek) {
        Select select = new Select(ddm);
        select.selectByValue(secenek);
    }

    /**
     bu metot ile acilan ilk pencereye donulur
     @param sayi girilmesi gereken gecilecek pencerenin indexidir
     */
    public static void switchToWindow1(int sayi) {
        List<String> tumWindowHandles = new ArrayList<String>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tumWindowHandles.get(sayi));
    }

    /**
     bu metot ile acilan pencereye gecilir
     @param sayi girilmesi gereken gecilecek pencerenin indexidir
     */
    public static void switchToWindow2(int sayi) {
        Driver.getDriver().switchTo().window(Driver.getDriver().getWindowHandles().toArray()[sayi].toString());
    }

    /**
     * Explicit wait yapar
     bu metot ile bir element gorulene kadar kodlar bekletilir
     @param element girilmesi gereken locate dir
     @param sayi girilmesi gereken saniyedir
     */
    public static void visibleWait(WebElement element, int sayi) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     bu metot ile alert gorulene kadar kodlar bekletilir
     @param sayi girilmesi gereken saniyedir
     */
    public static void alertWait(int sayi) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.alertIsPresent());
    }
    /**
     bu metot ile tum sayfanin screenshot i alinir
     */
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
    /**
     bu metot ile webelementin screenshot i alinir
     @param element girilmesi gereken locate dir
     */
    public static void webElementScreenShoot(WebElement element) {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/webElementScreenshot" + tarih + ".png";
        try {
            FileUtils.copyFile(element.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     bu metot ile mause element ustunde bekletilir
     @param webElement girilmesi gereken locate dir
     */
    public static void moveToElement(WebElement webElement) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(webElement).perform();
    }

    /**
     bu metot ile herhangi bir webelemente JavascriptExecutor kullanarak tiklayabilirim
     @param webElement girilmesi gereken locate dir
     */
    public static void clickByJavaScript(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].click();", webElement);

    }

    /**
     * bu metot elementin ustune JavascriptExecutor ile goturur
     @param webElement girilmesi gereken locate dir
       */
    public static void scrollIntoViewByJavaScript(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();//Casting
        jse.executeScript("arguments[0].scrollIntoView(true);", webElement);

    }

    /**
     * bu metot javascript kodu ile elemente string gonderir(java sendkey() ile ayni)
   @param webElement girilmesi gereken locate dir
     @param string locate gonderilecek olan deger
     */
    public static void sendKeyWithJavaScript(String string, WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();//Casting
        jse.executeScript("arguments[0].value = '"+string+"';", webElement);

    }
    /**
     bu metot javascript kodu ile sayfayi en alta goturur
     */
    public static void scrollEndByJavaScript() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
    bu metot javascript kodu ile sayfayi en yukari goturur
     */
    public static void scrollTopByJavaScript() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
    }

    /**bu metot sayfayi girilen string degerindeki elemente goturur
     * @param str girilmesi gereken elementin locatinin string halidir
     */
    public static void scrollToElementWithString(String str) {
        WebElement bottom = Driver.getDriver().findElement(By.xpath(str));
        Actions actions = new Actions(Driver.getDriver());
        actions.scrollToElement(bottom).perform();

    }

    /**
     * @param element girilmesi gereken locatidir
    bu metot sayfayi girilen elemente goturur
     */
    public static void scrollToElementWithWebElement(WebElement element) {
        WebElement bottom = element;
        Actions actions = new Actions(Driver.getDriver());
        actions.scrollToElement(bottom).perform();
    }

    /**
     * @param sleep girilmesi gereken saniye
     bu metot girilen saniye kadar java kodlarini bekletir
     */
    public static void threadSleep(int sleep) {
        try {
            Thread.sleep(sleep * 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param str degeri expected metin
     * @param atr degeri actual metin
     bu metot expected metinin alertteki actual metini icerdigini dogrulamak icin
     */
    public static void assertTextContainsAssertTrue(String str, String atr) {
        assertTrue(str.contains(atr));
    }

    /**Bu metot bir webelementin secili olup olmadigini dogrular
     *  @param webElement girilecek webelement dir.
     */
    public void assertTrueIsSelected(WebElement webElement){
        Assert.assertTrue(webElement.isSelected());
    }

    /** Bu metot iki string degerin birbirine equal olup olmadigini dogrular
    @param str girilecek 1. metindir
     @param str1 girilecek 2. metindir
     */
    public void assertTrueEquals(String str, String str1){
        Assert.assertTrue(str.equals(str1));
    }

    /**
     bu metot alerti kabul eder
     */
    public static void switchAlertAccept() {
        Driver.getDriver().switchTo().alert().accept();
    }

    /**
         bu metot alerti reddeder
     */
    public static void switchAlertDismiss() {
        Driver.getDriver().switchTo().alert().dismiss();
    }

    /**
     * @param str olarak alerte gonderilecek metin girilmeli
    bu metot girilen metini alerte mesaj olarak gonderir
     */
    public static void switchAlertSendKey(String str) {
        Driver.getDriver().switchTo().alert().sendKeys(str);
    }

    /**
     * @param str olarak xpath locati girilmeli
    bu metot girilen xpath locati ile webelement olusturur
     */
    public static String findByXpathString(String str) {
        String location = Driver.getDriver().findElement(By.xpath(str)).getText();
        return location;
    }

    /** bu metot girilen id locati ile webelement olusturur
     * @param str olarak id locati girilmeli
         */
    public static WebElement findByIdWebelement(String str) {
        WebElement w = Driver.getDriver().findElement(By.id(str));
        return w;
    }

    /**
     bu metot ekrani bir masue tekeri donmesi kadar asagi kaydirir
     */
    public static void pageDown() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    /**
     bu metot ekrani bir masue tekeri donmesi kadar yukari kaydirir
     */
    public static void pageUp() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_UP).perform();
    }

    /**
     bu metot ekrani bir tik asagi kaydirir
     */
    public static void arrowDown() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.ARROW_DOWN).perform();
    }

    /**
   bu metot ekrani bir tik yukari kaydirir
     */
    public static void arrowUp() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.ARROW_UP).perform();
    }

    /** bu metot webelementin gorunur oldugunu dogrulamak icindir
     @param  webElement girilecek olan webelementdir
     */
    public static void assertTrueIsDisplayed(WebElement webElement) {
        assertTrue(webElement.isDisplayed());
    }

    /**
        @param  webElement girilmesi gereken element locati
     @param str gonderilmek istenen metin
     *bu metot ile metin kutusuna sendkeys gonderir
     */
    public static void sendAttributeJavaScript(WebElement webElement, String str) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('value', '" + str + "')", webElement);
    }

    /**
      @param id girilmesi gereken id degeri
      @param attributeName gonderilmesi gereken attribute ismi
      bu metot ile girilen attribute degerleri ile texti alabilirim
     */
       public static void getValueByJavaScript(String id, String attributeName) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String string = js.executeScript("return document.getElementById('" + id + "')." + attributeName + "").toString();
        System.out.println(string);
        //        NOT: document.querySelector("p").value;  -> TAG KULLANILABILIR
//             document.querySelector(".example").value; -> CSS DEGERI KULLANILABILIR
//             document.querySelector("#example").value; -> CSS DEGERI KULLANILABILIR
    }

    /**
     *  JavaScript ile webelement olusturma
     * @param javascriptYolu internet sitesinden sag klik ile JS yolunu kopyala ile alınan metin olacak
     */

    public static WebElement webelementJavaScript(String javascriptYolu) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        WebElement webElement = (WebElement) js.executeScript("return "+javascriptYolu+"");
        return webElement;
    }

    /**
     *  JavaScript ile webelement olusturup isEnabled oldugunu sorgulama
     * @param str internet sitesinden sag klik ile JS yolunu kopyala ile alınan metin olacak
     */
    public static void assertIsEnabled(String str){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        WebElement webElement = (WebElement) js.executeScript("return "+str+"");
        AssertJUnit.assertTrue(webElement.isEnabled());
    }

    /**
     *  JavaScript ile cift klik yapma
     * @param element ile locate verilir
     */
    public static void doubleClick (WebElement element){
        Actions actions=new Actions(Driver.getDriver());
        actions.doubleClick(element).perform();
    }


    /**bu metot search boxa sendkeys gonderir
        * @param webElement girilmesi gereken element dir
     * @param str sendkey ile gonderilmek istenen metindir
     */
    public static void typeWithJavaScript(WebElement webElement, String str) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('value', '" + str + "')", webElement);
    }

    /** Bu metot ile select objesinin indexi ile secim yapilir
     *
     * @param webElement elementin locatidir
     * @param str   secilecek index numarasidir
     */
    public static void selectByIndexWithJavascript(WebElement webElement, String str) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].selectedIndex = "+str+"; arguments[0].dispatchEvent(new Event('change'))", webElement);
    }

    /** Bu metot ile select objesinin value'su ile secim yapilir
     *
     * @param webElement elementin locatidir
     * @param str   gonderilecek value degeridir
     */
    public static void selectByValueWithJavascript(WebElement webElement, String str) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].value = "+str+"; arguments[0].dispatchEvent(new Event('change'))", webElement);
    }

    /**
     * Bu metot ile pencere degistirilir. ikinci pencereye gecilir.
     * @param firstPage parametresine ilk pencerenin handle degeri girilir.
     */

    public void switchToHandle( String firstPage){
        firstPage=Driver.getDriver().getWindowHandle();
        Set<String> pagesHandles=Driver.getDriver().getWindowHandles();
        for (String str: pagesHandles){
            if(!str.equals(firstPage)){
                Driver.getDriver().switchTo().window(str);

            }
        }

    }

    /**
     * Bu metot ile bir elementin tag'ı ve texti verilerek locate alınır
     * @param tag elementin tag'ı
     * @param text elementin textidir
     * @return element locate döndürür
     */
    public WebElement xpathContainsLocateAlma(String tag, String text){
        WebElement element = Driver.getDriver().findElement(By.xpath("//"+tag+"[contains(text(),'"+text+"')]"));
return element;

    }


    /**
     * Bu metot xpath contains ile bir textin locatini alıp geriye text dönderir.
     * @return
     */
    public String xpathContainsTextAlma(String tag, String text){

        WebElement element = Driver.getDriver().findElement(By.xpath("//"+tag+"[contains(text(),'"+text+"')]"));
         text = element.getText();
        return text;
    }

    //Tüm Sayfa ScreenShot parametreli
    public static String tumSayfaResmi(String name) {
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/screenshot/screenshot" +tarih+name+ ".png";
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        try {
            FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tarih;
    }

    /**
     * Bu metot Action class kullanarak bir webelementin ustune gidip bekler
     * @param element yerine webelement'in locate koyulmalidir
     */
    public static void moveToElementWithAction(WebElement element){
        Actions action = new Actions(Driver.getDriver());
        action.moveToElement(element).perform();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int bekle(int saniye){
        try {
            Thread.sleep(saniye*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return saniye;
    }

    /**
     * Click yapilacak elementi belli etmek icin arka plan rengini degistirip yanip yanip sondururerek yerini belli eder.
     * Burada amac tiklama islemini belirgin hale getirmek.
     * @param element click yapilacak element
     * @param driver
     */
    public static void flash(WebElement element,WebDriver driver){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        String elementColor=element.getCssValue("backgroundColor"); //locate alinan yerin  arka plan rengini alir
        for (int i = 0; i < 10; i++) {
            changeColor("rgb(0,0,0)", element, driver); //elemente siyah renk verir rgb kizmi rengi belirtir
            //changeColor("rgb(255,0,0)", element, driver); //kirmizi renk
            //changeColor("rgb(0,255,0)", element, driver); //yesil renk
            changeColor(elementColor, element, driver);
        }
    }

    /**
     * flash metoduna renk degistirme islemini yaptirir. Elementin arka plan renginin parametre olarak atanacagini bildirir.
     * @param color arka plan rengi
     * @param element   arka plan rengi degisecek element
     * @param driver
     */
    public static void changeColor(String color, WebElement element, WebDriver driver){
        JavascriptExecutor js= (JavascriptExecutor) driver; //javascript kodlarini calistirir
        js.executeScript("arguments[0].style.backgroundColor='"+color+"'", element); //elementin renginin degismesini sağlar

        try{
            Thread.sleep(20);
        }catch (Exception e){

        }
    }

    /**
     * Bu metot ile bir dosyayi bilgisayardan secerek yukleme yapilir. Metot dosya yukleme
     * isteyen bir pencere acildiginda kullanilabilir
     * @param filePath bilgisayardan yuklenecek dosya yolu
     */
    public static void robotClassDosyaYukleme(String filePath){
        try{
            ReusableMethods.bekle(3);
            StringSelection stringSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);
            Robot robot = new Robot();
            //pressing ctrl+v
            robot.keyPress(KeyEvent.VK_CONTROL);
            ReusableMethods.bekle(3);
            robot.keyPress(KeyEvent.VK_V);
            ReusableMethods.bekle(3);
            //releasing ctrl+v
            robot.keyRelease(KeyEvent.VK_CONTROL);
            ReusableMethods.bekle(3);
            robot.keyRelease(KeyEvent.VK_V);
            ReusableMethods.bekle(3);
            System.out.println("PASSED");
            //pressing enter
            ReusableMethods.bekle(3);
            robot.keyPress(KeyEvent.VK_ENTER);
            ReusableMethods.bekle(3);
            //releasing enter
            robot.keyRelease(KeyEvent.VK_ENTER);
            ReusableMethods.bekle(3);
            System.out.println("ENTER");
        }catch (Exception e){
        }
    }

    /** Bu metot islem yapilacak elementin etrafina renkli cerceve cizerek belirgin hale getirir.
     *
     * @param locate islem yapilacak elementin cssSelector turunden locate string olarak girilmeli
     */
    public static void showElementWithFrame(String locate){
        WebElement element = Driver.getDriver().findElement(By.cssSelector(""+locate+""));
        String script = "arguments[0].style.border='3px solid red';";
//        String script = "arguments[0].style.border='3px solid white';";
//        String script = "arguments[0].style.border='3px solid yellow';";
//        String script = "arguments[0].style.border='3px solid green';";
        ((JavascriptExecutor) Driver.getDriver()).executeScript(script, element);

    }
}