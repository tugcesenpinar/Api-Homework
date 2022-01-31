package services;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import models.CreateBooking;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.given;

public class CreateBookingTest {
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

      /*  Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");

       CreateBooking createRequest = new CreateBooking("tugce","senpinar",500, true, Date.valueOf("2022-01-01") ,Date.valueOf("2022-05-01"),"breakfast");
        String createData = new Gson().toJson(createRequest); */
        given()
                .body(createData)
                .contentType(ContentType.JSON)
                .log().all().
                when()
                .post("https://restful-booker.herokuapp.com/booking").
                then()
                .statusCode(200).log().all();
    }

    @AfterTest
    public void deleteBooking(){
        given()
                .header("Cookie","token="+cookie).
                when()
                .delete("https://restful-booker.herokuapp.com/booking/21\n").
                then()
                .statusCode(201)
                .log().all();
    }
}
