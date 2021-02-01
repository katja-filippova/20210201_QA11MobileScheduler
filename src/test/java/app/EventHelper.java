package app;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class EventHelper extends BaseHelper {
    public EventHelper(AppiumDriver driver) {
        super(driver);
    }

    public boolean isContainerPresent() {
        return isElementPresent(By.id("wizard_settings_main_container"));
    }

    public void initEventCreation() {
        tap(By.id("fab_main"));
    }

    public void tapOnPencilButton() {
        tap(By.id("fab_add_event"));
    }

    public void fillInTitleForm(String title) {
        fillInForm(By.id("info_title_input"), title);

    }

    public void fillInTypeForm(String type) {
        fillInForm(By.id("info_type_input"), type);
    }

    public void fillInTimeRangeForm() {
        tap(By.id("info_range_edit"));
        tap(By.id("info_tp_date_from_txt"));
        tap(By.id("12"));
        tap(By.id("00"));
        tap(By.id("info_tp_date_to_txt"));
        tap(By.id("13"));
        tap(By.id("00"));
        tap(By.id("info_timePicker_ok_btn"));
    }


    public void fillInWageForm() {
        tap(By.id("info_wage_edit"));
        fillInForm(By.id("info_wage_input"), "30");
        tap(By.id("info_wage_save"));
    }

    public void tapOnAddButton() {
        tap(By.id("info_save_btn"));
    }
}
