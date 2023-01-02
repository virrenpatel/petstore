package io.swagger.petstore.curdtest;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.testbase.TestBase;
import io.swagger.petstore.userinfo.UserSteps;
import io.swagger.petstore.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCURDTestWithSteps extends TestBase {
    static String username = "MananPatel";
    static String firstName = "Manan" + TestUtils.getRandomValue();
    static String lastName = "Shah";
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static String status = "0";
    static String password = "password1";
    static String phone = "020232323";

    static int settId = 122;
    @Steps
    UserSteps userSteps;

    @Title("This will create a new User")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createUser(settId,username, firstName, lastName, email, status, password, phone, settId);
        response.log().all().statusCode(200);

    }

    @Title("Verify if the student was added to the application")
    @Test
    public void test002(){
        HashMap<String, Object> userMap = userSteps.getUserInfoByuserName(firstName);
        Assert.assertThat(userMap, hasValue(firstName));
        firstName = (String) userMap.get(firstName);

    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        username = username + "_updated";
        userSteps.updateUser(settId,username,firstName,lastName,email,password,phone,status)
                .log().all().statusCode(200);

        HashMap<String, Object> studentMap = userSteps.getUserInfoByuserName(username);
        Assert.assertThat(studentMap, hasValue(username));

    }

}
