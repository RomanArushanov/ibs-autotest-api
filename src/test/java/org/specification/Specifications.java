package org.specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Specifications {

    public static RequestSpecification requestSpecification(String BaseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(BaseUri)
                .build();
    }

    public static void installSpecification(RequestSpecification requestSpecification) {
        RestAssured.requestSpecification = requestSpecification;
    }
}

