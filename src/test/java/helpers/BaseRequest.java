package helpers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.EndPoint;

public class BaseRequest {

    public static RequestSpecification initRequestSpecification() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setBaseUri(EndPoint.api)
                .setAccept(ContentType.JSON);

        return requestSpecBuilder.build();
    }
}
