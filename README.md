# 20210201_QA11MobileScheduler
Mobile application test for Scheduler Application - application for creating events on user timezable - using Android emulator and Appium test automation tool. 
The project logic is based on Value Object pattern, which you can find in app package.
The test logic is based on TestNG Framework.
In the test package you will find basic tests for this app - creation of new user, creation of event for today and for date specified in past or future.

# To run the project user should have:
1. Appium server opened;
2. In command line find and open your emulator:
```bash
emulator -list-avds
```
```bash
emulator @name_of_your_emulator
```
3. Use Intellij IDEA to run the project or use command line:
```bash
gradle name_of_your_test_suite
```
(from your build.gradle file) 
