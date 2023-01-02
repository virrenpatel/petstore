package io.swagger.petstore.curdtest;

import io.restassured.response.Response;
import io.swagger.petstore.model.UserPojo;
import io.swagger.petstore.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    @Test
    public void createUser(){
        UserPojo userPojo = new UserPojo();
        userPojo.setId(234);
        userPojo.setUsername("Mandaan Shah");
        userPojo.setFirstName("Maanan");
        userPojo.setLastName("Shaah");
        userPojo.setEmail("12233@gmail.com");
        userPojo.setPassword("password1");
        userPojo.setPhone("0203384346");
        userPojo.setUserStatus("0");

        Response response = given()
                .header("Content-Type", "application/json")
                //  .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post("/user");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void updateUser(){
        UserPojo userPojo = new UserPojo();
        userPojo.setId(12);
        userPojo.setUsername("Mandaan Saahah");
        userPojo.setFirstName("Maannan");
        userPojo.setLastName("Shaah");
        userPojo.setEmail("1222233@gmail.com");
        userPojo.setPassword("password1");
        userPojo.setPhone("0203384346");
        userPojo.setUserStatus("0");
        Response response = given()
                .header("Content-Type", "application/json")
                //  .contentType(ContentType.JSON)

                .body(userPojo)
                .when()
                .put("/user/12");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void getUserInfo() {
        Response response = given()
                .header("accept", "application/json")
                .when()
                .get("/user/Mandaan Saahah");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteUser() {
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .delete("/user/Mandaan Shah");
        response.then().statusCode(200);
        response.prettyPrint();

    }
}
