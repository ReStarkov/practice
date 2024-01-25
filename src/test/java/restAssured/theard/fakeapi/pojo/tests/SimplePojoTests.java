package restAssured.theard.fakeapi.pojo.tests;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import restAssured.theard.fakeapi.pojo.models.user.Address;
import restAssured.theard.fakeapi.pojo.models.user.Geolocation;
import restAssured.theard.fakeapi.pojo.models.user.Name;
import restAssured.theard.fakeapi.pojo.models.user.addUser.RequestAddUser;
import restAssured.theard.fakeapi.pojo.models.user.addUser.ResponseAddUser;
import restAssured.theard.fakeapi.pojo.models.user.authUser.AuthUserRequest;
import restAssured.theard.fakeapi.pojo.models.user.getSingleUser.GetSingleUserResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;

public class SimplePojoTests {

    @BeforeAll
    private static void setUp() {
        RestAssured.baseURI = "https://fakestoreapi.com";
    }

    private RequestAddUser createUser(){
        Name name = new Name("Wayne", "Rooney");
        Geolocation geolocation = new Geolocation("13.1123", "123.11111");
        Address address = Address.builder()
                .city("Manchester")
                .zipcode("12926-3874")
                .street("Red")
                .number(3)
                .geolocation(geolocation).build();

        return RequestAddUser.builder()
                .email("12e3@gmail.com")
                .username("Wayne")
                .phone("123w4567890")
                .password("10Wazzzza!")
                .name(name)
                .address(address).build();
    }

    @Test
    @Tag("pojo")
    @DisplayName("создание пользователя")
    public void createUserTest() {
        Name name = new Name("Wayne", "Rooney");
        Geolocation geolocation = new Geolocation("13.1123", "123.11111");
        Address address = Address.builder()
                .city("Manchester")
                .zipcode("12926-3874")
                .street("Red")
                .number(3)
                .geolocation(geolocation).build();

        RequestAddUser request = RequestAddUser.builder()
                .email("12e3@gmail.com")
                .username("Wayne")
                .phone("12345r67890")
                .password("10Wazzzza!")
                .name(name)
                .address(address).build();

        ResponseAddUser response = given()
                .body(request)
                .when()
                .post("/users")
                .then()
                .log().all()
                .extract().as(ResponseAddUser.class);

        assertThat(response, hasProperty("id"));
        assertThat(response.getId(), notNullValue());
    }

    @Test
    @Tag("pojo")
    @Disabled("падает из-за того что сайт на моках")
    @DisplayName("обновление данных пользователя")
    public void updateUserTest() {
        RequestAddUser user = createUser();
        String newMail = "new@mail.com";

        int id = given()
                .body(user)
                .post("/users")
                .then()
                .extract()
                .body()
                .jsonPath().getInt("id");

        user.setEmail(newMail);

        given()
                .body(user)
                .put("/users/" + id)
                .then()
                .log().all()
                .statusCode(200);

        GetSingleUserResponse response = given()
                .get("/users/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(GetSingleUserResponse.class);
        assertThat(response.getEmail(), equalTo(newMail));
    }

    @Test
    @DisplayName("удаление пользователя")
    public void deleteUserTest() {
        given()
                .delete("/users/6")
                .then()
                .statusCode(200);

    }

    @Test
    @DisplayName("авторизация пользователя. Вариант отправки просто карты вместо создания класса")
    public void authUserWithMapTest() {
        Map<String,String> map = new HashMap<>();
        map.put("username", "mor_2314");
        map.put("password", "83r5^_");
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("/auth/login")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @DisplayName("авторизация пользователя c созданием классов. Проверка содержания jwt токена")
    public void authUserWithClassTest() {
        AuthUserRequest request = AuthUserRequest.builder()
                .username("mor_2314")
                .password("83r5^_").build();

        String token = given()
                .contentType(ContentType.JSON)
                .body(request)
                .post("/auth/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        // парсинг токена
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getClaim("user").toString();
        assertThat(username, containsString(request.getUsername()));
    }
}
