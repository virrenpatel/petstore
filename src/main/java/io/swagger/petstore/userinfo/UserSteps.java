package io.swagger.petstore.userinfo;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.constants.EndPoints;
import io.swagger.petstore.model.UserPojo;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {

    @Step("Create user with userName : {0}, firstName:{1}, lastName: {2},email:{3}")
    public ValidatableResponse createUser(int id ,String username, String firstName, String lastName, String email, String status, String setPassword, String setPhone, int userID) {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(122);
        userPojo.setUsername(username);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(setPassword);
        userPojo.setPhone(setPhone);
        userPojo.setUserStatus(status);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .header("Authorization", "special-key")
                //.contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }

    @Step("Getting the user information with firstName: {0}")

    public HashMap<String, Object> getUserInfoByuserName(String firstName){
        String p1 = "findAll{it.firstName == '";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .header("Authorization", "special-key")
                .when()
                .get(EndPoints.GET_ALL_USER)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + firstName + p2);
    }

    @Step("Updating User information with userName: {0}, firstName: {1}, lastName: {2}, email: {3}, programme: {4} and courses: {5}")

    public ValidatableResponse updateUser(int settId, String username, String firstName, String lastName, String email, String password, String phone, String status){

        UserPojo userPojo = UserPojo.getUserPojo(username,firstName,lastName,email,password,phone,status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .pathParam("userName", username)
                .body(userPojo)
                .when()
                .put("/user/Manana")
                .then();

    }
}



