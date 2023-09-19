import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ApiTest {

    String baseUrl = "";
    int lengthOfList = 0;
    String requestBodyToPost = "";
    String requestBodyToPut = "";
    String requestBodyToPatch = "";

    private Properties prop;

    @BeforeClass
    public void setup() throws IOException {
        InputStream input = new FileInputStream("data.properties");
        prop = new Properties();

        prop.load(input);

        // Get all values
        baseUrl = prop.getProperty("baseUri");
        lengthOfList = Integer.parseInt(prop.getProperty("listLength"));
        requestBodyToPost = prop.getProperty("requestBodyToPost");
        requestBodyToPut = prop.getProperty("requestBodyToPut");
        requestBodyToPatch = prop.getProperty("requestBodyToPatch");
    }

    @Test
    public void get_post_details_and_check_they_equal_100_in_length() {
        String title ="sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
        String body ="quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto";

        ResponseAPI[] allResponseDetails = RestAssured.given().log().all()
                .when().get(baseUrl + "/posts").as(ResponseAPI[].class);
        System.out.println("no of results: " + allResponseDetails.length);

        Assert.assertEquals(allResponseDetails[0].getUserId(), 1);
        Assert.assertEquals(allResponseDetails[0].getId(), 1);
        Assert.assertEquals(allResponseDetails[0].getTitle(), title);
        Assert.assertEquals(allResponseDetails[0].getBody(), body);
        Assert.assertEquals(allResponseDetails.length, lengthOfList);

    }

    @Test
    public void get_post_details_and_check_they_equal_100_in_length_using_jsonPath() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseUrl + "/posts")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(response.jsonPath().getList("posts").size(), lengthOfList);
    }

    @Test
    public void post_endpoint_response_matches_payload_data() {

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBodyToPost)
                .when()
                .post(baseUrl + "/posts")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("userId"), "2");
        Assert.assertEquals(response.jsonPath().getString("id"), "101");

    }

    @Test
    public void put_endpoint_response_matches_payload_data() {

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBodyToPut)
                .when()
                .put(baseUrl + "/posts/2")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("userId"), "3");
        Assert.assertEquals(response.jsonPath().getString("id"), "2");
    }

    @Test
    public void delete_endpoint_resource() {

        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete(baseUrl + "/posts/2")
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void patch_endpoint_response_matches_payload_data() {

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBodyToPatch)
                .when()
                .patch(baseUrl + "/posts/9")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void with_different_properties() throws IOException {
        Properties differentProperties = new Properties();
        FileInputStream input = new FileInputStream("dataCopy.properties");
        differentProperties.load(input);
        input.close();

        // Re-run the tests with different properties
        prop = differentProperties;

        baseUrl = prop.getProperty("baseUri");
        lengthOfList = Integer.parseInt(prop.getProperty("listLength"));
        requestBodyToPost = prop.getProperty("requestBodyToPost");
        requestBodyToPut = prop.getProperty("requestBodyToPut");
        requestBodyToPatch = prop.getProperty("requestBodyToPatch");

        get_post_details_and_check_they_equal_100_in_length_using_jsonPath();
        get_post_details_and_check_they_equal_100_in_length_using_jsonPath();
        post_endpoint_response_matches_payload_data();
        put_endpoint_response_matches_payload_data();
        delete_endpoint_resource();
        patch_endpoint_response_matches_payload_data();
    }
}

