package otus.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import otus.services.PetApi;
import otus.services.UserApi;

public class ApiModule extends AbstractModule {

  private static final String API_URL = System.getProperty("api.url", "https://petstore.swagger.io/v2");

  @Override
  protected void configure() {
  }

  @Provides
  @Singleton
  public RequestSpecification requestSpec() {
    return RestAssured.given()
        .baseUri(API_URL)
        .contentType("application/json");
  }

  @Provides
  @Singleton
  public PetApi getPetApi(RequestSpecification request) {
    return new PetApi(request);
  }

  @Provides
  @Singleton
  public UserApi getUserApi(RequestSpecification request) {
    return new UserApi(request);
  }
}
