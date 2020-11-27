package io.cucumber.skeleton.pages.sauceDemoPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SauceDemoHomePage {
    WebDriver driver;


    @FindBy(how= How.XPATH,using = "//div[@class='product_label']")
    private WebElement productLabel;

    public SauceDemoHomePage(WebDriver driver){
    this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    public String getProductLabel(){
//        String text=productLabel.getText();
//        return text;
        return productLabel.getText().trim();
    }

    public  SauceDemoItemDescPage clickItem(String itemName){
        driver.findElement(By.xpath("//div[text()='"+itemName+"']")).click();
        SauceDemoItemDescPage sauceDemoItemDescPage=new SauceDemoItemDescPage(driver);
        return  sauceDemoItemDescPage;
    }


    public  String getItemPrice(String itemName){
        return driver.findElement(By.xpath("//div[text()='"+itemName+"']/../../../div[@class='pricebar']/div[@class='inventory_item_price']")).getText();
    }

    //inline method parameter passing
    public  String getItemDescription(String itemName) {
        return driver.findElement(By.xpath("//div[text()='" + itemName + "']/../../div")).getText();
    }


    public  String getTitle(){
        return driver.getTitle();
    }

    public List<WebElement> getItems(){
        return driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
    }

    }
