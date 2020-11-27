package io.cucumber.skeleton.pages.sauceDemoPages;


import io.cucumber.skeleton.pages.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoCartPage extends PageUtils {
    WebDriver driver;

    @FindBy(how= How.XPATH,using = "//a[contains(text(),'CHECKOUT')]")
    private WebElement checkout;


    public SauceDemoCartPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public SauceDemoCheckoutPage clickCheckout(){
        clickOn(driver,checkout,10);
        SauceDemoCheckoutPage sauceDemoCheckoutPage=new SauceDemoCheckoutPage(driver);
        return sauceDemoCheckoutPage;

    }

}
