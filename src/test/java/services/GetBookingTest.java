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

public class GetBookingTest {
    Attachment attachment = new Attachment();

    @BeforeTest
    public void postCreateBooking() {
        String createData = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
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
        baseURI = "https://restful-booker.herokuapp.com";
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all();
        Response response = request.
                when()
                .get("/booking/21");
        String result = attachment.addAttachment(request, baseURI, response);
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .log().all();
    }
}
