package order;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderMethods {
    private static final String PATH_ORDER = "/api/v1/orders";
    static Response createOrder(Order order) {
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post(PATH_ORDER);
        return response;
    }

    static Integer getOrderTrack(Response response) {
        return response.then().extract().path("track");
    }

    static void verifyIfOrderCreatedSuccessfully(Response response) {
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_CREATED)
                .and()
                .body("track", notNullValue());
    }

    static void cancelOrder(int track) {
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"track\": " + track + "}")
                .when()
                .put(PATH_ORDER +"/cancel");
    }
}

