package io.cucumber.skeleton.pages.sauceDemoPages;


import io.cucumber.skeleton.pages.PageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SauceDemoCheckoutPage extends PageUtils {
    WebDriver driver;
    @FindBy(how= How.CSS,using = ".btn_primary")
    private WebElement contn;

    public  SauceDemoCheckoutPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public SauceDemoCheckoutPage setDetails (String field,String value){
        List<WebElement> el=driver.findElements(By.xpath("//input[@type='text']"));
        for(WebElement element:el){
            if(element.getAttribute("placeholder").contains(field)){
                setValue(driver,element,10,value);
                //element.sendKeys(value);
                break;
            }
        }
        return this;
    }

    public SauceDemoOverViewPage clickContinue (){
        contn.click();
        SauceDemoOverViewPage sauceDemoOverViewPage=new SauceDemoOverViewPage(driver);
        return sauceDemoOverViewPage;
    }
}
