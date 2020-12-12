package apitest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class TestGetRequest {

    @Test
    public void test(){
        Response response= RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.prettyPrint());

    }
}
