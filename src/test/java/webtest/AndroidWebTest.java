package webtest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidWebTest {
    ChromeOptions chromeOptions;
    AndroidDriver driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    String testName = "Testing Website on Android Chrome with Java";
    String accessKey = System.getenv("ACCESS_KEY");
    String cloudURL = System.getenv("CLOUD_URL");

    @Before
    public void setUp() throws MalformedURLException {
        dc.setCapability("testName", testName);
        dc.setCapability("accessKey",accessKey);
        dc.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
        dc.setBrowserName(MobileBrowserType.CHROMIUM);
        driver = new AndroidDriver(new URL(cloudURL + "/wd/hub"),dc);
    }

    @Test
    public void testYourAndroidApp() throws InterruptedException {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://demo-bank.ct.digital.ai");
        driver.findElement(By.xpath("//*[@data-auto='username']//input")).sendKeys("company");
        driver.findElement(By.xpath("//*[@data-auto='password']//input")).sendKeys("company");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@data-auto='login']")).click();
        driver.findElement(By.xpath("//*[@data-auto='transfer-funds']")).click();
        driver.findElement(By.xpath("//input[@name='NAME']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@name='PHONE']")).sendKeys("0541234567");
        driver.findElement(By.xpath("//input[@name='AMOUNT']")).sendKeys("1000");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@data-auto='country']")).click();
        driver.findElement(By.xpath("//*[text()='India']")).click();
        driver.findElement(By.xpath("//*[@data-auto='transfer-button']")).click();
        driver.findElementByXPath("(//*[@text='Logout'])[1]").click();
    }

    @After
    public void tearDown() {
        if (driver != null)
        {
            driver.quit();
            System.out.println("Report URL : " + driver.getCapabilities().getCapability("digitalai:reportUrl"));

        }
    }

}
