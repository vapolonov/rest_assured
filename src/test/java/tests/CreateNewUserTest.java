package tests;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.example.dto.CreateNewUserDTO;
import org.example.dto.CreateNewUserResponseDTO;
import org.example.services.PetstoreApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateNewUserTest {


  @Test
  public void createNewUser() {
    PetstoreApi petstoreApi = new PetstoreApi();

    CreateNewUserDTO userDTO = CreateNewUserDTO.builder()
        .id(400L)
        .firstName("FirstName")
        .lastName("LastName")
        .email("mail@mail.ru")
        .userStatus(505L)
        .build();

    petstoreApi.createUser(userDTO)
        .statusCode(HttpStatus.SC_OK)
        .body("code", equalTo(200))
        .body("type", equalTo("unknown"))
        .body("message", equalTo("400"));
    //.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));

    CreateNewUserResponseDTO response = petstoreApi.createUser(userDTO)
        .extract().body().as(CreateNewUserResponseDTO.class);

            /*    Assertions.assertAll(
            ()->  Assertions.assertEquals(200, response.getCode(), "Incorrect code"),
            ()-> Assertions.assertEquals("unknown", response.getType(), "Incorrect type"),
            ()->Assertions.assertEquals("400", response.getMessage(), "Incorrect message")
        );*/

    Assertions.assertEquals(200, response.getCode(), "Incorrect code");
    Assertions.assertEquals("unknown", response.getType(), "Incorrect type");
    Assertions.assertEquals("400", response.getMessage(), "Incorrect message");

    String actualType = petstoreApi.createUser(userDTO).extract().body().jsonPath().get("type");
    Assertions.assertEquals("unknown", actualType);
  }
}
