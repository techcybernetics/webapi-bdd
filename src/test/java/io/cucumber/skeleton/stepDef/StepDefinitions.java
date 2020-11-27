package io.cucumber.skeleton.stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.FizzBuzz;
import org.junit.Assert;

// Glue code
public class StepDefinitions {
    FizzBuzz fizzBuzz;
    String result;

    @Given("Create a FizzBuzz game play")
    public void createFizzBuzzInstance() {
        // Write code here that turns the phrase above into concrete actions
        fizzBuzz=new FizzBuzz();

    }


    @When("I play with number {int}")
    public void GetNumber(int int1) {
        // Write code here that turns the phrase above into concrete actions
        result= fizzBuzz.game(int1);
    }

    @Then("the result is Fizz")
    public void compareFizzResult() {
        // Write code here that turns the phrase above into concrete actions

        Assert.assertEquals(result,"Fizz");
    }
    @Then("the result is Buzz")
    public void compareBuzzResult() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(result,"Buzz");
    }

    @Then("the result is FizzBuzz")
    public void compareFizzBuzzResult() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(result,"FizzBuzz");
    }
    @Then("the results are {string}")
    public void the_results_are(String value) {
        // Write code here that turns the phrase above into concrete actions
       Assert.assertEquals(result,value);
    }

}
