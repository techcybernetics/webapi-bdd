package io.cucumber.skeleton.pages.datePickerPages;


import io.cucumber.skeleton.pages.PageUtils;
import io.cucumber.skeleton.utils.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DatePickerPage extends PageUtils {
    WebDriver driver;
    @CacheLookup //Declaration
    @FindBy(xpath ="//i[@class='glyphicon glyphicon-th']")
    private WebElement dateIcon; //assignment
    @CacheLookup //Declaration
    @FindBy(xpath ="//input[@placeholder='Start date']")
    private WebElement startDate; //assignment
    @CacheLookup //Declaration
    @FindBy(xpath =" //th[@class='datepicker-switch']")
    private WebElement pickerSwitch; //assignment
   // @FindBy(xpath ="//th[@class='datepicker-switch']/preceding-sibling::th")
    @FindBy(css =".prev")
    private WebElement previous; //assignment
    //@FindBy(xpath ="//th[@class='datepicker-switch']/following-sibling::th")
    @FindBy(css ="th[class='datepicker-switch']+th")
    //th[class='datepicker-switch']+th
    private WebElement next; //assignment

    public DatePickerPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void selectDate(String date){
       // List<WebElement> elements=driver.findElements(By.xpath("//table[@class='table-condensed']/tbody/tr/td")); //THis is with xpath
        List<WebElement> elements=driver.findElements(By.cssSelector(".table-condensed tbody tr td")); //This is with CSS
        for(WebElement el:elements){
            if(el.getText().equals(date)){
                if(el.getAttribute("class").contains("disable")){
                    System.out.println("The date is disabled");
                }
                else{
                    el.click();
                }
                break;
            }
        }

    }

    public DatePickerPage clickDatePicker(){
        dateIcon.click();
        return this;
    }

    public DatePickerPage clickStartDate(){
        startDate.click();
        return this;
    }

    public String currentMonthAndYear(){
        return pickerSwitch.getText();
    }

    public  DatePickerPage selectDate(String date,String monthYear){
        try{
            int clickCount=0;
            while(!currentMonthAndYear().equals(monthYear)){
                getIconElement(monthYear).click();
                driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
                clickCount++;
                System.out.println(clickCount);
            }
            selectDate(date);
        }

        catch(Exception ex){

        }




        return this;
    }

    public WebElement getIconElement(String monthDate){
        WebElement icon=null;
        Date date= DateUtils.convertStringToDate(monthDate);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        Calendar calNow=Calendar.getInstance();

        if(calendar.get(Calendar.YEAR)==calNow.get(Calendar.YEAR) && calendar.get(Calendar.MONTH)>calNow.get(Calendar.MONTH)){
            System.out.println("The month is greater than current month. Year is same");
            icon=next;

        }
        else if(calendar.get(Calendar.YEAR)>calNow.get(Calendar.YEAR)){
            System.out.println("The year is greater than the current year");
            icon=next;
        }

        else if(calendar.get(Calendar.YEAR)==calNow.get(Calendar.YEAR) && calendar.get(Calendar.MONTH)<calNow.get(Calendar.MONTH)){
            System.out.println("The year is same. Month is a previous month");
            icon=previous;
        }

        else if (calendar.get(Calendar.YEAR)<calNow.get(Calendar.YEAR)){

            icon=previous;

        }
        return icon;
    }


}
