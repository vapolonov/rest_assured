package org.example.services;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.dto.CreateNewUserDTO;

import static io.restassured.RestAssured.given;

public class PetstoreApi {
  private static final String BASE_URL = "https://petstore.swagger.io/v2";
  private static final String USER = "/user";
  private RequestSpecification spec;

  public PetstoreApi() {
    spec = given()
        .baseUri(BASE_URL)
        .contentType(ContentType.JSON)
        .log().all();
  }

  public ValidatableResponse createUser(CreateNewUserDTO userDTO) {

    return given(spec)
        .body(userDTO)
        .when()
        .post(USER)
        .then()
        .log().all();
  }
}
