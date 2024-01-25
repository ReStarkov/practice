package restAssured.theard.fakeapi;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class SimpleTests {

    public final String basePath= "https://fakestoreapi.com/users";

    @Test
    @DisplayName("проверка что длина вернувшегося массива равна 10")
    public void getAllUsersTest(){
        given()
                .log()
                .all()
                .when()
                .get(basePath)
                .then()
                .statusCode(200)
                .body("", hasSize(10));
    }

    @Test
    @DisplayName("получение данных по пользователю, проверка города и формата зип-кода")
    public void getSingleUserTest(){
        int id = 2;
        given()
                .log()
                .all()
                .when().pathParam("userId", id)
                .get(basePath+"/{userId}")
                .then()
                .log().all()
                .statusCode(200)
                .body("address.city", equalTo("kilcoole"))
                .body("address.zipcode", Matchers.matchesPattern("\\d{5}-\\d{4}")); // 12926-3874
    }

    @Test
    @DisplayName("получение списка пользователей с лимитом")
    public void getUsersWithLimitTest(){
        int limit = 2;
        given().queryParam("limit", limit)
                .when()
                .get(basePath)
                .then().log().all()
                .body("", hasSize(limit));
    }

    @Test
    @DisplayName("получение отсортированного списка по id")
    public void getSortUsersTest(){
        String sortBy = "desc";
        Response sortedResponse = given().queryParam("sort", sortBy)
                .when()
                .get(basePath)
                .then().log().all()
                .statusCode(200)
                .extract().response();

        Response notSortedResponse = given()
                .when()
                .get(basePath)
                .then().log().all()
                .statusCode(200)
                .extract().response();

        //получение id из респонсов
        List<Integer>sortedIds = sortedResponse.jsonPath().getList("id");
        List<Integer>notSortedIds = notSortedResponse.jsonPath().getList("id");

        //сортировка по убыванию неотсортированного списка id
/*      Collections.sort(notSortedIds);
        Collections.reverse(notSortedIds);*/
        List<Integer>sortedByCode = notSortedIds.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Assertions.assertEquals(sortedIds,sortedByCode);
    }
}
