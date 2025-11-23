package tests;

import otus.services.PetApi;
import otus.services.UserApi;

public class BaseTest {
  protected UserApi userApi = new UserApi();
  protected PetApi petApi = new PetApi();
}
