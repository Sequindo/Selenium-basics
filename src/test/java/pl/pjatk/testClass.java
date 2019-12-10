package pl.pjatk;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testClass {
    public String doTest(WebDriver driver, String km, String fuel, String cost)
    {
        driver.findElement(By.xpath(" //*[@name='tripdistance'] ")).clear();
        driver.findElement(By.xpath(" //*[@name='tripdistance'] ")).sendKeys(km);
        driver.findElement(By.xpath(" //*[@name='fuelefficiency'] ")).clear();
        driver.findElement(By.xpath(" //*[@name='fuelefficiency'] ")).sendKeys(fuel);
        driver.findElement(By.xpath(" //*[@name='gasprice'] ")).clear();
        driver.findElement(By.xpath(" //*[@name='gasprice'] ")).sendKeys(cost);
        driver.findElement(By.xpath(" //*[@value='Calculate'] ")).click();
        return(driver.findElement(By.xpath("//*[contains(@class, 'verybigtext')]")).getText());
    }
    @Test
    public void test()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.calculator.net/fuel-cost-calculator.html");
        Assert.assertEquals("This trip will require 22.7 liters of fuel, which amounts to a fuel cost of $29.46.",
                doTest(driver,"412","5.5","1.3"));
        Assert.assertEquals("This trip will require 154 liters of fuel, which amounts to a fuel cost of $223.3.",
                doTest(driver,"2200","7.0","1.45"));
;   }
}
