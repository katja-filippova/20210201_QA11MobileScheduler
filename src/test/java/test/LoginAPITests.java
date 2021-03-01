package test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginAPITests {

    String baseURL = "https://super-scheduler-app.herokuapp.com";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImthdGphMTIzNEBnbWFpbC5jb20ifQ.l-54TcZ7jvRiWLRfR2x7OxikTULkqJ8YMeNgqG1Ba8I";

    @Test
    public void loginRegisteredUser() throws IOException {
        String response = sendRequestWithLoginAndPassword();
        JsonElement parsed = new JsonParser().parse(response);
        JsonElement token = parsed.getAsJsonObject().get("token");
        System.out.println(token);
        JsonElement status = parsed.getAsJsonObject().get("status");
        Assert.assertEquals(status.toString(), "\"Login success\"");
        JsonElement registration = parsed.getAsJsonObject().get("registration");
        Assert.assertFalse(registration.getAsBoolean());

    }

    private String sendRequestWithLoginAndPassword() throws IOException {
        return Request.Post(baseURL + "/api/login")
                .bodyString("{ \"email\": \"" + "katja1234@gmail.com" + "\", \"password\": \""
                                + "Katja12345678" + "\"}"
                        , ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
    }

    @Test
    public void getResponseCodeWithLoginTest() throws IOException {
        String email = "katja1234@gmail.com";
        String pwd = "Katja12345678";

        int statusCode = Request.Post(baseURL + "/api/login").bodyString("{ \"email\": \""
                        + email + "\", \"password\": \"" + pwd + "\"}"
                , ContentType.APPLICATION_JSON)
                .execute().returnResponse().getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void getRecordsPeriodTest() throws IOException {
        String res = Request.Get(baseURL + "/api/recordsPeriod")
                .addHeader("Authorization", token)
                .addHeader("Content-Type", "application/json")
                .execute().returnContent().asString();

        System.out.println(res);

        String events = Request.Post(baseURL + "/api/records")
                .addHeader("Authorization", token)
                .addHeader("Content-Type", "application/json")
                .bodyString(res, ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();

        System.out.println(events);
    }

    @Test
    public void addNewSchedule() throws IOException {
        int response = Request.Post(baseURL + "/api/schedule")
                .addHeader("Authorization", token)
                .addHeader("Content-Type", "application/json")
                .bodyString("{" +
                        "  \"breaks\": 1," +
                        "  \"currency\": \"Euro\"," +
                        "  \"dateFrom\": \"11.03\"," +
                        "  \"dateTo\": \"12.03\"," +
                        "  \"daysOfWeek\": [" +
                        "    2" +
                        "  ]," +
                        "  \"editDate\": \"11.03\"," +
                        "  \"id\": 0," +
                        "  \"timeFrom\": \"11-00\"," +
                        "  \"timeTo\": \"12-00\"," +
                        "  \"title\": \"English lesson\"," +
                        "  \"type\": \"Blabla\"," +
                        "  \"wage\": 2" +
                        "}", ContentType.APPLICATION_JSON)
                .execute().returnResponse().getStatusLine().getStatusCode();
        Assert.assertEquals(response, 200);
    }

    @Test
    public void deleteScheduleById() throws IOException {
        Request.Delete(baseURL + "/api/schedule/{id}").execute().returnResponse();
    }
}
