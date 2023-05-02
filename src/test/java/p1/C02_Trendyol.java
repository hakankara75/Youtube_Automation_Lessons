package p1;

import org.junit.Test;
import org.openqa.selenium.By;
import utilities.TestBase;

public class C02_Trendyol extends TestBase {
    @Test
    public void name() throws InterruptedException {
        driver.get("https://www.trendyol.com/uyelik?cb=https%3A%2F%2Fwww.trendyol.com%2F");
driver.findElement(By.xpath("(//div[@class='ty-mgr-2 ty-relative ty-checkbox-container'])[2]")).click();
Thread.sleep(3000);




    }
}
