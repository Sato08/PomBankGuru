package pom_bankguru.action.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pom_bankguru.commons.AbstractPage;
import pom_bankguru.interfaces.LoginPageUI;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;

    public LoginPageObject() {
    }

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getLoginPageUrl() {
        return getCurrentUrl(driver);
    }

    public void clickToHereLink(){

//        WebDriverWait waitExplicit = new WebDriverWait(driver, 30);
//        By byLocator = By.xpath(LoginPageUI.HERE_LINK);
//        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));

        JavascriptExecutor executor = (JavascriptExecutor)driver;

        WebElement element = driver.findElement(By.xpath(LoginPageUI.HERE_LINK));

        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        waitForElementInVisible(driver, LoginPageUI.HERE_LINK);
        clickToElement(driver, LoginPageUI.HERE_LINK);
    }

    public void inputToUserIDTextbox(String username) {

//        waitForElementInVisible(driver, LoginPageUI.USER_ID_TEXTBOX);
        sendkeysToElement(driver, LoginPageUI.USER_ID_TEXTBOX, username);
    }

    public void inputToPasswordTextbox(String password) {
//        waitForElementInVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickToLoginButton() {
        waitForElementInVisible(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}
