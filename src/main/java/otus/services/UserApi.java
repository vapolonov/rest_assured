package otus.services;

import com.google.inject.Inject;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import otus.dto.UserDTO;

public class UserApi {

  private final RequestSpecification request;
  private static final String USER = "/user";

  @Inject
  public UserApi(RequestSpecification request) {
    this.request = request;
  }

  public Response createUser(UserDTO user) {
    return request
            .with()
            .body(user)
            .log().all()
            .when()
            .post(USER);
  }
}
