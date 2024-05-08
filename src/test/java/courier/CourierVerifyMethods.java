package courier;

import io.restassured.response.Response;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

//тестовые методы отдельно

public class CourierVerifyMethods {
    static void verifySuccessfullCreation201(Response response) {
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_CREATED)
                .and()
                .body("ok", equalTo(true));
    }

    static void verifyCreationSameLogin409(Response response) {
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_CONFLICT)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    static void verifyCreationLackOfData400(Response response) {
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    static void verifyLoginSuccess200(Response response) {
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("id", notNullValue());
    }

    static void verifyLoginLackOfData400(Response response) {
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}
