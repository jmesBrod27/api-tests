import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {

    @Test
    @DisplayName("Fetch body equals to 100 objects")
    public void getPostDetailsAndCheckTheyEqual100InLength()
    {

        ResponseAPI[] allResponseDetails = RestAssured.given().log().all()
                .when().get("https://jsonplaceholder.typicode.com/posts").as(ResponseAPI[].class);
        System.out.println("no of results: "+ allResponseDetails.length);

        Assert.assertEquals(allResponseDetails[0].getUserId(),1);
        Assert.assertEquals(allResponseDetails[0].getId(),1);
        Assert.assertEquals(allResponseDetails[0].getTitle(),"sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        Assert.assertEquals(allResponseDetails[0].getBody(),"quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
        Assert.assertEquals(allResponseDetails.length,100);

    }

    @Test
    @DisplayName("Post to create new resource and check if body matches the payload provided")
    public void
    post_endpoint_response_matches_payload_data() {

        String requestBody = "{\n" +
                "  \"userId\": \"2\",\n" +
                "  \"title\": \"batman\",\n" +
                "  \"body\": \"Wayne\",\n" +
                "  \"id\": \"102\" \n}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.jsonPath().getString("title"),"batman" );
        Assert.assertEquals(response.jsonPath().getString("body"), "Wayne");
        Assert.assertEquals(response.jsonPath().getString("userId"),"2");
        Assert.assertEquals(response.jsonPath().getString("id"),"101");

    }
    @Test
    @DisplayName("Update resource and check if body matches the payload provided")
    public void
    put_endpoint_response_matches_payload_data() {

        String requestBody = "{\n" +
                "  \"userId\": \"3\",\n" +
                "  \"title\": \"superman\",\n" +
                "  \"body\": \"Clark\",\n" +
                "  \"id\": \"2\" \n}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/2")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("title"),"superman" );
        Assert.assertEquals(response.jsonPath().getString("body"), "Clark");
        Assert.assertEquals(response.jsonPath().getString("userId"),"3");
        Assert.assertEquals(response.jsonPath().getString("id"),"2");
    }

    @Test
    @DisplayName("delete a resource")
    public void
    delete_endpoint_resource() {

        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("https://jsonplaceholder.typicode.com/posts/2")
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    @DisplayName("Patch a resource and check if body matches the payload provided")
    public void
    patch_endpoint_response_matches_payload_data() {


        String requestBody = "{\n" +
                "  \"title\": \"flash\"}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .patch("https://jsonplaceholder.typicode.com/posts/9")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("title"),"flash");
        }
    }

