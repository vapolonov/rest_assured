package otus.services;

import com.google.inject.Inject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import otus.dto.PetDTO;

public class PetApi {

  private final RequestSpecification request;

  private static final String PET = "/pet";
  private static final String STATUS = "/findByStatus";

  @Inject
  public PetApi(RequestSpecification request) {
    this.request = request;
  }

  public Response addNewPet(PetDTO pet) {
    return request
        .contentType(ContentType.JSON)
        .with()
        .body(pet)
        .log().all()
        .when()
        .post(PET);
  }

  public Response getPetById(Long petId) {
    return request
        .when()
        .get(PET + "/"+ petId);
  }

  public Response deletePetById(Long petId) {
    return request
        .when()
        .delete(PET + "/"+ petId);
  }

  public Response findPetByStatus(String status) {
    return request
        .queryParam("status", status)
        .when()
        .get(PET + STATUS);
  }
}
