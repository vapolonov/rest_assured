package tests;

import static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import otus.dto.CategoryDTO;
import otus.dto.PetDTO;
import otus.dto.TagDTO;
import java.util.ArrayList;
import java.util.List;

public class AddNewPetTest extends BaseTest {

  /*
  1. Создание нового питомца
  2. Проверка создания питомца
  3. Удаление созданного питомца
  4. Проверка, что питомец удален
   */
  @Test
  public void checkAddNewPetTest() {

    CategoryDTO category = new CategoryDTO(1L, "Cats");

    ArrayList<TagDTO> tags = new ArrayList<>();
    tags.add(new TagDTO(1L, "friendly"));
    tags.add(new TagDTO(2L, "trained"));

    ArrayList<String> photoUrls = new ArrayList<>();
    photoUrls.add("photo1.jpg");
    photoUrls.add("photo2.jpg");

    PetDTO pet = PetDTO.builder()
        .id(123L)
        .name("NewPet")
        .status("available")
        .category(category)
        .photoUrls(photoUrls)
        .tags(tags)
        .build();

    Response addPet = petApi.addNewPet(pet);

    // проверяем данные ответа при создании пета
    addPet.then()
        .statusCode(HttpStatus.SC_OK)
        .body("category.name", equalTo("Cats"))
        .body("name", equalTo("NewPet"))
        .body("tags[0].name", equalTo("friendly"))
        .body("status", equalTo("available"))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/pet.json"));

    // получаем созданного пета по Id
    Response getPetById = petApi.getPetById(123L);
    getPetById.then()
        .statusCode(HttpStatus.SC_OK)
        .body("name", equalTo("NewPet"));

    // удаляем созданного пета
    Response deletePet = petApi.deletePetById(123L);
    deletePet.then()
        .log().all()
        .statusCode(HttpStatus.SC_OK);

    // проверяем, что пет по указанному Id не найден
    Response checkDeletedPet = petApi.getPetById(123L);
    checkDeletedPet.then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  // Найти питомца по статусу
  @Test
  public void petShouldBeFindByStatus() {
    Response response = petApi.findPetByStatus("available");
    response.then()
        .log().all()
        .statusCode(HttpStatus.SC_OK)
        // Проверка, что ответ не пустой массив
        .body("$", not(empty()))
        // Проверка, что это массив
        .body("$", instanceOf(List.class))
        // Проверка минимального количества
        .body("size()", greaterThan(1));
  }

  // Негативный тест, найти питомца по несуществующему id
  @Test
  public void petShouldNotBeFoundBYWrongPetId() {
    Response deletePet = petApi.deletePetById(123L);
    deletePet.then()
        .log().all()
        // проверка, что пет не найден
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }
}
