package app;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.time.Duration;

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
        tap(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"12\"]"));
        tap(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"0\"]"));
        tap(By.id("info_tp_date_to_txt"));
        tap(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"13\"]"));
        tap(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"0\"]"));
        tap(By.id("info_timePicker_ok_btn"));
    }


    public void fillInWageForm() {
        tap(By.id("info_wage_edit"));
        fillInForm(By.id("info_wage_input"), "30");
        tap(By.id("info_wage_save"));
    }

    public void tapOnSaveButton() {
        tap(By.id("info_save_btn"));
    }

    public void fillInBreakForm(int breaks) {
        if (breaks > 0) {
            for (int i = 0; i < breaks; i++) {
                tapOnBreakButton();
            }
        }
    }

    private void tapOnBreakButton() {
        tap(By.id("info_break_plus_btn"));
    }

    public void selectDate(String type, String month, String day) {
        if (!getSelectedMonth().equals(month)) {
            if (type.equals("past")) {
                swipeToRightTillNeededMonth(month);
                if(!getSelectedDay().equals(day)){
                    swipeToRightTillNeededDay(day);
                }
            }else if(type.equals("future")){
                swipeToLeftTillNeededMonth(month);
                if(!getSelectedDay().equals(day)){
                    swipeToLeftTillNeededDayOfMonth(day);
                }
            }
        }else if(!getSelectedDay().equals(day)){
            if(type.equals("past")){
                swipeToRightTillNeededDay(day);
            }else if (type.equals("future")){
                swipeToLeftTillNeededDayOfMonth(day);
            }
        }
    }

    private void swipeToLeftTillNeededDayOfMonth(String day) {
        while (!getSelectedDay().equals(day)) {
            moveElementToLeft(By.id("info_viewPager"));
            getSelectedDay();
        }
    }

    private void swipeToLeftTillNeededMonth(String month) {
        while (!getSelectedMonth().equals(month)) {
            moveElementToLeft(By.id("info_viewPager"));
            getSelectedMonth();
        }
    }
    private void moveElementToRight(By locator) {
        TouchAction action = new TouchAction(driver);
        //get activity points
        Dimension size = driver.manage().window().getSize();
        int leftPoint = (int) (size.width * 0.2);
        int rightPoint = (int) (size.width * 0.5);

        WebElement element = driver.findElement(locator);

        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        action
                .longPress(PointOption.point(leftPoint, middleY))
                .moveTo(PointOption.point(rightPoint, middleY))
                .release()
                .perform();
    }
    private void moveElementToLeft(By locator) {
        TouchAction action = new TouchAction(driver);

        Dimension size = driver.manage().window().getSize();
        int leftPoint = (int) (size.width * 0.2);
        int rightPoint = (int) (size.width * 0.5);

        WebElement element = driver.findElement(locator);

        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        action
                .longPress(PointOption.point(rightPoint, middleY))
                .moveTo(PointOption.point(leftPoint, middleY))
                .release()
                .perform();
    }

    private void swipeToRightTillNeededDay(String day) {
        while (!getSelectedDay().equals(day)) {
            moveElementToRight(By.id("info_viewPager"));
            getSelectedDay();
        }
    }

    private String getSelectedDay() {
        WebElement element = driver.findElement(By.id("date_container_layout"));
        return element
                .findElement(By.xpath(".//*[@resource-id='com.example.svetlana.scheduler:id/row_day_number_txt']"))
                .getText();
    }

    private void swipeToRightTillNeededMonth(String month) {
        while (!getSelectedMonth().equals(month)) {
            moveElementToRight(By.id("info_viewPager"));
            getSelectedMonth();
        }
    }

    private String getSelectedMonth() {
        WebElement element = driver.findElement(By.id("date_container_layout"));
        return element
                .findElement(By.xpath(".//*[@resource-id='com.example.svetlana.scheduler:id/row_month_txt']"))
                .getText();
    }

    public boolean isEventPresent() {
        waitForElement(By.id("row_container_main"), 20);
        return isElementPresent(By.id("row_container_main"));
    }

    public void multiSwipe (int swipesCount){
        for (int i = 0; i < swipesCount; i++) {
            swipeUp();
        }
    }

    private void swipeUp() {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;

        int startY = (int) (size.height*0.8);
        int endY = (int) (size.height*0.2);

        action.longPress(PointOption.point(x, startY))
                .moveTo(PointOption.point(x, endY))
                .release()
                .perform();

    }


}
