package io.cucumber.skeleton.pages.orangehrmPages;

import io.cucumber.skeleton.pages.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrangeHRMLoginPage extends PageUtils {
    WebDriver driver;

   /* XPATH
    @FindBy(xpath = "//input[@id='txtUsername']")
    @CacheLookup
    private WebElement userName;
    @FindBy(xpath = "//input[@id='txtPassword']")
    @CacheLookup
    private WebElement password1;
    @FindBy(xpath = "//input[@id='btnLogin']")
    @CacheLookup
    private WebElement loginButton;
    */
   @FindBy(css = "input#txtUsername")
   @CacheLookup
   public WebElement userName;
    @FindBy(css="input#txtPassword")
    @CacheLookup
    public WebElement password;
    @FindBy(css="input#btnLogin")
    @CacheLookup
    public WebElement loginButton;

    public OrangeHRMLoginPage(WebDriver driver) {
        System.out.println("*******The constructor is called********");
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    public OrangeHRMHomePage login(String user,String pass) {
        setValue(driver,userName,20,user);
        setValue(driver,password,20,pass);
        clickOn(driver,loginButton,20);
        OrangeHRMHomePage orangeHRMHomePage=new OrangeHRMHomePage(driver);
        return  orangeHRMHomePage;
    }



}
