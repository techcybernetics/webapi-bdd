package io.cucumber.skeleton.pages.sauceDemoPages;


import io.cucumber.skeleton.pages.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SauceDemoLoginPage extends PageUtils {
    WebDriver driver;

    @FindBy(xpath = "//input[@id='user-name']")
    @CacheLookup
    private WebElement userName;

    @FindBy(xpath = "//input[@id='password']")
    @CacheLookup
    private WebElement password1;

    @FindBy(xpath = "//input[@id='login-button']")
    @CacheLookup
    private WebElement loginButton;

    //This is a constructor
    public SauceDemoLoginPage(WebDriver driver) {
        System.out.println("*******The constructor is called********");
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    public SauceDemoHomePage login(String user) {
        setValue(driver,userName,20,user);
        setValue(driver,password1,20,"secret_sauce");
        clickOn(driver,loginButton,20);
        SauceDemoHomePage sauceDemoHomePage=new SauceDemoHomePage(driver);
        return sauceDemoHomePage;
    }
    public SauceDemoHomePage login(String user,String password) {
        setValue(driver,userName,20,user);
        setValue(driver,password1,20,password);
        clickOn(driver,loginButton,20);
        SauceDemoHomePage sauceDemoHomePage=new SauceDemoHomePage(driver);
        return sauceDemoHomePage;
    }
}
