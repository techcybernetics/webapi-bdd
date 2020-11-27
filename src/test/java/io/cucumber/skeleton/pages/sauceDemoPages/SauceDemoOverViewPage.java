package io.cucumber.skeleton.pages.sauceDemoPages;


import io.cucumber.skeleton.pages.PageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SauceDemoOverViewPage extends PageUtils {
    WebDriver driver;
    @FindBy(how= How.XPATH,using = "//a[contains(text(),'FINISH')]")
    private WebElement finish;


    public  SauceDemoOverViewPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public SauceDemoOverViewPage getDetails (String field){
        List<WebElement> elm=driver.findElements(By.xpath("//div[@class='summary_info']/div"));
        for(WebElement element:elm){
            if(element.getText().equals(field)){

            }
        }
        return this;
    }
    public SauceDemoOverViewPage clickFinish(){
        finish.click();
        return this;
    }

}
