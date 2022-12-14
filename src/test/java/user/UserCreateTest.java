package user;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.User.Credentials;
import praktikum.User.User;
import praktikum.User.UserGenerator;
import praktikum.User.UserClient;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

public class UserCreateTest {

    private User user;
    private UserClient userClient;
    private String accessToken;

    @Before
    public void setUp() {
        user = UserGenerator.getUser();
        userClient = new UserClient();
    }

    @Test
    public void createUser200(){
        ValidatableResponse responseCreate = userClient.createUser(Credentials.from(user));
        accessToken = responseCreate.extract().path("accessToken");
        assertEquals(SC_OK, responseCreate.extract().statusCode());
    }

    @Test
    public void createAlreadyExistUser403(){
        ValidatableResponse responseCreateUniqueUser = userClient.createUser(Credentials.from(user));
        ValidatableResponse responseCreateExistUser = userClient.createUser(Credentials.from(user));
        accessToken = responseCreateUniqueUser.extract().path("accessToken");
        assertEquals(SC_FORBIDDEN, responseCreateExistUser.extract().statusCode());
        assertEquals("User already exists", responseCreateExistUser.extract().path("message"));
    }

    @Test
    public void createUserWithoutNameAndPassword403(){
        ValidatableResponse responseCreate = userClient.createUser(Credentials.fromOnlyEmail(user));
        assertEquals(SC_FORBIDDEN, responseCreate.extract().statusCode());
        assertEquals("Email, password and name are required fields", responseCreate.extract().path("message"));
    }

    @Test
    public void createUserWithoutNameAndEmail403(){
        ValidatableResponse responseCreate = userClient.createUser(Credentials.fromOnlyPassword(user));
        assertEquals(SC_FORBIDDEN, responseCreate.extract().statusCode());
        assertEquals("Email, password and name are required fields", responseCreate.extract().path("message"));
    }

    @Test
    public void createUserWithoutName403(){
        ValidatableResponse responseCreate = userClient.createUser(Credentials.fromOnlyEmailAndPassword(user));
        assertEquals(SC_FORBIDDEN, responseCreate.extract().statusCode());
        assertEquals("Email, password and name are required fields", responseCreate.extract().path("message"));
    }



    @After
    public void cleanUp() {
        userClient.deleteUser(accessToken);
    }
}
