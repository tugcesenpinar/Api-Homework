package services;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class DeleteBookingTest {

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
