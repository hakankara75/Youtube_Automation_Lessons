package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.util.concurrent.TimeUnit;

public class Driver {
    /*
        Driver class'ındaki temel mantık extends yöntemiyle değil yani ReusableMethods class'ına extent etmek yerine
    Driver class'ından static methodlar kullanarak driver oluştururuz. Static olduğu için class ismi ile
    her yerden methoda ulaşabileceğiz.
     */
    /*
    Singleton Pattern: Tekli kullanım kalıbı.
        Bir class'tan obje oluşturulmasının önüne geçilmesi için kullanılan ifade
        Bir class'tan obje oluşturmanın önüne geçmek için default constructor'ın kullanımını engellemek için
    private access modifire kullanarak bir constructor oluştururuz
     */
    private Driver(){

    }
    static WebDriver driver;

    public static WebDriver getDriver() {
        /*
            Driver'i her çağırdığında yeni bir pencere açılmasının önüne geçmek için
        if bloğu içinde Eğer driver'a değer atanmamışsa(driver doluysa) değer ata, Eğer değer atanmışsa Driver'i aynı
        sayfada RETURN et. Bunun sadece yapmamız gereken if(driver==null) kullanmak
         */
        if (driver == null) {
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":

                    //asagideki 3 satirdaki kodlar testleri Headless (Jenkins gibi) kosmak istedigimiz yerlerde aktive edilebilir
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless"); // Başsız modu etkinleştir
                    options.addArguments("--disable-gpu"); // GPU kullanımını devre dışı bırak
                    // driver = new ChromeDriver(options); bu satir yorumdan kalkarsa, alt satir yoruma alinirsa headless calisir.
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
                    break;
                case "edge":

                    driver = new EdgeDriver(new EdgeOptions().addArguments("--remote-allow-origins=*"));
                    break;
                case "chrome-headless":

                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    //bu secenekte chrome acilmadan test kosulur
                    break;
                default:

                    driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {//Driver'a değer atanmışşsa
            driver.close();
            driver = null;
        }
    }

    public static void quitDriver() {
        if (driver != null) {//Driver'a değer atanmışşsa
            driver.quit();
            driver = null;
        }
    }
}

