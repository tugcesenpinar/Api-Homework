package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Attachment;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetBookingIDTest {
    Attachment attachment = new Attachment();

    @Test
    public void getBookingId(){
        baseURI = "https://restful-booker.herokuapp.com";

        RequestSpecification request = RestAssured.given()
                .header("firstname","tugce");
        Response response = request.
                when()
                .get("/booking");
        String result = attachment.addAttachment(request, baseURI, response);
        response.then()
                       .assertThat()
                       .statusCode(HttpStatus.SC_OK);
    }
}
