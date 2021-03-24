package pom_bankguru.action.pageGenerator;

import org.openqa.selenium.WebDriver;
import pom_bankguru.commons.AbstractPage;
import pom_bankguru.interfaces.HomePageUI;

public class HomePageObject extends AbstractPage {

    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isWelcomeMessageDisplayed(String expectedText) {
        String actualText = getTextElement(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
        return actualText.contains(expectedText);
    }
    public boolean isUserIDDisplayed(String username) {
        String actualText = getTextElement(driver, HomePageUI.USERID_TEXT);
//        System.out.println(username + " " + actualText);
        return actualText.contains(username);
    }
}
