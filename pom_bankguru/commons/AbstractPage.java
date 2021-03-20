package pom_bankguru.commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pom_bankguru.commons.Constants;


public class AbstractPage {
    /*Web Browser */
    public void openUrl(WebDriver driver, String urlValue){
        driver.get(urlValue);
        driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshToPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
        sleepInSecond(driver, 2);
    }

    public void cancelAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public String getTextFromAlert(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    public void sendkeysToAlert(WebDriver driver, String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    public void waitForAlertPresent(WebDriver driver){
        waitExplicit = new WebDriverWait(driver, longTimeout);
        waitExplicit.until(ExpectedConditions.alertIsPresent());
    }

    /* Web Element */

    public void clickToElement(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void clickToElement(WebDriver driver, String locator, String ... dynamicValue) {
        locator = String.format(locator, (Object[]) dynamicValue);
        element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void sendkeysToElement(WebDriver driver, String locator, String value) {
        element = driver.findElement(By.xpath(locator));
        element.clear();
        element.sendKeys(value);
    }

    public void sendkeysToElement(WebDriver driver, String locator, String senkeyValue, String... dynamicValue) {
        locator = String.format(locator, (Object[]) dynamicValue);
        element = driver.findElement(By.xpath(locator));
        element.clear();
        element.sendKeys(senkeyValue);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String itemText) {
        element = driver.findElement(By.xpath(locator));
        select = new Select(element);
        select.selectByVisibleText(itemText);

    }

    public void selectItemInDropdown(WebDriver driver, String locator, String itemText, String... dynamicValue) {
        locator = String.format(locator, (Object[]) dynamicValue);
        element = driver.findElement(By.xpath(locator));
        select = new Select(element);
        select.selectByVisibleText(itemText);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public void selectItemInCustomDropDown(WebDriver driver, String parentXpath, String allItemXpath,
                                           String expectedValueItem) throws Exception {
        element = driver.findElement(By.xpath(parentXpath));

        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click;", element);
        Thread.sleep(1000);

        waitExplicit = new WebDriverWait(driver, longTimeout);
        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

        elements = driver.findElements(By.xpath(allItemXpath));

        for (WebElement childElement : elements) {
            if (childElement.getText().contains(expectedValueItem)) {
                if (childElement.isDisplayed()) {
                    childElement.click();
                } else {
                    js.executeScript("arguments[0].scrollIntroView(true);", childElement);
                    Thread.sleep(1000);
                    js.executeScript("arguments[0].click();", childElement);
                }
                Thread.sleep(1000);
                break;
            }

        }
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
        element = driver.findElement(By.xpath(locator));
        return element.getAttribute(attributeName);
    }

    public String getTextElement(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.getText();
    }

    public String getTextElement(WebDriver driver, String locator,String... dynamicValue ) {
        locator = String.format(locator, (Object[]) dynamicValue);
        element = driver.findElement(By.xpath(locator));
        return element.getText();
    }

    public int countElementNumber(WebDriver driver, String locator) {
        elements = driver.findElements(By.xpath(locator));
        return elements.size();
    }

    public void checkTheCheckbox(WebDriver driver, String locator){
        element = driver.findElement(By.xpath(locator));
        if (!element.isSelected()){
            element.click();
        }
    }

    public void uncheckTheCheckbox(WebDriver driver, String locator){
        element = driver.findElement(By.xpath(locator));
        if(element.isSelected()){
            element.click();
        }
    }

    public boolean isControlDisplayed(WebDriver driver, String locator){
        element = driver.findElement(By.xpath(locator));
        return element.isDisplayed();
    }

    public boolean isControlDisplayed(WebDriver driver, String locator, String ... dynamicValue){
        locator = String.format(locator, (Object[])dynamicValue);
        element = driver.findElement(By.xpath(locator));
        return element.isDisplayed();
    }

    public boolean isControlUnDisplayed(WebDriver driver, String locator){
        Date date = new Date();
        System.out.println("Start time = "+date.toString());
        overrideGlobalTimeout(driver, Constants.SHORT_TIMEOUT);
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        if(elements.size()==0){
            System.out.println("Element is not in DOM");
            System.out.println("End time = "+ new Date().toString());
            overrideGlobalTimeout(driver, Constants.LONG_TIMEOUT);
            return true;
        } else if (elements.size()>0 && !elements.get(0).isDisplayed()){
            System.out.println("Element is in DOM but not visible/displayed");
            System.out.println("End time = "+ new Date().toString());
            overrideGlobalTimeout(driver, Constants.LONG_TIMEOUT);
            return true;
        } else {
            System.out.println("Element is in DOM and visible");
            overrideGlobalTimeout(driver, Constants.LONG_TIMEOUT);
            return false;

        }

    }

    public boolean isControlSelected(WebDriver driver, String locator){
        element = driver.findElement(By.xpath(locator));
        return element.isSelected();
    }

    public boolean isControlEnabled(WebDriver driver, String locator){
        element = driver.findElement(By.xpath(locator));
        return element.isEnabled();
    }

    public void switchToChildWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String runWindowID : allWindowIDs) {
            if (!runWindowID.equals(parentID)) {
                driver.switchTo().window(runWindowID);
                System.out.println("Child ID: " + runWindowID);
                break;
            }
        }
    }

    public void switchByTitle(WebDriver driver, String titleExpected) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow: allWindows) {
            driver.switchTo().window(runWindow);
            String runTitle = driver.getTitle();
            if(runTitle.equals(titleExpected)) {
                break;
            }
        }
    }

    public boolean closeAllWindowWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String runWindowID: allWindowIDs) {
            if(!runWindowID.equals(parentID)) {
                driver.switchTo().window(runWindowID);
                driver.close();
            }
        }

        driver.switchTo().window(parentID);
        if(driver.getWindowHandles().size()==1)
            return true;
        else
            return false;
    }

    public void doubleClickToElement(WebDriver driver, String locator){
        element = driver.findElement(By.xpath(locator));
        action = new Actions(driver);
        action.doubleClick(element).perform();
    }

    public void hoverMouseToElement(WebDriver driver, String locator){
        element = driver.findElement(By.xpath(locator));
        action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key){
        element = driver.findElement(By.xpath(locator));
        action = new Actions(driver);
        action.sendKeys(element, key).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key, String... dynamicValue){
        locator = String.format(locator, (Object[]) dynamicValue);
        element = driver.findElement(By.xpath(locator));
        action = new Actions(driver);
        action.sendKeys(element, key).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        element = driver.findElement(By.xpath(locator));
        action = new Actions(driver);
        action.contextClick(element).perform();
    }

    public void dragAndDrop(WebDriver driver, String locator){
        WebElement sourceDrag =driver.findElement(By.xpath(locator));
        WebElement targetDrag =driver.findElement(By.xpath(locator));
        action.dragAndDrop(sourceDrag, targetDrag).perform();
    }

    public void uploadFiles(WebDriver driver, String locator) throws Exception {

        for(String file:files) {
            WebElement uploadFiles=driver.findElement(By.xpath(locator));
            uploadFiles.sendKeys(file);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        driver.findElement(By.xpath(locator)).click();
    }

    public void highlightElement(WebDriver driver) {
        js = (JavascriptExecutor) driver;
        String orginalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style","border: 3px solid red; border-style: dashed;");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,"style", orginalStyle);

    }

    public Object executeForBrowser(WebDriver driver, String javaSript) {
        js = (JavascriptExecutor) driver;
        return js.executeScript(javaSript);
    }

    public boolean verifyTextInInnerText(WebDriver driver, String locator, String textExpected) {
        js = (JavascriptExecutor) driver;
        String textActual = (String) js.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        System.out.println("Text actual = " + textActual);
        return textActual.equals(textExpected);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public Object scrollToElement(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        js = (JavascriptExecutor) driver;
        return js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        element = driver.findElement(By.xpath(locator));
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public void scrollToBottomPage(WebDriver driver) {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        element = driver.findElement(By.xpath(locator));
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove,String... dynamicValue ) {
        locator = String.format(locator, (Object[]) dynamicValue);
        element = driver.findElement(By.xpath(locator));
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }
    public Object navigateToUrlByJS(WebDriver driver,String url) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("window.location = '" + url + "'");
    }

    public boolean checkAnyImageLoad(WebDriver driver, String locator){
        element=driver.findElement(By.xpath(locator));
        js = (JavascriptExecutor) driver;
        boolean status = (boolean) js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth>0", element);
        if(status){
            return true;
        }else {
            return false;
        }
    }

    public void waitForElementPresence(WebDriver driver, String locator){
        waitExplicit = new WebDriverWait(driver, longTimeout);
        byLocator = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byLocator));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... dynamicValue){
        locator = String.format(locator, (Object[]) dynamicValue);
        waitExplicit = new WebDriverWait(driver, 30);
        byLocator = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    public void waitForElementInVisible(WebDriver driver, String locator){
        Date date = new Date();
        waitExplicit = new WebDriverWait(driver, Constants.SHORT_TIMEOUT);
        overrideGlobalTimeout(driver, Constants.SHORT_TIMEOUT);
        System.out.println("Start time for wait invisible = "+date.toString());
        byLocator = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
        System.out.println("End time for wait invisible = "+new Date().toString());
        overrideGlobalTimeout(driver, Constants.LONG_TIMEOUT);
    }

    public void clearDataInTextbox(WebDriver driver, String locator){
        element=driver.findElement(By.xpath(locator));
        element.clear();

    }

    public void overrideGlobalTimeout(WebDriver driver, int timeOut){
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }
    public void sleepInSecond(WebDriver driver, long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private WebElement element;
    private List<WebElement> elements;
    private Select select;
    private JavascriptExecutor js;
    private WebDriverWait waitExplicit;
    private Actions action;
    private By byLocator;
    private int longTimeout= Constants.LONG_TIMEOUT;

    private String rootFile = System.getProperty("user.dir");

    private String fileName01="Image 01.jpg";
    private String fileName02="Image 02.jpg";
    private String fileName03="Image 03.jpg";

    private String fileNamePath01 = rootFile + "\\uploadFiles\\" + fileName01;
    private String fileNamePath02 = rootFile + "\\uploadFiles\\" + fileName02;
    private String fileNamePath03 = rootFile + "\\uploadFiles\\" + fileName03;

    private String[] files = {fileNamePath01, fileNamePath02, fileNamePath03};

}
