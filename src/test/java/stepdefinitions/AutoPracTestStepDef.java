package stepdefinitions;

import drivermanager.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.AutoTestPracPage;
import utils.PropertyUtil;
import java.io.File;

public class AutoPracTestStepDef {

    AutoTestPracPage autoTestPracPage = new AutoTestPracPage(DriverManager.getDriver());
    String path = PropertyUtil.getInstance().get("test.file");
    final String absPath = new File(path).getAbsolutePath();

    @Given("User should navigate to Automation Practice Page")
    public void user_should_navigate_to_Page() {
        boolean opened = autoTestPracPage.isNameVisible();
        Assert.assertTrue(opened);
    }

    @When("Enter name {string} , email {string} and phone :{string}")
    public void enterNameEmailAndPhone(String name, String email, String phone) {
        autoTestPracPage.enterName(name);
        autoTestPracPage.enterEmail(email);
        autoTestPracPage.enterPhone(phone);
    }

    @And("Select dateFirst {string} , dateSecond {string}, dateThird start {string} and dateThird end {string}")
    public void selectFirstSecondDateAndThirdStartAndEndDates(String d1, String d2, String d3s, String d3e) {
        autoTestPracPage.enterDatePicker1(d1);
        autoTestPracPage.selectDatePicker2(d2);
        autoTestPracPage.enterDatePicker3start(d3s);
        autoTestPracPage.enterDatePicker3end(d3e);
    }

    @And("Select gender {string}, day {string} and country {string}")
    public void selectGenderDayAndCountry(String gender, String day, String country) {
        if(gender.equalsIgnoreCase("Male")) {
            autoTestPracPage.clickMale();
        } else {
            autoTestPracPage.clickFemale();
        }
        autoTestPracPage.selectDay(day);
        autoTestPracPage.selectCountry(country);
    }

    @Then("Verify above entered data is valid")
    public void verifyAboveEnteredDataIsValid() {
        String date1 = autoTestPracPage.valueOfDate1();;
        String date2 = autoTestPracPage.valueOfDate2();
        Assert.assertNotNull(date1);
        Assert.assertNotNull(date2);
    }

    @And("Handle dynamic button, and alerts using {string}")
    public void handleDynamicButtonAndAlertsUsing(String arg0) {
        autoTestPracPage.clickStart();
        autoTestPracPage.clickStop();
        autoTestPracPage.clickOkSimpleAlert();
        autoTestPracPage.clickOkConfirmationAlert();
        autoTestPracPage.enterTextOnPromptAlert(arg0);
    }

    @And("Handle double click, drag & drop and slide")
    public void handleDoubleClickDragDropAndSlide() {
        autoTestPracPage.clickCopyText();
        autoTestPracPage.dragAndDrop();
        autoTestPracPage.slidePriceRange(60, 5);
    }

    @And("Upload files and submit")
    public void uploadFilesAndSubmit() {
        autoTestPracPage.singleFileUpload(absPath);
    }

    @And("Complete all actions of shadow dom")
    public void completeAllActionsOfShadowDom() {
        autoTestPracPage.inputShadowDom("test auto");
        autoTestPracPage.chooseFileShadowDom(absPath);
    }
}
