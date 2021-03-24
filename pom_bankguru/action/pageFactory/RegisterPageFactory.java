package pom_bankguru.action.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pom_bankguru.interfaces.RegisterPageUI;

public class RegisterPageFactory {
    WebDriver driver;

    public RegisterPageFactory() {
    }

    public RegisterPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.NAME, using = "emailid")
    private WebElement emailTextBox;

    @FindBy(how = How.NAME, using = "btnLogin")
    private WebElement submitButton;

    @FindBy(how = How.XPATH, using = "//td[text()='User ID :']/following-sibling::td")
    private WebElement userIDTextBox;

    @FindBy(how = How.XPATH, using = "//td[text()='Password :']/following-sibling::td")
    private WebElement passwordTextBox;

    public void inputToEmailTextBox(String email) {
        emailTextBox.sendKeys(email);
    }

    public void clickToSubmitButton() {

        JavascriptExecutor executor = (JavascriptExecutor)driver;

        executor.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        submitButton.click();
    }

    public String getUsernameInformation() {
        return userIDTextBox.getText();
    }

    public String getPasswordInformation() {

        return passwordTextBox.getText();
    }

    public void openLoginPageUrl(String loginPageUrl){
        driver.get(loginPageUrl);

    }
}
