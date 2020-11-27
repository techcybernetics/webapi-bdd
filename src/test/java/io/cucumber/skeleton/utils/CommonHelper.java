package io.cucumber.skeleton.utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class CommonHelper {
    public WebDriver driver=null;
    public void quitDriver(Scenario scenario){

        if(scenario.isFailed()){

            try{
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                File screenshot_with_scenario_name = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileHandler.copy(screenshot_with_scenario_name,
                        new File("./target/" + scenario.getName() + ".png"));
                System.out.println(scenario.getName());
                scenario.attach(screenshot, "image/png", "snap");
            }

            catch (WebDriverException | IOException somePlatformsDontSupportScreenshots){
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }


        driver.close();
    }

}
