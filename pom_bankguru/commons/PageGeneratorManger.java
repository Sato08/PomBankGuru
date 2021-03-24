package pom_bankguru.commons;

import org.openqa.selenium.WebDriver;
import pom_bankguru.action.pageGenerator.HomePageObject;
import pom_bankguru.action.pageGenerator.LoginPageObject;
import pom_bankguru.action.pageGenerator.RegisterPageObject;

public class PageGeneratorManger {

    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static RegisterPageObject getRegisterPage(WebDriver driver){
        return new RegisterPageObject(driver);
    }

    public static HomePageObject getHomePage(WebDriver driver){
        return new HomePageObject(driver);
    }
}
