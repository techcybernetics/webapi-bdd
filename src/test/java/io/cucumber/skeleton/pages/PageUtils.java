package io.cucumber.skeleton.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUtils {

    public void setValue(WebDriver driver, WebElement locator, int timeout, String value){
        new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(locator));
        locator.sendKeys(value);
    }

    public void clickOn(WebDriver driver,WebElement locator,int timeout){
        new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(locator));
        locator.click();
    }

    public String getTextValue(WebDriver driver,WebElement locator,int timeout){
        new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(locator));
        return  locator.getText();

    }


}
