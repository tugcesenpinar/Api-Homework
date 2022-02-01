package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Attachment;
import org.apache.http.HttpStatus;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class UpdateBookingTest {
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

        baseURI = "https://restful-booker.herokuapp.com";
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + cookie);
        Response response = request
                .body(updateData).
                when()
                .put("/booking/26");
                String result = attachment.addAttachment(request, baseURI, response);
        response.
                then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("firstname",IsEqual.equalTo("Tugcee"))
                .log().all();

    }
}
