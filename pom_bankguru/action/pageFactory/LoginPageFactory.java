package pom_bankguru.action.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pom_bankguru.interfaces.LoginPageUI;

public class LoginPageFactory {
    WebDriver driver;

    public LoginPageFactory() {
    }

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//input[@name='uid']")
    private WebElement userIDTextBox;

    @FindBy(how = How.NAME, using = "password")
    private WebElement passwordTextBox;

    @FindBy(how = How.NAME, using = "btnLogin")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//a[text()= 'here']")
    private WebElement hereLink;

    public String getLoginPageUrl() {
        return driver.getCurrentUrl();
    }

    public void clickToHereLink(){


        JavascriptExecutor executor = (JavascriptExecutor)driver;

        executor.executeScript("arguments[0].scrollIntoView(true);", hereLink);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hereLink.click();
    }

    public void inputToUserIDTextbox(String username) {
        userIDTextBox.sendKeys(username);
    }

    public void inputToPasswordTextbox(String password) {
        passwordTextBox.sendKeys(password);
    }

    public void clickToLoginButton() {
        loginButton.click();

    }

}
