package orderList;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class OrderListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }


    @Test
    @DisplayName("Check we've got list of orders in the body")
    @Description("Test for GET /api/v1/orders. Expected to get courier's list of orders and StatusCode 200")
    public void checkListOfOrdersInBody() {
        Response response = OrderListMethods.getOrderList();
        OrderListMethods.verifyOrderListObtained200(response);
    }

}
