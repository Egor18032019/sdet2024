package helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import models.AdditionRequest;
import models.EntityRequest;
import models.EntityResponse;
import models.EntityResponseArray;
import org.apache.http.HttpStatus;
import org.testng.asserts.SoftAssert;
import utils.EndPoint;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Steps {

    static RequestSpecification requestSpecification = BaseRequest.initRequestSpecification();

    @Step("Запрос на получение одной сущности c id = {id}")
    public static EntityResponse getEntityRequest(String id) {
        String response = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .get(id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readerFor(EntityResponse.class).readValue(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Запрос на получение списка сущностей")
    public static EntityResponseArray getAllEntityRequest() {
        String response = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .get(EndPoint.getAll)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readerFor(EntityResponseArray.class).readValue(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Step("Отправка сущности на /create ")
    public static String postEntityRequest(EntityRequest request) {
        String response = RestAssured.given()
                .spec(requestSpecification)
                .body(request)
                .when()
                .post(EndPoint.create)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();
        return response;
    }

    @Step("Создание сущности")
    public static EntityRequest createEntityRequest() {

        AdditionRequest additionRequest = AdditionRequest.builder()
                .additionalNumber(ThreadLocalRandom.current().nextInt())
                .additionalInfo("Дополнительная информация")
                .build();

        EntityRequest request = EntityRequest.builder()
                .title(String.valueOf(UUID.randomUUID()))
                .verified(true)
                .addition(additionRequest)
                .importantNumbers(new Integer[]{1, 2, 3})
                .build();

        return request;
    }


    @Step("Удаление сущности c id = {id}")
    public static String deleteEntityOnServer(String id) {
        String response = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .delete(id)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .extract()
                .asString();

        return response;
    }

    @Step("Обновление сущности и ее дополнений")
    public static void patchEntityRequest(EntityRequest request, String id) {
        RestAssured.given()
                .spec(requestSpecification)
                .body(request)
                .when()
                .patch(id)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .extract()
                .asString();
    }

    @Step("Сравнение двух сущностей")
    public static boolean isEquals(EntityRequest request, EntityResponse response) {
        SoftAssert asert = new SoftAssert();
        asert.assertTrue(request.getTitle().equals(response.getTitle()), "различаются title");
        asert.assertTrue(request.getVerified().equals(response.getVerified()), "различаются verified");
        asert.assertTrue(Arrays.equals(request.getImportantNumbers(), response.getImportantNumbers()), "различаются importantNumbers");
        asert.assertTrue(request.getAddition().getAdditionalInfo().equals(response.getAddition().getAdditionalInfo()), "различаются additional_indo в addition");
        asert.assertTrue(request.getAddition().getAdditionalNumber().equals(response.getAddition().getAdditionalNumber()), "различаются additional_indo в addition");
        asert.assertAll();
        return true;
    }

}
