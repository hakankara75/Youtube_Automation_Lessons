package p1;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

public class C02_N11_Texte_Dinamik_Locate_Alma  extends TestBase {
    @Test
    public void test() {
        driver.get("https://www.n11.com");
        WebElement aramaKutusu= driver.findElement(By.xpath("//input[@id='searchData']"));
        aramaKutusu.sendKeys("Fındık Kreması", Keys.ENTER);
        int sonuc= Integer.parseInt(driver.findElement(By.xpath("(//strong[text()=.])[2]")).getText());
        System.out.println(sonuc);



    }
}
