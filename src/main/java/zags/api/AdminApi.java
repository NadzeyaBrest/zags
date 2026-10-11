package zags.api;

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


    public static CreateAdminResponse sendAdminRequest(AdminRequest admin) {
        log.info("POST /sendAdminRequest");
        CreateAdminResponse response = given()
                .spec(Specifications.reqSpec())
                .body(admin)
                .when()
                .post(Endpoints.createAdmin)
                .then()
                .spec(Specifications.resSpec(200))
                .extract().body().jsonPath()
                .getObject("data", CreateAdminResponse.class);
        log.info("Админ создан: staffid={}", response.getStaffid());
        return response;
    }

    public static ApplicationResponseForAdmin processApplication(AdminProcessRequest request) {
        log.info("POST /requestProcess, applId={}, action={}",
                request.getApplId(), request.getAction());

        ApplicationResponseForAdmin response = given()
                .spec(Specifications.reqSpec())
                .body(request)
                .when()
                .post(Endpoints.changeApplicationStatus)
                .then()
                .spec(Specifications.resSpec(200))
                .extract().body().jsonPath()
                .getObject("data", ApplicationResponseForAdmin.class);
        log.info("Статус заявки {} изменён на '{}'",
                response.getApplicationid(), response.getStatusofapplication());
        return response;
    }

    public static List<ApplicationResponseForAdmin> getInfoForOneApplication() {
        log.debug("GET /getApplications?limit=1");
        List<ApplicationResponseForAdmin> applications = given()
                .spec(Specifications.reqSpec())
                .queryParam("limit", 1)
                .when()
                .get(Endpoints.getApplications)
                .then()
                .spec(Specifications.resSpec(200))
                .extract().body().jsonPath()
                .getList("data", ApplicationResponseForAdmin.class);
        log.debug("Получено заявок: {}", applications.size());
        return applications;
    }

    public static ApplicationByIdResponse getApplicationById(Integer appId) {
        log.info("GET /getApplStatus/{}", appId);
        ApplicationByIdResponse response = given()
                .spec(Specifications.reqSpec())
                .when()
                .pathParams("applicationId", appId)
                .get(Endpoints.getApplicationById)
                .then()
                .spec(Specifications.resSpec(200))
                .extract().body().jsonPath().getObject("data", ApplicationByIdResponse.class);
        log.info("Статус заявки {}: '{}'", appId, response.getStatusofapplication());
        return response;
    }

    public static List<ApplicationResponseForAdmin> getAllApplication() {
        log.info("GET /getApplications — получить все заявки");
        List<ApplicationResponseForAdmin> applications = given()
                .spec(Specifications.reqSpec())
                .when()
                .get(Endpoints.getApplications)
                .then()
                .spec(Specifications.resSpec(200))
                .extract().body().jsonPath()
                .getList("data", ApplicationResponseForAdmin.class);
        log.info("Получено заявок: {}", applications.size());
        return applications;
    }
}

