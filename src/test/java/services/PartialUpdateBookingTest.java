package services;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import models.UpdateBooking;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
    public void updateBooking() {

        UpdateBooking updateRequest = new UpdateBooking("tugce", "sena", 600, false);
        String updateData = new Gson().toJson(updateRequest);

        given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","token="+cookie)
                .body(updateData).
                when()
                .patch("https://restful-booker.herokuapp.com/booking/21").
                then()
                .statusCode(200).log().all();
}
}
