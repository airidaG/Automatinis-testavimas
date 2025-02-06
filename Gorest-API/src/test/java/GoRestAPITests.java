import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.notNullValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GoRestAPITests {

    private static final String BASE_URL = "https://gorest.co.in/public/v2/users";
    private static final String CONTENT_TYPE = "application/json";
    private static int createdUserId;
    private static final String GENDER = "male";
    private static final String STATUS = "active";
    private static final String NAME = "Garfield The Orange";
    private static final String TOKEN = "a3ec5c0b3019a512614c74e1c13725e5c7960e264b1d7dbdaee13e767a604036";


    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Order(1)
    void getAllUsers() {

        given()
                .when()
                .get()
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .header("Content-Type", containsString(CONTENT_TYPE))
                .time(lessThan(5000L));

    }

//    @Test
//    @Order(2)
//    void getUserById() {
//
//        int userId = 7670438;
//
//        given()
//                .when()
//                .get("/" + userId)
//                .then()
//                .log()
//                .body()
//                .assertThat()
//                .statusCode(200)
//                .body("id", notNullValue())
//                .body("id", equalTo(userId))
//                .body("gender", equalTo("male"))
//                .body("status", equalTo("active"))
//                .header("Content-Type", containsString(CONTENT_TYPE))
//                .time(lessThan(5000L));
//
//    }

    @Test
    @Order(3)
    void postUser() {

        User user = new User(Util.getRandomEmail(5), NAME, GENDER, STATUS);

        createdUserId =
                given()
                        .headers("Authorization", "Bearer " + TOKEN)
                        .contentType(ContentType.JSON)
                        .body(user)
                        .when()
                        .post()
                        .then()
                        .log().body()
                        .assertThat()
                        .statusCode(201)
                        .body("id", notNullValue())
                        .body("name", equalTo(user.getName()))
                        .body("email", equalTo(user.getEmail()))
                        .body("gender", equalTo(user.getGender()))
                        .body("status", equalTo(user.getStatus()))
                        .header("Content-Type", containsString(CONTENT_TYPE))
                        .time(lessThan(5000L))
                        .extract().path("id");

    }

    @Test
    @Order(4)
    void getJustCreatedUser() {

        given()
                .headers("Authorization", "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .get("/" + createdUserId)
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(createdUserId))
                .body("name", equalTo(NAME))
                .body("gender", equalTo(GENDER))
                .body("status", equalTo(STATUS))
                .header("Content-Type", containsString(CONTENT_TYPE))
                .time(lessThan(5000L));
    }

    @Test
    @Order(5)
    void deleteUserById() {
        given()
                .headers("Authorization", "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .delete("/" + createdUserId)
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(204)
                .time(lessThan(5000L));
    }

    @Test
    @Order(6)
    void verifyUserDeletion() {

        given()
                .headers("Authorization", "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .get("/" + createdUserId)
                .then()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Resource not found"));
    }
}
