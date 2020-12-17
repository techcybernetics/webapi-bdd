package io.cucumber.skeleton.stepDef.apisteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.utils.CommonHelper;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.io.IOException;

public class APISteps extends CommonHelper {
    Response response=null;
    @Given("I setup the uri")
    public void i_setup_the_uri() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        baseURI=CommonHelper.readProperties("src/test/resources/env.properties").getProperty("api.reqRes.baseUri"); //code refactoring
        System.out.println(baseURI);
    }

    @When("I call the getUser API")
    public void i_call_the_get_user_api() {
        // Write code here that turns the phrase above into concrete actions
       //response= httpClient().request(Method.GET,"/users/1");
        response=httpClient().get("/users/1");
        response.prettyPrint();
    }

    @Then("I validate the id")
    public void i_validate_the_id() {
        // Write code here that turns the phrase above into concrete actions
        int id=response.jsonPath().get("data.id");
        Assert.assertEquals(id,1);
    }
    @Then("I validate the first_name")
    public void i_validate_the_first_name() {
        // Write code here that turns the phrase above into concrete actions
        String firstName=response.jsonPath().get("data.first_name");
        Assert.assertEquals(firstName,"George");
    }

    @When("I call the getUser API by {string}")
    public void getUser(String id) {
        // Write code here that turns the phrase above into concrete actions
        response=httpClient().get("/users/"+id);
        response.prettyPrint();
    }

    @Then("I validate the {string}")
    public void validateFirstName(String firstName) {
        // Write code here that turns the phrase above into concrete actions
        String name=response.jsonPath().get("data.first_name");
        Assert.assertEquals(name,firstName);
    }


    @Then("I validate the api response by the json path {string} expected {string}")
    public void i_validate_the_api_response_by_the_json_path_expected(String path, String expected) {
        // Write code here that turns the phrase above into concrete actions

      if(path.contains(",")){
          String[] paths=path.split(",");
          String[] results=expected.split(",");
          int count=0;
          for(String val:paths){
              String value=response.jsonPath().get(val);
              Assert.assertEquals(value,results[count]);
              count++;
          }
      }
      else{
          String value=response.jsonPath().get(path);
          Assert.assertTrue(value.contains(expected)); //junit 5
         // Assert.assertThat(value, CoreMatchers.containsString(expected)); //junit 4
      }

    }

    @When("I call the postRequest API by {string} and {string}")
    public void i_call_the_post_request_api_by_and(String email, String pass) {
        // Write code here that turns the phrase above into concrete actions
        JSONObject req=new JSONObject();
        req.put("password","pistol");
        req.put("email","eve.holt@reqres.in");
        System.out.println(req.toJSONString());
        //httpClient().body(req.toJSONString());
         response=httpClient().body(req.toJSONString()).request(Method.POST,"/users/register");
         response.prettyPrint();
        System.out.println(response.statusCode());




    }

    @When("I call the putRequest API by {string} and {string}")
    public void i_call_the_put_request_api_by_and(String name, String job) {
        JSONObject req=new JSONObject();
        req.put("name",name);
        req.put("job",job);
        System.out.println(req.toJSONString());
        response=httpClient().body(req.toJSONString()).request(Method.PUT,"/users/2");
        response.prettyPrint();
        System.out.println(response.statusCode());
    }

    @When("I call the patchRequest API by {string} and {string}")
    public void patchRequest(String name, String job) {
        JSONObject req=new JSONObject();
        req.put("name",name);
        req.put("job",job);
        System.out.println(req.toJSONString());
        response=httpClient().body(req.toJSONString()).request(Method.PATCH,"/users/2");
        response.prettyPrint();
        System.out.println(response.statusCode());
    }

    @When("I call the deleteUser API by {string}")
    public void deleteUser(String id) {
        // Write code here that turns the phrase above into concrete actions
        //response=httpClient().delete("/users/"+id);
        response=httpClient().request(Method.DELETE,"/users/"+id);
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),204);
    }
    @Given("I setup the url for path param")
    public void i_setup_the_url_for_path_param() throws Exception{
        // Write code here that turns the phrase above into concrete actions
        baseURI= CommonHelper.readProperties("src/test/resources/env.properties").getProperty("api.ergast.baseUri");
    }
    @When("I call the get request by passing the param {string}")
    public void i_call_the_get_request_by_passing_the_param(String season) {
        // Write code here that turns the phrase above into concrete actions
    response=httpClient().pathParam("seasons",season).request(Method.GET,"/{seasons}/circuits.json");
    response.prettyPrint();
    }
    @Given("I setup the url for query param")
    public void i_setup_the_url_for_query_param() throws Exception{
        // Write code here that turns the phrase above into concrete actions
        baseURI=CommonHelper.readProperties("src/test/resources/env.properties").getProperty("api.google.baseUri");
    }

    @When("I call the get request by passing the query param {string}")
    public void i_call_the_get_request_by_passing_the_query_param(String searchItem) {
        // Write code here that turns the phrase above into concrete actions
    response=httpClient().queryParam("q",searchItem).request(Method.GET);
    response.prettyPrint();
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(),200);
    }

}
