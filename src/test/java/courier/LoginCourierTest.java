package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

//курьер может авторизоваться ------------------------------------------------------ checked
//для авторизации нужно передать все обязательные поля ----------------------------- checked
//система вернёт ошибку, если неправильно указать логин или пароль -----------------
//если какого-то поля нет, запрос возвращает ошибку -------------------------------- checked
//если авторизоваться под несуществующим пользователем, запрос возвращает ошибку ___
//успешный запрос возвращает id ---------------------------------------------------- checked

public class LoginCourierTest {
    int courierId;
    //SCOOTER_URL = "https://qa-scooter.praktikum-services.ru"
    @Before
    public void setUp() {
        RestAssured.baseURI = CourierMethods.SCOOTER_URL;
    }

    @After
    public void deleteCourier(){
        if (courierId > 0){
            CourierMethods.deleteCourier(courierId);
        }
    }

    @Test
    @DisplayName("Successful login as a courier")
    @Description("Test for POST /api/v1/courier request in which we log in successfully. We expect StatusCode: 200; and body which contains id (not null)")
    public void courierLoginSuccess() {
        var courier = Courier.random();  //create courier's credentials with random name
        CourierMethods.createCourier(courier);  //create courier

        Courier2 courier2 = Courier2.from(courier);  //extract login and password from courier's credentials
        courierId = CourierMethods.getId(courier2);   //log in and get id - needed to delete courier afterwards. Before test methods so that we get id and delete courier even if test fails

        Response response = CourierMethods.loginCourier(courier2); //log in and create variable with login response
        CourierVerifyMethods.verifyLoginSuccess200(response); // assert that response has 200 StatusCode and body contains id which is not null

    }

    @Test
    @DisplayName("Log in as courier using login=null")
    @Description("Test for POST /api/v1/courier/login request. Here we use as login - null and normal password. We expect that the response contains StatusCode: 400; and message: Недостаточно данных для входа")
    public void courierLoginWithNullLogin() {
        var courier = Courier.random();  //create courier's credentials with random name
        CourierMethods.createCourier(courier);  //create courier

        Courier2 courierID = Courier2.from(courier);  //extract login and password from courier's credentials
        courierId = CourierMethods.getId(courierID);  //log in and get id - needed to delete courier afterwards. Before test methods so that we get id and delete courier even if test fails

        Courier2 courier2 = Courier2.withLoginNull(courier); // extract only password and use null instead of login
        Response response = CourierMethods.loginCourier(courier2); // try to log in using login = null and normal password
        CourierVerifyMethods.verifyLoginLackOfData400(response); // check that we got StatusCode:400 and body.message: "Недостаточно данных для входа"
    }

    @Test
    @DisplayName("Log in as courier using login=''")
    @Description("Test for POST /api/v1/courier/login request. Here we use as login ='' and normal password. We expect that the response contains StatusCode: 400; and message: Недостаточно данных для входа")
    public void courierLoginWithoutLogin() {
        var courier = Courier.random();  //create courier's credentials with random name
        CourierMethods.createCourier(courier);  //create courier

        Courier2 courierID = Courier2.from(courier);  //extract login and password from courier's credentials
        courierId = CourierMethods.getId(courierID);  //log in and get id - needed to delete courier afterwards. Before test methods so that we get id and delete courier even if test fails

        Courier2 courier2 = Courier2.withoutLogin(courier); // extract only password and use null instead of login
        Response response = CourierMethods.loginCourier(courier2); // try to log in using login = null and normal password
        CourierVerifyMethods.verifyLoginLackOfData400(response); // check that we got StatusCode:400 and body.message: "Недостаточно данных для входа"
    }

    @Test
    @DisplayName("Log in as courier using password = null")
    @Description("Test for POST /api/v1/courier/login request. In this test we use only login to log in, the password is null. We expect that the response contains StatusCode: 400; and message: Недостаточно данных для входа")
    public void courierLoginWithNullPassword(){
        var courier = Courier.random();
        CourierMethods.createCourier(courier);

        Courier2 courierID = Courier2.from(courier);
        courierId = CourierMethods.getId(courierID);

        Courier2 courier2 = Courier2.withPasswordNull(courier);
        Response response = CourierMethods.loginCourier(courier2);
        CourierVerifyMethods.verifyLoginLackOfData400(response);
    }

    @Test
    @DisplayName("Log in as courier using password = ''")
    @Description("Test for POST /api/v1/courier/login request. In this test we use only login to log in, the password is ''. We expect that the response contains StatusCode: 400; and message: Недостаточно данных для входа")
    public void courierLoginWithoutPassword(){
        var courier = Courier.random();
        CourierMethods.createCourier(courier);

        Courier2 courierID = Courier2.from(courier);
        courierId = CourierMethods.getId(courierID);

        Courier2 courier2 = Courier2.withoutPassword(courier);
        Response response = CourierMethods.loginCourier(courier2);
        CourierVerifyMethods.verifyLoginLackOfData400(response);
    }
}
