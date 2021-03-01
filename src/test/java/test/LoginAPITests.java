package test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginAPITests {

     String baseURL="https://super-scheduler-app.herokuapp.com";

    @Test
    public void loginRegisteredUser() throws IOException {
        String response = sendRequestWithLoginAndPassword();
        JsonElement parsed = new JsonParser().parse(response);
        JsonElement token = parsed.getAsJsonObject().get("token");
        System.out.println(token);
        JsonElement status = parsed.getAsJsonObject().get("status");
        Assert.assertEquals(status.toString(), "Login success");
        JsonElement registration = parsed.getAsJsonObject().get("registration");
        //Assert.assertEquals(registration.toString(),"false");
        Assert.assertFalse(registration.getAsBoolean());

    }

    private String sendRequestWithLoginAndPassword() throws IOException {
        String response = Request.Post(baseURL + "/api/login")
                .bodyString("{\n" +
                        "  \"email\": \"katja1234@gmail.com\"" +
                        "  \"password\": \"\"Katja12345678\"\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        return response;
    }
}
