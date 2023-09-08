package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class Driver {
    /*
       Driver class'ındaki temel mantık extends yöntemiyle değil yani TestBase class'ına extent etmek yerine
   Driver class'ından static methodlar kullanarak driver oluştururuz. Static olduğu için class ismi ile
   her yerden methoda ulaşabileceğiz.
    */
    /*
        Singleton Pattern: Tekli kullanım kalıbı.
            Bir class'tan obje oluşturulmasının önüne geçilmesi için kullanılan ifade
            Bir class'tan obje oluşturmanın önüne geçmek için default constructor'ın kullanımını engellemek için
        private access modifire kullanarak bir constructor oluştururuz
         */
    private Driver() {

    }

    private static WebDriver driver;

    public static WebDriver getDriver() {
         /*
            Driver'i her çağırdığında yeni bir pencere açılmasının önüne geçmek için
        if bloğu içinde Eğer driver'a değer atanmamışsa(driver doluysa) değer ata, Eğer değer atanmışsa Driver'i aynı
        sayfada RETURN et. Bunun sadece yapmamız gereken if(driver==null) kullanmak
         */
        if (driver == null) {

            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions().addArguments("--remote-allow-origins=*"));
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(new EdgeOptions().addArguments("--remote-allow-origins=*"));
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    //bu secenekte chrome acilmadan test kosulur
                    break;
                default:
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();


            }

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {//if driver is pointing anywhere
            driver.quit();//quit when I call closeDriver
            driver = null;//make the driver null so when we call getDriver, we can open the driver again
        }
    }

}