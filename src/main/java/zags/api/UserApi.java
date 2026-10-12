package zags.api;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zags.api.models.UserCreateResponceApp;
import zags.api.models.UserCreateRequest;


import static io.restassured.RestAssured.given;

public class UserApi {
    private static final Logger log = LogManager.getLogger(UserApi.class);
    @Step("API: создать заявку, mode={user.mode}")
    public static UserCreateResponceApp sendUserRequest(UserCreateRequest user) {
        log.info("POST /sendUserRequest, mode={}", user.getMode());

        UserCreateResponceApp response = given()
                .spec(Specifications.reqSpec())
                .body(user)
                .when()
                .post(Endpoints.createUser)
                .then()
                .statusCode(200)
                .extract().body().jsonPath()
                .getObject("data", UserCreateResponceApp.class);
        log.info("Заявка создана: applicationid={}, applicantid={}, citizenid={}",
                response.getApplicationid(),
                response.getApplicantid(),
                response.getCitizenid());

        return response;
    }
}

