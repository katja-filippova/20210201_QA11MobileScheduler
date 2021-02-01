package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventCreationTests extends TestBase {

    @BeforeMethod
    public void preconditions() {
        if (!app.event().isContainerPresent()) {
            app.user().login("katja1234@gmail.com", "Katja12345678");
        }
    }

    @Test
    public void eventCreationTest() {
        app.event().initEventCreation();
        app.event().tapOnPencilButton();
        app.event().fillInTitleForm("Haircut");
        app.event().fillInTypeForm("Termin");
        app.event().hideKeyboard();
        //app.event().fillInTimeRangeForm();
        app.event().fillInWageForm();
        app.event().tapOnAddButton();
    }


}
