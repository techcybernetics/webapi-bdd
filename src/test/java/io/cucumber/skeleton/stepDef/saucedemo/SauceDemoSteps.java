package io.cucumber.skeleton.stepDef.saucedemo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
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
    public void setup1(){
        System.out.println("This is my custom setup1");
    }

    @Given("user is in the login page")
    public void loginPage() {

        driver= DriverManager.initDriver("chrome","https://www.saucedemo.com");
    }

    @When("the user enter the login and password")
    public void enterLoginAndPassword() {
        SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage(driver);
        sauceDemoHomePage= sauceDemoLoginPage.login("standard_user","secret_sauce");
    }


    @Then("the user is navigated to the login page")
    public void verifyUser() {
        String title= sauceDemoHomePage.getTitle();
        Assert.assertEquals("Swag Lab",title);

    }
    @When("the user enter the login {string} and password {string}")
    public void userParam(String userName, String password) {
        SauceDemoLoginPage sauceDemoLoginPage=new SauceDemoLoginPage(driver);
        sauceDemoHomePage= sauceDemoLoginPage.login(userName,password);
    }

    @Then("the user validates the products available in the homepage")
    public void products(DataTable dataTable) {

        //List of Maps

        List<Map<String,String>> list=dataTable.asMaps(String.class,String.class);
        System.out.println(list.size());

        for (int i=0;i<list.size();i++){

            System.out.println(list.get(i).get("products"));

            for(WebElement el:sauceDemoHomePage.getItems()){

                if(el.getText().contains(list.get(i).get("products"))){
                    Assert.assertEquals(el.getText(),list.get(i).get("products"));
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
