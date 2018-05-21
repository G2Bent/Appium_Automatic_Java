package testcase;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import report.ExtentTestNGIReporterListener;

import java.net.MalformedURLException;
import java.net.URL;
@Listeners(ExtentTestNGIReporterListener.class)
public class BaseSetup {

    private AndroidDriver driver;

    AndroidDriver getDriver(){
        return driver;
    }
    @BeforeClass
    public void setUpClass(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "aaa");
//        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.cifutech");
        capabilities.setCapability("appActivity", ".MainActivity");
        capabilities.setCapability("noReset", true);
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDownClass() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}

//com.LOL.cifutech
