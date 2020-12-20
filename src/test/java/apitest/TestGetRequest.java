package apitest;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;


public class TestGetRequest {

    @Test
    public void test()  {
        Response response= get("https://reqres.in/api/users?page=1");
       System.out.println(response.prettyPrint());
//        System.out.println(response.getBody().asString());
//        int responseCode=response.getStatusCode();
//        System.out.println(response.getStatusCode());
//        System.out.println(response.getStatusLine());
//        System.out.println(response.getHeader("content-type"));
//        System.out.println(response.getTimeIn(TimeUnit.SECONDS));
//        Assert.assertEquals(responseCode,200);
    }

    @Test
    public void testEqualTo(){
    given().
            get("https://reqres.in/api/users?page=1").
            then().
            statusCode(200).
            body("page", equalTo(1)).
            body("data.id[0]",equalTo(1));


    }
    @Test
    public void testHasItem(){
        given().
                get("https://reqres.in/api/users?page=1").
                then().
                statusCode(200).
                body("page", equalTo(1)).
                body("data.first_name",hasItems("Janet","George"));


    }

    @Test
    public void log(){
        given().
                get("https://reqres.in/api/users?page=1").
                then().
                statusCode(200).
                body("data.id[0]", equalTo(1)).log().all();

    }

    @Test
    public void apiAuthTest(){
        given().auth().preemptive().
                basic("Admin","admin123").
                when().get("https://opensource-demo.orangehrmlive.com/index.php/dashboard").
                then().
                assertThat().statusCode(200).log().all();

    }
}
