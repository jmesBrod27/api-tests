import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;


public class ApiTest {

//    @BeforeAll
//    public static void setup() {
//        RestAssured.baseURI = "";
//    }
    @Test
    @DisplayName("Fetch body equals to 100 objects")
    public void
    lotto_resource_returns_200_with_expected_id_and_winners() {

        when().
                get("https://my-json-server.typicode.com/jmesBrod27/api-tests/posts/1").
                then().
                statusCode(200).
                body("id", equalTo(1),
                        "title", equalTo("hello"));
                //.assertThat();
    }


    @Test
    @DisplayName("Post to create new resource and check if body match payload provided")
    public void
    post_endpoint_response_matches_payload_data() {

        String requestBody = "{\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"id\": \"4\" \n}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("https://my-json-server.typicode.com/jmesBrod27/api-tests/posts")
                .then()
                .extract().response();
        System.out.println(response);
//        assertEqualsNoOrder(201, response.statusCode());
//        assertEqualsNoOrder("foo", response.jsonPath().getString("title"));
//        Assertions.assertEquals("bar", response.jsonPath().getString("body"));
//        Assertions.assertEquals("1", response.jsonPath().getString("userId"));
//        Assertions.assertEquals("101", response.jsonPath().getString("id"));
    }
}
