package io.cucumber.skeleton.pages.techCanvastablePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TablePage {
    WebDriver driver;


    @FindBy(how = How.XPATH, using = "html/body")
    private WebElement table;
    private String row="//table/tbody/tr";

    @FindBy(how = How.TAG_NAME, using = "th")
    private WebElement column;

    public TablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPriceByMake(String model){
        String price="not found";
        List<WebElement> rows=table.findElements(By.xpath(row));
        for(int i=2; i<=rows.size();i++){
            String var=driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[2]")).getText();
            if(var.contains(model)){
                price=driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[3]")).getText();
            }
        }

        return  price;
    }
    public String getPriceByName(String make){

        String price="not found";

        List<WebElement> rows=table.findElements(By.xpath(row));
        for(int i=2; i<=rows.size();i++){
            String var=driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText();
            if(var.contains(make)){
                price=driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[3]")).getText();
            }
        }

        return  price;
    }



}
