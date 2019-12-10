package pl.pjatk;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class kalkulatorPodatkowy {
    public String doTest(WebDriver driver, String brutto, String procent)
    {
        driver.get("https://kalkulatory.gofin.pl/Kalkulator-wynagrodzen,12.html");
        try {
            driver.findElement(By.xpath(" //*[@id='formalAgreementModalAgree'] ")).click();
        } catch(NoSuchElementException e) {}
        driver.findElement(By.xpath(" //*[@name='kb'] ")).clear();
        driver.findElement(By.xpath(" //*[@name='kb'] ")).sendKeys(brutto);
        Select zaliczka = new Select(driver.findElement(By.xpath(" //*[@name='s'] ")));
        zaliczka.selectByVisibleText(procent);
        driver.findElement(By.xpath(" //*[@id='przyciskObliczenia'] ")).click();
        WebElement wynik = driver.findElement(By.xpath("//tr[@class='trWynik']/td"));
        return wynik.getText();
    }
    @Test
    public void tests()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Assert.assertEquals(doTest(driver,"12000","18%"),"8.427,87 zł");
        Assert.assertEquals(doTest(driver,"12000","32%"),"6.993,87 zł");
    }
}
