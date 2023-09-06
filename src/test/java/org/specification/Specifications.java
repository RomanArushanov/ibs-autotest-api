package org.specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.utils.APIUtils;

public class Specifications {

    public static RequestSpecification requestSpecification(String BaseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(BaseUri)
                .setSessionId(APIUtils.getJSessionId())
                .build();
    }

    public static void installSpecification(RequestSpecification requestSpecification) {
        RestAssured.requestSpecification = requestSpecification;
    }
}

