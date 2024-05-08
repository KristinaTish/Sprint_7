package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

//Проверь, что когда создаёшь заказ:
//можно указать один из цветов — BLACK или GREY ------------------------------------------------------ checked
//можно указать оба цвета ---------------------------------------------------------------------------- checked
//можно совсем не указывать цвет --------------------------------------------------------------------- checked
//тело ответа содержит track ------------------------------------------------------------------------- checked
//Чтобы протестировать создание заказа, нужно использовать параметризацию ---------------------------- checked

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final String[] colors;
    private final String SCOUTER_URL = "https://qa-scooter.praktikum-services.ru";
    private int track;

    public CreateOrderTest(String[] colors) {
        this.colors = colors;
    }

    @Parameterized.Parameters
    public static Collection switchColors() {
        return Arrays.asList(new String[][]{{"BLACK"}}, new String[][]{{"GREY"}}, new String[][]{{"BLACK", "GREY"}}, new String[][]{{}}, new String[][]{{null}});
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = SCOUTER_URL;
    }

    @After
    public void deleteOrder() {
        if (track != 0) {
            OrderMethods.cancelOrder(track);
        }
    }

    @Test
    @DisplayName("Check successful choosing between Black and Grey scooter")
    @Description("Basic test on POST /api/v1/orders. Checking that it's possible to choose Black or Grey scooter")
    public void checkChoosingColor() {
        var order = new Order("Альбер", "Камю", "Крысиная аллея, 6", 4, "+79040000000", 5, "2024-06-06", "", colors);
        Response response = OrderMethods.createOrder(order);
        track = OrderMethods.getOrderTrack(response);
        OrderMethods.verifyIfOrderCreatedSuccessfully(response);
    }
}
