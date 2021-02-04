package test;

import org.testng.Assert;
import org.testng.annotations.Test;


public class TestOpenApplication extends TestBase {

    @Test
    public void testLaunchApp() {
        String version = app.getAppVersion();
        System.out.println("app opened");
        Assert.assertEquals(version, "0.0.3");
    }

    @Test
    public void testThatEventCreated(){
        app.user().login("katja1234@gmail.com", "Katja12345678");
        app.event().multiSwipe(3);
    }

}
