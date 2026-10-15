package zags.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zags.api.models.AdminProcessRequest;
import zags.api.models.AdminRequest;

import static io.restassured.RestAssured.given;

public class AdminApi {

    private static final Logger log = LogManager.getLogger(AdminApi.class);

    @Step("API: создать админа")
    public static Response sendAdminRequest(AdminRequest admin) {
        log.info("POST /sendAdminRequest");
        Response response = given()
                .spec(Specifications.reqSpec())
                .body(admin)
                .when()
                .post(Endpoints.createAdmin);
        log.info("Админ создан: статус коде ={}", response.getStatusCode());
        return response;
    }


    @Step("API: обработать заявку {request.applId}, action={request.action}")
    public static Response processApplication(AdminProcessRequest request) {
        log.info("POST /requestProcess, applId={}, action={}",
                request.getApplId(), request.getAction());
        Response response = given()
                .spec(Specifications.reqSpec())
                .body(request)
                .when()
                .post(Endpoints.changeApplicationStatus);
        log.info("Ответ: status={}", response.getStatusCode());
        return response;
    }

    @Step("API: получить заявку по id={appId}")
    public static Response  getApplicationById(Integer appId) {
        log.info("GET /getApplStatus/{}", appId);
        Response response = given()
                .spec(Specifications.reqSpec())
                .when()
                .pathParams("applicationId", appId)
                .get(Endpoints.getApplicationById);
        log.info("Ответ: status={}", response.getStatusCode());
        return response;
    }

    @Step("API: получить все заявки")
    public static  Response  getAllApplication() {
        log.info("GET /getApplications — получить все заявки");
        Response  response = given()
                .spec(Specifications.reqSpec())
                .when()
                .get(Endpoints.getApplications);
        log.info("Ответ: status={}", response.getStatusCode());
        return response;
    }
}

