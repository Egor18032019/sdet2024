package tests;

import helpers.Steps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import models.EntityRequest;
import models.EntityResponse;
import models.EntityResponseArray;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.EndPoint;

import java.util.Arrays;
import java.util.List;

public class ApiRequestsTest {
    private String idForDelete;
    private static final String decimetre = "/";

    @AfterMethod
    public void afterMethod(ITestResult result, ITestContext context) {
        if (idForDelete == null) return;
        if (!result.getMethod().getMethodName().equalsIgnoreCase("deleteOneFromApi")) {
            Steps.deleteEntityOnServer(EndPoint.delete + decimetre + idForDelete);
        } else {
            try {
                Steps.deleteEntityOnServer(EndPoint.delete + decimetre + idForDelete);
            } catch (AssertionError assertionError) {
                String[] message = assertionError.getMessage().split("\n");
                if (!message[0].equals("1 expectation failed.")) {
                    System.out.println("Ошибок больше чем ожидалось: " + assertionError.getMessage());
                }
                if (!message[1].equals("Expected status code <204> but was <500>.")) {
                    System.out.println("Неожиданный статус ошибки чем ожидалось: " + assertionError.getMessage());
                }
            }
        }
        idForDelete = null;
    }

    @Test
    @Description("Тест на /api/get/")
    @Epic(value = "GET")
    public void getOneFromApi() {
        EntityRequest request = Steps.createEntityRequest();
        String id = Steps.postEntityRequest(request);
        EntityResponse response = Steps.getEntityRequest(EndPoint.get + decimetre + id);
        boolean isEqualsEntity = Steps.isEquals(request, response);

        Assert.assertTrue(isEqualsEntity, "Сервер вернул измененный предварительно записанный запрос.");
        idForDelete = id;
    }

    @Test
    @Description("Тест на GET по /api/getAll")
    @Epic(value = "GET")
    public void getAllFromApi() {
        EntityRequest request = Steps.createEntityRequest();
        String responseID = Steps.postEntityRequest(request);
        Integer id = Integer.valueOf(responseID);
        EntityResponseArray entityResponseArray = Steps.getAllEntityRequest();
        List<EntityResponse> entityResponseList = Arrays.stream(entityResponseArray.getEntity())
                .filter(
                        o -> o.getId().equals(id)
                )
                .toList();
        boolean isOneElement = entityResponseList.size() == 1;

        Assert.assertTrue(isOneElement, "В списке не содержится предварительно записанная сущность.");
        idForDelete = responseID;
    }

    @Test
    @Description("Тест на POST по /api/create")
    @Epic(value = "POST")
    public void postOneFromApi() {
        EntityRequest request = Steps.createEntityRequest();
        String responseID = Steps.postEntityRequest(request);
        EntityResponse entityFromServer = Steps.getEntityRequest(EndPoint.get + decimetre + responseID);
        boolean isUpdate = Steps.isEquals(request,entityFromServer);

        Assert.assertTrue(isUpdate, "Отправленное на сервер не равно пришедшему с сервера.");
        idForDelete = responseID;
    }

    @Test
    @Description("Тест на DELETE по /api/delete")
    @Epic(value = "DELETE")
    public void deleteOneFromApi() {
        EntityRequest request = Steps.createEntityRequest();
        String id = Steps.postEntityRequest(request);
        String response = Steps.deleteEntityOnServer(EndPoint.delete + decimetre + id);

        Assert.assertNotNull(response, "что то пошло не так");
        idForDelete = id;
    }

    @Test
    @Description("Тест на PATCH по /api/patch")
    @Epic(value = "PATCH")
    public void patchOneFromApi() {
        EntityRequest request = Steps.createEntityRequest();
        String id = Steps.postEntityRequest(request);
        EntityRequest requestForUpdateBeforeWriteOnServer = Steps.createEntityRequest();
        Steps.patchEntityRequest(requestForUpdateBeforeWriteOnServer, EndPoint.patch + decimetre + id);
        EntityResponse entityAfterUpdateFromServer = Steps.getEntityRequest(EndPoint.get + decimetre + id);
        boolean isUpdate = Steps.isEquals(requestForUpdateBeforeWriteOnServer, entityAfterUpdateFromServer);

        Assert.assertTrue(isUpdate);
        idForDelete = id;
    }
}
