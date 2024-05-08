package courier;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

//методы для вызова ручек у курьера

public class CourierMethods {
    static final String SCOOTER_URL = "https://qa-scooter.praktikum-services.ru";
    static final String PATH_COURIER = "/api/v1/courier";

    static Response createCourier(Courier courier) {
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(PATH_COURIER);
        return response;
    }
    static Response loginCourier(Courier2 courier2) {
        Response response2 = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier2)
                .when()
                .post(PATH_COURIER + "/login");
        return response2;
    }

    static Response deleteCourier(int id) {
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .delete(PATH_COURIER + "/" + id);
        return response;
    }

    static int getId (Courier2 courier2){
        GetIdClass getId = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier2)
                .when()
                .post(PATH_COURIER + "/login")
                .body().as(GetIdClass.class);
        int id = getId.getId();
        return id;
    }
}
