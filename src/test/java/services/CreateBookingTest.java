package services;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Attachment;
import models.BookingDates;
import models.CreateBooking;
import org.apache.http.HttpStatus;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.ParseException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CreateBookingTest {
    private String cookie = "";
   Attachment attachment = new Attachment();

    @BeforeTest
    public void Token() {
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
    public void postCreateBooking() throws ParseException {
     /*  String createData = "{\n" +
               "    \"firstname\" : \"Tugce\",\n" +
               "    \"lastname\" : \"Senpinar\",\n" +
               "    \"totalprice\" : 300,\n" +
               "    \"depositpaid\" : true,\n" +
               "    \"BookingDates\" : {\n" +
               "        \"checkin\" : \"2022-01-01\",\n" +
               "        \"checkout\" : \"2022-05-01\"\n" +
               "    },\n" +
               "    \"additionalneeds\" : \"Breakfast\"\n" +
               "}";*/

      /*  SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd");

            Date checkin = formatter.parse("2022-01-01");
            Date checkout = formatter.parse("2022-05-01"); */

        baseURI = "https://restful-booker.herokuapp.com";
        BookingDates bookingdates = new BookingDates("2022-01-02","2022-05-02");

        CreateBooking createRequest = new CreateBooking("tugce", "senpinar", 500, true, "breakfast",bookingdates );
        String createData = new Gson().toJson(createRequest);
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all();
        Response response= request
                .body(createData).
                when()
                .post("/booking");
        String result = attachment.addAttachment(request, baseURI, response);
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("lastname", IsEqual.equalTo(createRequest.getLastname()))
                .log().all();

    }

    @AfterTest
    public void deleteBooking() {
        given()
                .header("Cookie", "token=" + cookie).
                when()
                .delete("https://restful-booker.herokuapp.com/booking/21\n").
                then()
                .statusCode(201)
                .log().all();
    }
}
