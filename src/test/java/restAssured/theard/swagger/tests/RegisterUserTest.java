package restAssured.theard.swagger.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import restAssured.theard.swagger.models.dataModels.RegisterData;
import restAssured.theard.swagger.models.request.RegistrationRequest;
import restAssured.theard.swagger.models.response.RegistrationResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RegisterUserTest extends BaseTest{

    @Test
    @DisplayName("Регистрация пользователя c обязательными параметрами")
    @Tag("swagger")
    public void registrationWithMinParams(){
        String login = "Login" + faker.number().numberBetween(1,1000000);
        String pass = "Qwerty13!";
        RegistrationRequest request = RegistrationRequest.builder().pass(pass).login(login).build();
        RegistrationResponse response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/signup")
                .then()
                .statusCode(201)
                .extract().as(RegistrationResponse.class);

        assertThat(response.getInfo().getMessage(), equalTo("User created"));
        assertThat(response.getRegisterData().getId(), notNullValue());
        assertThat(response.getRegisterData().getLogin(), equalTo(login));
    }

    @Test
    @DisplayName("Регистарция уже существующего пользователя. Ожидается ошибка 'Login already exist'")
    @Tag("swagger")
    public void retryRegistartionTest(){
        RegisterData user = createUser();
        RegistrationRequest request = RegistrationRequest.builder()
                .pass(user.getPass())
                .login(user.getLogin())
                .build();
        RegistrationResponse response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/signup")
                .then()
                .statusCode(400)
                .extract().as(RegistrationResponse.class);
        assertThat(response.getInfo().getMessage(), equalTo("Login already exist"));
    }
}
