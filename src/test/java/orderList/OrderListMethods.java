package orderList;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
//Проверь, что в тело ответа возвращается список заказов.
public class OrderListMethods {
    private static final String PATH_ORDER_LIST = "/api/v1/orders";
    static Response getOrderList() {
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .get(PATH_ORDER_LIST);
        return response;
    }

    static void verifyOrderListObtained200(Response response) {
        response.then().assertThat().body("orders", notNullValue())
                .and()
                .statusCode(HttpURLConnection.HTTP_OK);
    }
}
