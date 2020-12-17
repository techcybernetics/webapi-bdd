package apitest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
public class TC_GetRequest {

    @Test
    public void test(){

        //specify the base URI /URL
        baseURI="https://reqres.in/api/users";
        //Given I send a request to the API
        //Create Request Object
        RequestSpecification httpRequest=given();

        //Response Object


        //**********When I receive the response**********
        Response response=httpRequest.request(Method.GET,"/1");

        //********Then I validate the status code*********
        //status code validation
        int statusCode=response.statusCode();
        Assert.assertEquals(statusCode,200);

        //print the response
        System.out.println(response.prettyPrint());


    }
}
