# 20210201_QA11MobileScheduler
Mobile application test for Scheduler Application (application for creating events on user's timetable) using Android emulator and Appium test automation tool. 
The project logic is based on Value Object pattern, which you can find in the app package.
The test logic is implemented using TestNG Framework.
In the test package you will find basic tests for this app - creation of the new user, creation of the event for today and for the date specified in the past or future.

# To run the project a user should do:
1. start Appium server;
2. find and open in command line your android emulator:
```bash
emulator -list-avds
```
```bash
emulator @name_of_your_emulator
```
3. use Intellij IDEA to run the project or use command line:
```bash
gradle name_of_your_test_suite
```
(from your build.gradle file) 
