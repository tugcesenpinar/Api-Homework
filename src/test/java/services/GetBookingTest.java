package services;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetBookingTest {

    @BeforeTest
    public void postCreateBooking() {
        String createData = "{\n" +
                "    \"firstname\" : \"Tugce\",\n" +
                "    \"lastname\" : \"Senpinar\",\n" +
                "    \"totalprice\" : 300,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2022-01-01\",\n" +
                "        \"checkout\" : \"2022-05-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        given()
                .body(createData)
                .contentType(ContentType.JSON)
                .log().all().
                when()
                .post("https://restful-booker.herokuapp.com/booking").
                then()
                .statusCode(200).log().all();
    }

    @Test
    public void getBooking(){
        given().
                when()
                .get("https://restful-booker.herokuapp.com/booking/21").
                then()
                .log().all();
    }
}
