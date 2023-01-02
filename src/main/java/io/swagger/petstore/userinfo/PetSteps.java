package io.swagger.petstore.userinfo;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.constants.EndPoints;
import io.swagger.petstore.model.PetPojo;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class PetSteps {
    @Step("Create Pet with PetId:{0},category:{1},name:{2},photoUrls{3},tags:{4},status:{5}")

    public ValidatableResponse createPet(int id, HashMap<String,Object> category, String name, List<String> photoUrls, List<HashMap<String,Object>> tags, String status) {
        PetPojo petPojo = new PetPojo();
        petPojo.setCategory(category);
        petPojo.setName(name);
        petPojo.setPhotoUrls(photoUrls);
        petPojo.setTags(tags);
        petPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(petPojo)
                .when()
                .post()
                .then();
    }

    @Step("Get Pet details with name  :{0}")

    public HashMap<String,Object>getPetDetailsByname(String name){
        String p1 ="findAll{it.name =='";
        String p2 ="'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.Get_all_Pet)
                .then()
                .statusCode(200)
                .extract()
                .path(p1+name +p2);
    }
    @Step("Update Pet details with PetId:{0},category:{1},name:{2},photoUrls{3},tags:{4},status:{5}")

    public ValidatableResponse updatePet(int petID, HashMap<String,Object>category, String name, List<String> photoUrls, List<HashMap<String,Object>> tags,String status) {
        PetPojo petPojo = new PetPojo();
        petPojo.setId(petID);
        petPojo.setCategory(category);
        petPojo.setName(name);
        petPojo.setPhotoUrls(photoUrls);
        petPojo.setTags(tags);
        petPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("petId", petID)
                .body(petPojo)
                .when()
                .put(EndPoints.Update_Pet_By_ID)
                .then();
    }
    @Step("Delete Pet details with petId :{0}")

    public ValidatableResponse deletePet(int petId){
        return SerenityRest.given().log().all()
                .pathParam("petId",petId)
                .when()
                .delete(EndPoints.Delete_Pet_BY_ID)
                .then();
    }

    @Step("Get Pet details with petId :{0}")

    public ValidatableResponse getPetById(int petId){
        return SerenityRest.given().log().all()
                .pathParam("petId",petId)
                .when()
                .get(EndPoints.Get_Single_Pet_By_ID)
                .then();
    }
}
