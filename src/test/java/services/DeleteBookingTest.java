package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Attachment;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DeleteBookingTest {

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

    public void deleteBooking(){

        baseURI = "https://restful-booker.herokuapp.com";
        RequestSpecification request = RestAssured.given()
                .header("Cookie","token="+cookie);
        Response response = request.
                when()
                .delete("/booking/21");
        String result = attachment.addAttachment(request, baseURI, response);
        response.
                then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .log().all();
    }
}
