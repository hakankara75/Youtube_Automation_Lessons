package p1;

import org.junit.Test;
import org.openqa.selenium.By;

import static utilities.TestBase.driver;

public class C01 {
    @Test
    public void test() throws InterruptedException {
//        2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");

        //alttan açılıp kapanan reklamı kapatmak icin shadow root (closed)

        driver.findElement(By.xpath("//div[@class='grippy-host']")).click();
        driver.findElement(By.xpath("//path[@stroke='#FAFAFA']")).click();

    }
}
