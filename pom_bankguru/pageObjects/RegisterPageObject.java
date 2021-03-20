package pom_bankguru.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pom_bankguru.commons.AbstractPage;
import pom_bankguru.interfaces.LoginPageUI;
import pom_bankguru.interfaces.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {

    WebDriver driver;

    public RegisterPageObject() {
    }

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToEmailTextBox(String email) {
//        waitForElementInVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendkeysToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
    }

    public void clickToSubmitButton() {

        JavascriptExecutor executor = (JavascriptExecutor)driver;

        WebElement element = driver.findElement(By.xpath(RegisterPageUI.SUBMIT_BUTTON));

        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        waitForElementInVisible(driver, RegisterPageUI.SUBMIT_BUTTON);
        clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
    }

    public String getUsernameInformation() {
//        waitForElementInVisible(driver, RegisterPageUI.USERID_TEXTBOX);
        return getTextElement(driver, RegisterPageUI.USERID_TEXTBOX);
    }

    public String getPasswordInformation() {
//        waitForElementInVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
        return getTextElement(driver, RegisterPageUI.PASSWORD_TEXTBOX);
    }

    public void openLoginPageUrl(String loginPageUrl){
        openUrl(driver, loginPageUrl);

    }
}
