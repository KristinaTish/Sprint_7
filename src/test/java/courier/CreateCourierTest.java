package courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;


//курьера можно создать ------------------------------------------------------ checked
//нельзя создать двух одинаковых курьеров ------------------------------------ checked
//чтобы создать курьера, нужно передать в ручку все обязательные поля -------- checked
//запрос возвращает правильный код ответа ------------------------------------ checked
//успешный запрос возвращает ok: true ---------------------------------------- checked
//если одного из полей нет, запрос возвращает ошибку ------------------------- checked
//если создать пользователя с логином, который уже есть, возвращается ошибка - checked

public class CreateCourierTest {

    public int courierId;

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
    @DisplayName("Create unique courier")
    @Description("Basic test for POST /api/v1/courier request. Expected StatusCode 201 and body.ok: true")
    public void createUniqueCourier() {
        var courier = Courier.random();
        Response response = CourierMethods.createCourier(courier);
        Courier2 courier2 = Courier2.from(courier);
        courierId = CourierMethods.getId(courier2);
        CourierVerifyMethods.verifySuccessfullCreation201(response);
    }

    @Test
    @DisplayName("Create 2 similar couriers' accounts")
    @Description("Basic test for POST /api/v1/courier request destined to check the impossibility to create two similar accounts. Expected StatusCode 409 and body.message:'Этот логин уже используется. Попробуйте другой.'")
    public void createTwoSimilarCouriers(){
        var courier = Courier.random();
        Response response = CourierMethods.createCourier(courier);
        Response response2 = CourierMethods.createCourier(courier);
        CourierVerifyMethods.verifyCreationSameLogin409(response2);

        if (response.statusCode() == 201 || response2.statusCode() == 201){
            Courier2 courier2 = Courier2.from(courier);
            courierId = CourierMethods.getId(courier2);
        }
    }

    @Test
    @DisplayName("Create courier with login = null")
    @Description("Test for POST /api/v1/courier request destined to check the impossibility to create courier account with login = null. Expected StatusCode 400 and body.message:'Недостаточно данных для создания учетной записи'")
    public void ccWithNullLogin(){
        var courier = Courier.withNullLogin();
        Response response = null;
        try{
            response = CourierMethods.createCourier(courier);
            CourierVerifyMethods.verifyCreationLackOfData400(response);
        } catch (AssertionError e) {
            if (response != null && response.statusCode() == 201){
                Courier2 courier2 = Courier2.from(courier);
                courierId = CourierMethods.getId(courier2);
            } throw e;
        }
    }

    @Test
    @DisplayName("Create courier with login =''")
    @Description("Test for POST /api/v1/courier request destined to check the impossibility to create courier account with login =''. Expected StatusCode 400 and body.message:'Недостаточно данных для создания учетной записи'")
    public void ccWithoutLogin(){
        var courier = Courier.withoutLogin();
        Response response = null;
        try{
            response = CourierMethods.createCourier(courier);
            CourierVerifyMethods.verifyCreationLackOfData400(response);
        } catch (AssertionError e) {
            if (response != null && response.statusCode() == 201){
                Courier2 courier2 = Courier2.from(courier);
                courierId = CourierMethods.getId(courier2);
            } throw e;
        }
    }


    @Test
    @DisplayName("Create courier with password = null")
    @Description("Test for POST /api/v1/courier request destined to check the impossibility to create courier account with password = null. Expected StatusCode 400 and body.message:'Недостаточно данных для создания учетной записи'")
    public void ccWithNullPassword(){
        var courier = Courier.withNullPassword();
        Response response = null;
        try{
            response = CourierMethods.createCourier(courier);
            CourierVerifyMethods.verifyCreationLackOfData400(response);
        } catch (AssertionError e) {
            if (response != null && response.statusCode() == 201){
                Courier2 courier2 = Courier2.from(courier);
                courierId = CourierMethods.getId(courier2);
            } throw e;
        }
    }

    @Test
    @DisplayName("Create courier with password =''")
    @Description("Test for POST /api/v1/courier request destined to check the impossibility to create courier account with password = ''. Expected StatusCode 400 and body.message:'Недостаточно данных для создания учетной записи'")
    public void ccWithoutPassword(){
        var courier = Courier.withoutPassword();
        Response response = null;
        try{
            response = CourierMethods.createCourier(courier);
            CourierVerifyMethods.verifyCreationLackOfData400(response);
        } catch (AssertionError e) {
            if (response != null && response.statusCode() == 201){
                Courier2 courier2 = Courier2.from(courier);
                courierId = CourierMethods.getId(courier2);
            } throw e;
        }
    }
    //метод работает,но не удаляет аккаунт
    @Test
    @DisplayName("Create courier with firstName = null")
    @Description("Test for POST /api/v1/courier request destined to check the impossibility to create courier account with firstName = null. Expected StatusCode 400 and body.message:'Недостаточно данных для создания учетной записи'")
    public void ccWithNullFirstName(){
        var courier = Courier.withNullFirstName();
        Response response = null;
        try{
            response = CourierMethods.createCourier(courier);
            CourierVerifyMethods.verifyCreationLackOfData400(response);
        } catch (AssertionError e) {
            if (response != null && response.statusCode() == 201){
                Courier2 courier2 = Courier2.from(courier);
                courierId = CourierMethods.getId(courier2);
            } throw e;
        }
    }

    @Test
    @DisplayName("Create courier with firstName = ''")
    @Description("Test for POST /api/v1/courier request destined to check the impossibility to create courier account with firstName = ''. Expected StatusCode 400 and body.message:'Недостаточно данных для создания учетной записи'")
    public void ccWithoutFirstName(){
        var courier = Courier.withoutFirstName();
        Response response = null;
        try{
            response = CourierMethods.createCourier(courier);
            CourierVerifyMethods.verifyCreationLackOfData400(response);
        } catch (AssertionError e) {
            if (response != null && response.statusCode() == 201){
                Courier2 courier2 = Courier2.from(courier);
                courierId = CourierMethods.getId(courier2);
            } throw e;
        }
    }
}

