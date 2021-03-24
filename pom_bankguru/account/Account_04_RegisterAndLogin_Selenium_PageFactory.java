package pom_bankguru.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pom_bankguru.action.pageFactory.HomePageFactory;
import pom_bankguru.action.pageFactory.LoginPageFactory;
import pom_bankguru.action.pageFactory.RegisterPageFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Account_04_RegisterAndLogin_Selenium_PageFactory {
    /*Link tham khảo: https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html */

    WebDriver driver;
    String email, username, password, loginPageUrl;

    LoginPageFactory loginPageFactory;
    RegisterPageFactory registerPageFactory;
    HomePageFactory homePageFactory;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");

        driver = new ChromeDriver();
        email = "automation10" + randomDataTest() + "@gmail.com";

        loginPageFactory = new LoginPageFactory(driver);
        registerPageFactory = new RegisterPageFactory(driver);
        homePageFactory = new HomePageFactory(driver);

        //Selenium API: Xử lí triệt để theo PO trong bài sau
        System.out.println("PRE-CONFITION - STEP: 1. Open BankGuru Application");
        driver.get("http://demo.guru99.com/v4/");

        System.out.println("PRE-CONFITION - STEP: 2. Get Login Page Url");
        loginPageUrl = loginPageFactory.getLoginPageUrl();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().fullscreen();

    }

    @Test
    public void TC_01_RegisterToSystem(){
        System.out.println("REGISTER-STEP: 1.Click to Here link");
        loginPageFactory.clickToHereLink();

        System.out.println("REGISTER-STEP: 2.Input to Email ID Textbox");
        registerPageFactory.inputToEmailTextBox(email);

        System.out.println("REGISTER-STEP: 3.Click to SUBMIT button");
        registerPageFactory.clickToSubmitButton();

        System.out.println("REGISTER-STEP: 4.Get Username/Password infor");
        username = registerPageFactory.getUsernameInformation();
        password = registerPageFactory.getPasswordInformation();

    }

    public void TC_02_LoginToSystem(){
        System.out.println("LOGIN -STEP:1.Open Login Page");
        registerPageFactory.openLoginPageUrl(loginPageUrl);

        System.out.println("LOGIN -STEP:2.Input to UserID/Password textbox");
        loginPageFactory.inputToUserIDTextbox(username);
        loginPageFactory.inputToPasswordTextbox(password);

        System.out.println("LOGIN -STEP:3.Click to LOGIN button");
        loginPageFactory.clickToLoginButton();


        System.out.println("LOGIN -STEP:4.Verify Welcome Message displayes");
        Assert.assertTrue(homePageFactory.isWelcomeMessageDisplayed("Wellcome To Manager's Page"));

        System.out.println("LOGIN -STEP:5.Verify UserID dispalyed");
        Assert.assertTrue(homePageFactory.isUserIDDíplayed(username));


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
