package io.cucumber.skeleton.pages.sauceDemoPages;


import io.cucumber.skeleton.pages.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CommonPage extends PageUtils {
    WebDriver driver;
    @CacheLookup //Declaration
    @FindBy(xpath ="//button[contains(text(),'Open Menu')]")
    private WebElement menu; //assignment
    //@FindBy(xpath ="//a[@id='logout_sidebar_link']")
    @FindBy(how= How.XPATH,using = "//a[@id='logout_sidebar_link']")
    private WebElement logoutLink;

    @FindBy(how= How.XPATH,using = "//span[@class='fa-layers-counter shopping_cart_badge']")
    private WebElement itemCount;

   public CommonPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public int itemCount(){
        String count=getTextValue(driver,itemCount,10);
        return Integer.parseInt(count);
    }

    public void logout () {

        menu.click();
       clickOn(driver,logoutLink,30);

    }

    public SauceDemoCartPage clickCart(){
       clickOn(driver,itemCount,10);
       SauceDemoCartPage sauceDemoCartPage=new SauceDemoCartPage(driver);
       return  sauceDemoCartPage;
    }
}
