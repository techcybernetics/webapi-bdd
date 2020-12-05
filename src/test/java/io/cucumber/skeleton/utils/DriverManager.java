package io.cucumber.skeleton.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class DriverManager {
    static WebDriver driver;
    public static WebDriver initDriver(String browser,String url){
        if(browser.equalsIgnoreCase("ff")){
          // System.setProperty("webdriver.gecko.driver","src/driver/firefoxDriver/geckodriver.exe");
            FirefoxOptions options=new FirefoxOptions();
            options.setHeadless(false);
            driver=new FirefoxDriver(options);
        }
        else if(browser.equalsIgnoreCase("chrome")){
           // System.setProperty("webdriver.chrome.driver", "src\\driver\\chromeDriver\\chromedriver");
            Map<String,Object> prefs=new HashMap<>();
            prefs.put("download.default_directory","/Users/owner/Desktop/Selenium/workspace/automation/downloads");
            ChromeOptions options=new ChromeOptions();
            options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
            options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setHeadless(false);
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            options.addArguments("--start-fullscreen");
          options.setExperimentalOption("prefs",prefs);


            driver=new ChromeDriver(options);

        }
        else if(browser.equalsIgnoreCase("safari")){
            driver=new SafariDriver();
        }
      // driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    public static void takeScreenShot() throws Exception{

        File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String timestamp=new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());

        File screenshot=new File("snapshot/"+driver.getTitle()+timestamp+".png");
        FileHandler.copy(scrFile,screenshot);


    }


}
