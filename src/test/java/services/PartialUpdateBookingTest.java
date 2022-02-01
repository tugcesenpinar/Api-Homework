package services;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Attachment;
import models.BookingDates;
import models.UpdateBooking;
import org.apache.http.HttpStatus;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PartialUpdateBookingTest {

    private String cookie = "";
    Attachment attachment = new Attachment();

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

        baseURI = "https://restful-booker.herokuapp.com";

        RequestSpecification request = RestAssured.given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","token="+cookie);
        Response response= request
                .body(updateData).
                when()
                .post("/booking/35");
        String result = attachment.addAttachment(request, baseURI, response);
        response.
                then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("lastname", IsEqual.equalTo(updateRequest.getLastname()))
                .log().all();
}
}
