package otus.services;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import otus.dto.UserDTO;

public class UserApi {
  private static final String BASE_URL = "https://petstore.swagger.io/v2";
  private static final String USER = "/user";
  private RequestSpecification spec;

  public UserApi() {
    spec = given()
        .baseUri(BASE_URL)
        .contentType(ContentType.JSON);
  }

  public Response createUser(UserDTO user) {
    return
        given(spec)
            .with()
            .body(user)
            .log().all()
            .when()
            .post(USER);
  }
}
