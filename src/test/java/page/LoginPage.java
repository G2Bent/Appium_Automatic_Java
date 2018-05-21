package page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private By userName = By.xpath("//*[@text='請輸入登錄名稱或手機號']");
    private By pwd = By.xpath("//*[@text='請輸入密碼']");
    private By login = By.xpath("//*[@text='登入']");
    private By test = By.xpath("//*[@content-desc='']");

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public void login() {
        sendKeys(userName,"13160691226");
        sendKeys(pwd,"qvzn0123");
        click(login);
    }

    public void user_input(String text){
        sendKeys(userName,text);
    }

    public void password_input(String text){
        sendKeys(pwd,text);
    }

    public void login_btn(){
        click(login);
    }

}
