package otus.services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import otus.dto.PetDTO;

public class PetApi {

  private static final String BASE_URL = "https://petstore.swagger.io/v2";
  private static final String PET = "/pet";
  private static final String STATUS = "/findByStatus";

  public Response addNewPet(PetDTO pet) {
    return RestAssured.given()
        .baseUri(BASE_URL)
        .contentType(ContentType.JSON)
        .with()
        .body(pet)
        .log().all()
        .when()
        .post(PET);
  }

  public Response getPetById(Long petId) {
    return RestAssured.given()
        .baseUri(BASE_URL)
        .when()
        .get(PET + "/"+ petId);
  }

  public Response deletePetById(Long petId) {
    return RestAssured.given()
        .baseUri(BASE_URL)
        .when()
        .delete(PET + "/"+ petId);
  }

  public Response findPetByStatus(String status) {
    return RestAssured.given()
        .baseUri(BASE_URL)
        .queryParam("status", status)
        .when()
        .get(PET + STATUS);
  }
}
