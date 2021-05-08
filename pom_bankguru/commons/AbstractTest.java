package pom_bankguru.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AbstractTest {

    private WebDriver driver;

    public WebDriver openMultiBrower(String browerName){
        String rootFoder = System.getProperty("use.dir");

        if(browerName.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }
        else if(browerName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(browerName.equalsIgnoreCase("chromeheadless")){
            System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window_size = 256x144");
            driver = new ChromeDriver(options);
        }
        else{
            System.out.println("Please choose your brower in TestNG in xml");
        }
        return driver;
    }
}
