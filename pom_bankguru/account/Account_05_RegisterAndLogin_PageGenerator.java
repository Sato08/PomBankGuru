package pom_bankguru.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pom_bankguru.action.pageGenerator.HomePageObject;
import pom_bankguru.action.pageGenerator.LoginPageObject;
import pom_bankguru.action.pageGenerator.RegisterPageObject;
import pom_bankguru.commons.PageGeneratorManger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Account_05_RegisterAndLogin_PageGenerator {

    WebDriver driver;
    String email, username, password, loginPageUrl;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    HomePageObject homePage;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");

        driver = new ChromeDriver();
        email = "automation10" + randomDataTest() + "@gmail.com";

        //Selenium API: Xử lí triệt để theo PO trong bài sau
        System.out.println("PRE-CONFITION - STEP: 1. Open BankGuru Application");
        driver.get("http://demo.guru99.com/v4/");

        //Sau khi mở URL => Khởi tạo Login Page
        loginPage = PageGeneratorManger.getLoginPage(driver);

        System.out.println("PRE-CONFITION - STEP: 2. Get Login Page Url");
        loginPageUrl = loginPage.getLoginPageUrl();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().fullscreen();

    }

    @Test
    public void TC_01_RegisterToSystem(){
        System.out.println("REGISTER-STEP: 1.Click to Here link");
        registerPage = loginPage.clickToHereLink();

        //Sau khi khởi tạo Here link => Khỏi tạo Register Page
        // registerPage = new RegisterPageObject(driver);

        System.out.println("REGISTER-STEP: 2.Input to Email ID Textbox");
        registerPage.inputToEmailTextBox(email);

        System.out.println("REGISTER-STEP: 3.Click to SUBMIT button");
        registerPage.clickToSubmitButton();

        System.out.println("REGISTER-STEP: 4.Get Username/Password infor");
        username = registerPage.getUsernameInformation();
        password = registerPage.getPasswordInformation();

    }

    @Test
    public void TC_02_LoginToSystem(){
        System.out.println("LOGIN -STEP:1.Open Login Page");
        loginPage = registerPage.openLoginPageUrl(loginPageUrl);

        System.out.println("LOGIN -STEP:2.Input to UserID/Password textbox");
        loginPage.inputToUserIDTextbox(username);
        loginPage.inputToPasswordTextbox(password);

        System.out.println("LOGIN -STEP:3.Click to LOGIN button");
        homePage = loginPage.clickToLoginButton();


        System.out.println("LOGIN -STEP:4.Verify Welcome Message displayes");
        Assert.assertTrue(homePage.isWelcomeMessageDisplayed("Wellcome To Manager's Page"));

        System.out.println("LOGIN -STEP:5.Verify UserID dispalyed");
        Assert.assertTrue(homePage.isUserIDDisplayed(username));

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    public int randomDataTest(){
        Random random = new Random();
        return random.nextInt(9999);
    }
}
