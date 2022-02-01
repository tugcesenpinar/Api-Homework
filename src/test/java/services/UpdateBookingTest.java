package services;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import models.UpdateBooking;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class UpdateBookingTest {
 private String cookie = "";
    @BeforeTest
    public void Token(){
        String user = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        String token = given().body(user)
                .contentType(ContentType.JSON).
                when()
                .post("https://restful-booker.herokuapp.com/auth").
                then()
                .statusCode(200)
                .extract().path("token");
        cookie = token;
        System.out.println(token);
    }

    @Test
    public void updateBooking() {

      /*  UpdateBooking updateRequest = new UpdateBooking("tugce", "sen", 500, true);
        String updateData = new Gson().toJson(updateRequest);*/

        String updateData = "{\n" +
                "    \"firstname\" : \"Tugcee\",\n" +
                "    \"lastname\" : \"sen\",\n" +
                "    \"totalprice\" : 300,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"BookingDates\" : {\n" +
                "        \"checkin\" : \"2022-01-01\",\n" +
                "        \"checkout\" : \"2022-05-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","token="+cookie)
                .body(updateData).
                when()
                .put("https://restful-booker.herokuapp.com/booking/1").
                then()
                .statusCode(200).log().all();

    }
}
