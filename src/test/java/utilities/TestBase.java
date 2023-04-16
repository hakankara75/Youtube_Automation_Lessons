package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class TestBase {
    //TestBase class'indan obje olusturmanin onune gecilmesi icin abstract yapilabilir
//Orn: TestBase base=new TestBase();
//Bu class'a extend ettigimiz test classlarinda ulasabiliriz.
    public static WebDriver driver;

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void tearDown() throws Exception {
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

    public WebElement findXpathWebelement(String str){
        WebElement w= driver.findElement(By.xpath(str));
        return w;
    }

    public void asserTextContainsAssertTrue(String str, String atr){
        assertTrue(str.contains(atr));
    }

    public void switchAlertAccept(){
        driver.switchTo().alert().accept();
    }

    public void switchAlertDismiss(){
        driver.switchTo().alert().dismiss();
    }

    public void switchAlertSendKey(String str){
        driver.switchTo().alert().sendKeys(str);
    }

    public String findByXpathString(String str){
        String location= driver.findElement(By.xpath(str)).getText();
        return location;
    }

    public void findByXpathClick(String str){
        driver.findElement(By.xpath(str)).click();
    }

    public void pageDown(){
        Actions actions=new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    public void pageUp(){
        Actions actions=new Actions(driver);
        actions.sendKeys(Keys.PAGE_UP).perform();
    }

    public void arrowDown(){
        Actions actions=new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).perform();
    }

    public void arrowUp(){
        Actions actions=new Actions(driver);
        actions.sendKeys(Keys.ARROW_UP).perform();
    }

    public void assertDisplayedWebelement(WebElement a){
        assertTrue(a.isDisplayed());
    }

    public void scrollToElement(String str){
        WebElement bottom = driver.findElement(By.xpath(str));
        Actions actions=new Actions(driver);
        actions.scrollToElement(bottom).perform();
        //bu kod locati alinan elemana kadar sayfayi asagi goturur
    }
}