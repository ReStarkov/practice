package restAssured.theard.fakeapi.pojo.tests;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import restAssured.theard.fakeapi.pojo.models.user.Address;
import restAssured.theard.fakeapi.pojo.models.user.Geolocation;
import restAssured.theard.fakeapi.pojo.models.user.Name;
import restAssured.theard.fakeapi.pojo.models.user.addUser.RequestAddUser;
import restAssured.theard.fakeapi.pojo.models.user.addUser.ResponseAddUser;
import restAssured.theard.fakeapi.pojo.models.user.authUser.AuthUserRequest;
import restAssured.theard.fakeapi.pojo.models.user.getAllUsers.ResponseItem;
import restAssured.theard.fakeapi.pojo.models.user.getSingleUser.GetSingleUserResponse;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

public class RefacorSimplePojoTests {
    //рефаторинг, вынемение в setUp базового url, добавление туда же логирование, вместо передачи в каждый тест log().all

    @BeforeAll
    private static void setUp() {
        RestAssured.baseURI = "https://fakestoreapi.com";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new AllureRestAssured());
    }

    private RequestAddUser createUser() {
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
                .statusCode(200);

        GetSingleUserResponse response = given()
                .get("/users/" + id)
                .then()
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
        Map<String, String> map = new HashMap<>();
        map.put("username", "mor_2314");
        map.put("password", "83r5^_");
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .post("/auth/login")
                .then()
                .statusCode(200);
    }

    @Test
    @Tag("pojo")
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
                .statusCode(200)
                .extract().jsonPath().getString("token");

        // парсинг токена
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getClaim("user").toString();
        assertThat(username, containsString(request.getUsername()));
    }

    @Test
    @Tag("pojo")
    @DisplayName("полученние данных по пользователю. Извлечение через класс")
    public void getSingleUserTest() {
        GetSingleUserResponse response = given()
                .when()
                .get("users/1")
                .then()
                .statusCode(200)
                .extract().as(GetSingleUserResponse.class);
        assertThat(response.getEmail(), equalTo("john@gmail.com"));
        assertThat(response.getAddress().getCity(), equalTo("kilcoole"));
    }

    @Test
    @Tag("pojo")
    @DisplayName("полученние данных по пользователю. Извлечение одного объекта из ответа в класс")
    public void getSingleUserTest2() {
        Name name = given()
                .when()
                .get("users/1")
                .then()
                .statusCode(200)
                .extract().jsonPath().getObject("name", Name.class);
        assertThat(name.getFirstname(), equalTo("john"));

    }

    @Test
    @Tag("pojo")
    @DisplayName("полученние данных по пользователю. проверка без извечения по пути")
    public void getSingleUserTest3() {
        given()
                .when()
                .get("users/1")
                .then()
                .statusCode(200)
                .body("name.firstname", equalTo("john"));
    }

    @Test
    @Tag("pojo")
    @DisplayName("Извлечение списка")
    public void getAllUsers() {
        List<ResponseItem> response = given()
                .when()
                .get("/users")
                .then()
                .extract()
                .jsonPath().getList("", ResponseItem.class);
        assertThat(response.get(0).getId(), equalTo(1));
    }

    @Test
    @Tag("pojo")
    @DisplayName("получение отсортированного списка по id")
    public void sortUsersTest() {
        String sortBy = "desc";
        List<ResponseItem> sortedResponse = given().queryParam("sort", sortBy)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("", ResponseItem.class);

        List<ResponseItem> notSortedResponse = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("", ResponseItem.class);

        //получение id из респонсов через стрим
        List<Integer> sortedIds = sortedResponse
                .stream().map(listElement -> listElement.getId())
                .collect(Collectors.toList());
        List<Integer> newSortedIds = notSortedResponse
                .stream().map(listElement -> listElement.getId())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        Assertions.assertEquals(sortedIds, newSortedIds);
    }

    @Test
    @Tag("pojo")
    @DisplayName("получение лимитированного списка")
    public void getLimitTest(){
        int limitSize = 3;
        List<ResponseItem> respose = given().queryParam("limit", limitSize)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("", ResponseItem.class);

        assertThat(respose, hasSize(limitSize));
    }

    @Tag("pojo")
    @DisplayName("получение лимитированного списка через провайдер")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10})
    public void getParameterizedLimitTest(int limitSize ){
        List<ResponseItem> respose = given().queryParam("limit", limitSize)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("", ResponseItem.class);

        assertThat(respose, hasSize(limitSize));
    }
}