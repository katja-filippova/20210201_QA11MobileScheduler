package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void testLogin(){
        app.user().login("katja1234@gmail.com", "Katja12345678");
       Assert.assertTrue(app.event().isElementPresent(By.id("sort_options")));
    }

}
