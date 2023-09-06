package org.utils;

import io.restassured.http.ContentType;
import org.basetest.Constant;
import org.product.Product;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIUtils {
    public static void getProductResponse() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .get(Constant.API_FOOD)
                .then()
                .assertThat()
                .statusCode(200)
                .headers("Content-Type", equalTo("application/json"));
    }

    public static void postProduct(Product product) {
        given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post(Constant.API_FOOD)
                .then()
                .assertThat()
                .statusCode(200);
    }

    public static void resetProduct() {
        given()
                .when()
                .post(Constant.RESET)
                .then()
                .assertThat()
                .statusCode(200);
    }

    public static String getJSessionId() {
        String jsessionid = given()
                .when()
                .contentType(ContentType.JSON)
                .get(Constant.API_FOOD)
                .then()
                .extract()
                .cookie("JSESSIONID");
        return jsessionid;
    }

    public static Product getLastProduct() {
        Product[] productList = given()
                .when()
                .get(Constant.API_FOOD)
                .as(Product[].class);

        return productList[productList.length - 1];
    }
}
