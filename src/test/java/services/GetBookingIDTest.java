package services;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetBookingIDTest {


    @Test
    public void getBookingId(){
        given().header("firstname","tugce").
                when()
                .get("https://restful-booker.herokuapp.com/booking\n").
                then()
                .log().all();
    }
}
