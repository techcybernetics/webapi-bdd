package apitest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TC_PostRequest {

    @Test
    public void test(){

        //specify the base URI /URL
        baseURI="https://reqres.in/api/users";
        RequestSpecification httpRequest=given();
        JSONObject req=new JSONObject();
        req.put("email","eve.holt@reqres.in");
        req.put("password","pistol");
        httpRequest.body(req.toJSONString());
        //Response Object
        Response response=httpRequest.post("/register");
        //status code validation
        int statusCode=response.statusCode();
        Assert.assertEquals(statusCode,201);

        //print the response
        response.prettyPrint();
        //body validation
        String id=response.jsonPath().get("id");
        System.out.println(id);
        //validate header information
        String contentType=response.header("content-type"); //capture content header
        String contentEncoding=response.header("server"); //capture content encoding

        System.out.println("content type "+contentType+ " Server "+contentEncoding);

    }
}
