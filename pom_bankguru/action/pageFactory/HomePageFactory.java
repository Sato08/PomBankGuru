package pom_bankguru.action.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pom_bankguru.interfaces.HomePageUI;

public class HomePageFactory {
    WebDriver driver;

    public HomePageFactory() {
    }

    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//marquee[@class='heading3']")
    private WebElement welcomeMessageText;

    @FindBy(how = How.XPATH, using = "//tr[@class='heading3']/child::td")
    private WebElement userIDText;

    public boolean isWelcomeMessageDisplayed(String expectedText) {
        String actualText = welcomeMessageText.getText();
        return actualText.contains(expectedText);
    }
    public boolean isUserIDDíplayed(String username) {
        String actualText = userIDText.getText();
        return actualText.contains(username);
    }
}
