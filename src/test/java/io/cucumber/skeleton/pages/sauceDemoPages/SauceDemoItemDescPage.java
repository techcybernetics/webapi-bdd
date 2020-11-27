package io.cucumber.skeleton.pages.sauceDemoPages;

import io.cucumber.skeleton.pages.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoItemDescPage extends PageUtils {
    WebDriver driver;
    @CacheLookup //Declaration
    @FindBy(how= How.XPATH,using = "//div[@class='inventory_details_name']")
    private WebElement itemName; //assignment

    @CacheLookup //Declaration
    @FindBy(how= How.XPATH,using = "//button[contains(text(),'ADD TO CART')]")
    private WebElement addToCartButton; //assignment

    @FindBy(how= How.XPATH,using = "//button[contains(text(),'REMOVE')]")
    private WebElement removeButton; //assignment
    SauceDemoItemDescPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    public String getItemName() {

        return getTextValue(driver, itemName, 10);

    }

    public void addItem(){

        clickOn(driver,addToCartButton,10);

    }

    public void removeItem(){
        clickOn(driver,removeButton,10);

    }
}
