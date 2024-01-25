package restAssured.theard.fakeapi.pojo.tests;

import io.restassured.RestAssured;
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
import restAssured.theard.fakeapi.pojo.models.user.getSingleUser.GetSingleUserResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
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
                .delete("https://fakestoreapi.com/users/6")
                .then()
                .statusCode(200);

    }
}
