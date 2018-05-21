package testcase;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import page.LoginPage;

import java.io.IOException;

public class LoginPageTest extends BaseSetup {

    @Test(description = "测试登录失败")
    public void TestLogin() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login();
        loginPage.findElementWithTimeout(By.xpath("//*[@text='首頁']"));
        assert true;
    }

    @Test(description = "測試登錄")
    public void  TestLogin1() throws IOException{
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.user_input("test2018");
        loginPage.password_input("a12345678");
        loginPage.login_btn();
        loginPage.findElementWithTimeout(By.xpath("//*[@text='首頁']"));
        assert true;
    }
    @Test(description = "项目描述")
    public void bb() {
        assert true;
    }

    @AfterTest
    public void teardown() {
        // calling flush writes everything to the log file
    }
}
