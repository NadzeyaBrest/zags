package zags.api;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zags.api.models.AdminProcessRequest;
import zags.api.models.AdminRequest;
import zags.api.models.ApplicationByIdResponse;
import zags.api.models.ApplicationResponseForAdmin;
import zags.api.models.CreateAdminResponse;


import java.util.List;

import static io.restassured.RestAssured.given;

public class AdminApi {

    private static final Logger log = LogManager.getLogger(AdminApi.class);

    @Step("API: создать админа")
    public static CreateAdminResponse sendAdminRequest(AdminRequest admin) {
        log.info("POST /sendAdminRequest");
        CreateAdminResponse response = given()
                .spec(Specifications.reqSpec())
                .body(admin)
                .when()
                .post(Endpoints.createAdmin)
                .then()
                .statusCode(200)
                .extract().body().jsonPath()
                .getObject("data", CreateAdminResponse.class);
        log.info("Админ создан: staffid={}", response.getStaffid());
        return response;
    }

    @Step("API: обработать заявку {request.applId}, action={request.action}")
    public static ApplicationResponseForAdmin processApplication(AdminProcessRequest request) {
        log.info("POST /requestProcess, applId={}, action={}",
                request.getApplId(), request.getAction());

        ApplicationResponseForAdmin response = given()
                .spec(Specifications.reqSpec())
                .body(request)
                .when()
                .post(Endpoints.changeApplicationStatus)
                .then()
                .statusCode(200)
                .extract().body().jsonPath()
                .getObject("data", ApplicationResponseForAdmin.class);
        log.info("Статус заявки {} изменён на '{}'",
                response.getApplicationid(), response.getStatusofapplication());
        return response;
    }

    @Step("API: получить заявку по id={appId}")
    public static ApplicationByIdResponse getApplicationById(Integer appId) {
        log.info("GET /getApplStatus/{}", appId);
        ApplicationByIdResponse response = given()
                .spec(Specifications.reqSpec())
                .when()
                .pathParams("applicationId", appId)
                .get(Endpoints.getApplicationById)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject("data", ApplicationByIdResponse.class);
        log.info("Статус заявки {}: '{}'", appId, response.getStatusofapplication());
        return response;
    }

    @Step("API: получить все заявки")
    public static List<ApplicationResponseForAdmin> getAllApplication() {
        log.info("GET /getApplications — получить все заявки");
        List<ApplicationResponseForAdmin> applications = given()
                .spec(Specifications.reqSpec())
                .when()
                .get(Endpoints.getApplications)
                .then()
                .statusCode(200)
                .extract().body().jsonPath()
                .getList("data", ApplicationResponseForAdmin.class);
        log.info("Получено заявок: {}", applications.size());
        return applications;
    }
}

