package zags.api;

import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    static Dotenv dotenv = Dotenv.load();
    static String username = dotenv.get("TEST_USERNAME");
    static String password = dotenv.get("TEST_PASSWORD");
    static String BASE_URL = "https://" + dotenv.get("BASE_URL");

    public static RequestSpecification reqSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .setAuth(RestAssured.preemptive().basic(username, password))
                .addFilter(new AllureRestAssured())
                .build();
    }

    public static ResponseSpecification resSpec(Integer code) {
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .build();
    }
}
