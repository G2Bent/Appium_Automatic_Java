package page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private final AndroidDriver driver;

    public BasePage(AndroidDriver driver){
        this.driver =  driver;
    }

    /**
     * 默認等待10秒
     * @param by
     * @return
     */
    public WebElement findElementWithTimeout(By by){
        WebElement webElement = null;
        try {
            webElement = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (TimeoutException e){
            throw new TimeoutException("查找超時: "+ by.toString());
        }catch(NoSuchElementException e){
            throw new NoSuchElementException("找不到元素:"+by.toString());
        }
        return webElement;
    }

    /**
     *
     * @param by
     * @param timeout 單位：秒
     * @return
     */
    public WebElement findElementWithTimeout(By by,long timeout){
        WebElement webElement = null;
        try {
            webElement = new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (TimeoutException e){
            throw new TimeoutException("没有找到元素: "+ by.toString());
        }

        return webElement;
    }

    public void click(By by){
        WebElement webElement = findElementWithTimeout(by);
        webElement.click();
    }

    public void sendKeys(By by,String text){
        WebElement webElement = findElementWithTimeout(by);
        webElement.sendKeys(text);
        if(driver.isKeyboardShown()){
            driver.hideKeyboard();
        }
    }
}
