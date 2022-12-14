package praktikum;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Client {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public Client() {
    }

    protected RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    protected RequestSpecification getSpecForDelete() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();
    }

    protected RequestSpecification getSpecForGetOrders() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();
    }
}
