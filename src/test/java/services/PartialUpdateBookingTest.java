package services;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import models.BookingDates;
import models.UpdateBooking;
import org.apache.http.HttpStatus;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PartialUpdateBookingTest {

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
    public void updateBooking()  {

        Map<String,String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2022-05-01");
        bookingdates.put("checkout", "2022-07-06");

        UpdateBooking updateRequest = new UpdateBooking("tugce", "sena", 600, false,bookingdates);
        String updateData = new Gson().toJson(updateRequest);

        given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","token="+cookie)
                .body(updateData).
                when()
                .post("https://restful-booker.herokuapp.com/booking/21").
                then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("lastname", IsEqual.equalTo(updateRequest.getLastname()))
                .log().all();
}
}
