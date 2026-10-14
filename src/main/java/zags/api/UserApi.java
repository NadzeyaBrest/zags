package zags.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zags.api.models.UserCreateRequest;


import static io.restassured.RestAssured.given;

public class UserApi {
    private static final Logger log = LogManager.getLogger(UserApi.class);

    @Step("API: создать заявку, mode={user.mode}")
    public static Response sendUserRequest(UserCreateRequest user) {
        log.info("POST /sendUserRequest, mode={}", user.getMode());
        Response response = given()
                .spec(Specifications.reqSpec())
                .body(user)
                .when()
                .post(Endpoints.createUser);
        log.info("Ответ: status={}", response.getStatusCode());
        return response;
    }
}

