package io.cucumber.skeleton.stepDef.saucedemo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.pages.sauceDemoPages.SauceDemoHomePage;
import io.cucumber.skeleton.pages.sauceDemoPages.SauceDemoLoginPage;
import io.cucumber.skeleton.utils.CommonHelper;
import io.cucumber.skeleton.utils.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class SauceDemoSteps extends CommonHelper{

    SauceDemoHomePage sauceDemoHomePage;

    //before every scenario //Global hooks
    @Before(order=1)
    public void setup(){
        System.out.println("This is my setup");
    }
    @Before(order = 2)
    public void setupG_1(){
        System.out.println("This is my setup G1");
    }
    @Before (order = 3)
    public void setupG_2(){
        System.out.println("This is my setup G2");
    }
    //after every scenario //Global Hooks
    @After
    public void tearDown(Scenario scenario){

        quitDriver(scenario);
    }

    @BeforeStep("@products") //Local hooks for the product scenario
    public void emptyTableData() throws Exception{

        System.out.println("This is my custom step to empty the table data");
        String clearDb="UPDATE INVENTORY "+
                "SET description=NULL";
        dbUpdt(clearDb);
        System.out.println("clean up completed.....");
    }

    @AfterStep("@products")
    public void setupAfter(){

        System.out.println("This is my custom after");
    }

    @Given("user is in the login page")
    public void loginPage() throws Exception{
        String url=CommonHelper.readProperties("src/test/resources/env.properties").getProperty("url.sauceLab");
        String browser=CommonHelper.readProperties("src/test/resources/env.properties").getProperty("browser");
        driver= DriverManager.initDriver(browser,url);
    }

    @When("the user enter the login and password")
    public void enterLoginAndPassword() {
        SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage(driver);
        sauceDemoHomePage= sauceDemoLoginPage.login("standard_user","secret_sauce");
    }


    @Then("the user is navigated to the login page")
    public void verifyUser() {
        String title= sauceDemoHomePage.getTitle();
        Assert.assertEquals("Swag Labs",title);

    }
    @When("the user enter the login {string} and password {string}")
    public void userParam(String userName, String password) {
        SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage(driver);
        sauceDemoHomePage= sauceDemoLoginPage.login(userName,password);
    }

    @Then("the user validates the products available in the homepage and updates the product description in the DB")
    public void products(DataTable dataTable) throws Exception {

        //List of Maps

        List<Map<String,String>> list=dataTable.asMaps(String.class,String.class);
        System.out.println(list.size());

        for (int i=0;i<list.size();i++){

            System.out.println(list.get(i).get("products"));

            for(WebElement el:sauceDemoHomePage.getItems()){

                String productData=list.get(i).get("products");
                String itemDesc=sauceDemoHomePage.getItemDescription(productData); // This is to get
                                                                                    // the item desc by passing the product name as arg
                if(el.getText().contains(productData)){

                    Assert.assertEquals(el.getText(),list.get(i).get("products"));
                    String sqlUpdt="UPDATE INVENTORY SET description=" +"'"+itemDesc+"'"+" where products="+"'"+productData+"'";
                    dbUpdt(sqlUpdt);
                    break;

                }

            }
        }

        //list of list

//        List<List<String>>list1=dataTable.asLists(String.class);
//        for (int i=0;i<list1.size();i++){
//            System.out.println(list1.get(i).get(0));
//        }

    }
}
