package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetBookingIDTest {


    @Test
    public void getBookingId(){
        String baseURL="https://restful-booker.herokuapp.com/booking";

        RequestSpecification request = RestAssured.given()
                .header("firstname","tugce");
               Response response= requestSpecification.get(baseURL);
                       response.then()
                       .assertThat()
                       .statusCode(HttpStatus.SC_OK);
    }
}
