package org.example.services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.dto.User;

import static io.restassured.RestAssured.given;

public class UserApi {
  private static final String BASE_URL = "https://petstore.swagger.io/v2";
  private static final String USER = "/user";
  private RequestSpecification spec;

  /*   public void exampleTest() {
        given()
                .baseUri("https://petstore.swagger.io/v2")  //https://petstore.swagger.io/v2{userName}
                .header("Content-Type", "application/json")
                .queryParam
                .queryParam
                .basePath("user")
                .param("userName", "testUser")
                .log().all()
                .body("")
        .when()
                .post()
        .then()
                .log().all();
    }*/

  public UserApi() {
    spec = given()
        .baseUri(BASE_URL)
        .contentType(ContentType.JSON);
  }

  public Response createUser(User user) {
    return
        given(spec)
            .with()
            .body(user)
            .log().all()
            .when()
            .post(USER);
  }
}
