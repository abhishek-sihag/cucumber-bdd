package pages;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AutoTestPracPage extends BasePage {

    static final Logger LOG = LogManager.getLogger(AutoTestPracPage.class);
    public AutoTestPracPage(WebDriver driver) {
        super(driver);
    }

    public static final By NAME = By.id("name");
    public static final By EMAIL = By.id("email");
    public static final By PHONE = By.id("phone");
    public static final By ADDRESS = By.id("textarea");
    public static final By MALE = By.xpath("//*[@id='male']");
    public static final By FEMALE = By.xpath("//*[@id='female']");
    public static final By COUNTRY = By.xpath("//*[@id='country']");
    public static final By COLORS = By.id("colors");
    public static final By SORTED_LIST = By.id("animals");
    public static final By DATE_PICKER1 = By.id("datepicker");
    public static final By DATE_PICKER2 = By.name("SelectedDate");
    public static final By DATE_PICKER3_1 = By.id("start-date");
    public static final By DATE_PICKER3_2 = By.id("end-date");
    public static final By SUBMIT_DATE = By.className("submit-btn");
    public static final By CHOOSE_SINGLE_FILE = By.id("singleFileInput");
    public static final By UPLOAD_SINGLE = By.xpath("//*[@id='singleFileForm']/button");
    public static final By CHOOSE_MULTIPLE_FILES = By.id("multipleFilesInput");
    public static final By UPLOAD_MULTIPLE = By.xpath("//*[@id='multipleFilesForm']/button");
    public static final By STATIC_WEB_TABLE = By.name("BookTable");
    public static final By DYNAMIC_WEB_TABLE = By.id("taskTable");
    public static final By PAGINATION_WEB_TABLE = By.id("productTable");
    public static final By SECTION1 = By.id("input1");
    public static final By SUBMIT_SECTION1 = By.id("btn1");
    public static final By SECTION2 = By.id("input2");
    public static final By SUBMIT_SECTION2 = By.id("btn2");
    public static final By SECTION3 = By.id("input3");
    public static final By SUBMIT_SECTION3 = By.id("btn3");
    public static final By TABS_INPUT = By.id("Wikipedia1_wikipedia-search-input");
    public static final By TABS_SEARCH = By.className("wikipedia-search-button");
    public static final By START_BTN = By.xpath("//button[@name='start']");
    public static final By STOP_BTN = By.cssSelector(".stop");
    public static final By SIMPLE_ALERT = By.id("alertBtn");
    public static final By CONFIRMATION_ALERT = By.id("confirmBtn");
    public static final By PROMPT_ALERT = By.id("promptBtn");
    public static final By NEW_TAB = By.xpath("//button[text()='New Tab']");
    public static final By POP_UP_WINDOWS = By.id("PopUp");
    public static final By POINT_ME = By.className("dropbtn");
    public static final By COPY_TEXT = By.xpath("//button[text()='Copy Text']");
    public static final By DND_SRC = By.id("draggable");
    public static final By DND_DST = By.id("droppable");
    public static final By SLIDER = By.xpath("//*[@id='slider-range']/span[1]");
    public static final By SCROLLING_DROPDOWN = By.id("comboBox");
    public static final By SHADOW_DOM = By.id("shadow_host");
    public static final By SHADOW_DOM_NESTED = By.id("nested_shadow_host");
    public static final By SHADOW_DOM_BLOG = By.cssSelector("a");
    public static final By SHADOW_DOM_CHOOSE_FILE = By.cssSelector("input[type='file']");
    public static final By SHADOW_DOM_CHECKBOX =  By.cssSelector("input[type='checkbox']");
    public static final By SHADOW_DOM_INPUT = By.cssSelector("input[type='text']");

    public boolean isNameVisible() {
        return isDisplayed(NAME);
    }

    public void enterName(String name){
        enterText(NAME, name);
    }

    public void enterEmail(String email){
        enterText(EMAIL, email);
    }

    public void enterPhone(String phone){
        enterText(PHONE, phone);
    }

    public void enterAddress(String address){
        enterText(ADDRESS, address);
    }

    public void clickMale(){
        click(MALE);
    }

    public void clickFemale(){
        click(FEMALE);
    }

    public void checkMonday() {
        check(By.id("monday"));
    }

    public void selectDay(String day) {
        check(By.id(day.toLowerCase()));
    }

    public boolean isDaySelected(String day) {
        return isSelected(By.id(day.toLowerCase()));
    }

    public void checkTuesday() {
        check(By.id("tuesday"));
    }

    public void checkWednesday() {
        check(By.id("wednesday"));
    }

    public void checkThursday() {
        check(By.id("thursday"));
    }

    public void checkFriday() {
        check(By.id("friday"));
    }

    public void checkSaturday() {
        check(By.id("saturday"));
    }

    public void checkSunday() {
        check(By.id("sunday"));
    }

    public void selectCountry(String country){
        selectOption(COUNTRY, country);
    }

    public void selectColors(List<String> color){
        multipleOptionsSelect(COLORS, color);
    }

    public void selectSortedList(List<String> animal){
        multipleOptionsSelect(SORTED_LIST, animal);
    }

    public void enterDatePicker1(String date){
        enterText(DATE_PICKER1, date);
    }

    public String valueOfDate1() {
        return getValue(DATE_PICKER1);
    }

    public void selectDatePicker2(String date){
        selectDateFromCalendar(DATE_PICKER2, date);
    }

    public String valueOfDate2() {
        return getValue(DATE_PICKER2);
    }

    public void enterDatePicker3start(String date){
        enterText(DATE_PICKER3_1, date);
    }

    public String valueOfDate3start() {
        return getValue(DATE_PICKER3_1);
    }

    public void enterDatePicker3end(String date){
        enterText(DATE_PICKER3_2, date);
    }

    public String valueOfDate3end() {
        return getValue(DATE_PICKER3_2);
    }

    public void clickSubmitDate(){
        click(SUBMIT_DATE);
    }

    public void dragAndDrop(){
        dragAndDrop(DND_SRC, DND_DST);
    }

    public void slidePriceRange(int x, int y){
        slide(SLIDER, x, y);
    }

    public List<Map<String, String>> printTable(){
        List<Map<String, String>> table =tableData(STATIC_WEB_TABLE);
        LOG.info(CommonUtil.formatTable(table));
        return table;
    }

    public List<Map<String, String>> printDynamicTable(){
        List<Map<String, String>> table =tableData(DYNAMIC_WEB_TABLE);
        LOG.info(CommonUtil.formatTable(table));
        return table;
    }

    public List<Map<String, String>> printPaginationTable(){
        List<Map<String, String>> table = new ArrayList<>();
        for(WebElement e: webElements(By.xpath("//*[@id='pagination']//a"))){
            e.click();
            table = tableData(PAGINATION_WEB_TABLE);
            LOG.info(CommonUtil.formatTable(table));
        }
        return table;
    }

    public void scrollSelect(String text){
        clickScrollAndSelect(SCROLLING_DROPDOWN, By.id("dropdown"),
                By.xpath("//*[@id='dropdown']/div[text()='"+text+"']"));
    }

    public void enterSec1AndSubmit(String text){
        enterText(SECTION1, text);
        click(SUBMIT_SECTION1);
    }

    public void enterSec2AndSubmit(String text){
        enterText(SECTION2, text);
        click(SUBMIT_SECTION2);
    }

    public void enterSec3AndSubmit(String text){
        enterText(SECTION3, text);
        click(SUBMIT_SECTION3);
    }

    public String singleFileUpload(String location){
        uploadFile(CHOOSE_SINGLE_FILE, location);
        click(UPLOAD_SINGLE);
        String status = getText(By.id("singleFileStatus"));
        LOG.info("Status of single File upload is: {}", status);
        return status;
    }

    public void multiFilesUpload(List<String> loc){
        uploadMultipleFiles(CHOOSE_MULTIPLE_FILES, loc);
        click(UPLOAD_MULTIPLE);
    }

    public void searchWiki(String text){
        enterText(TABS_INPUT, text);
        click(TABS_SEARCH);
    }

    public void clickStart(){
        click(START_BTN);
    }

    public void clickStop(){
        click(STOP_BTN);
    }

    public void clickOkSimpleAlert(){
        clickOkAlert(SIMPLE_ALERT);
    }

    public void clickOkConfirmationAlert(){
        clickOkAlert(CONFIRMATION_ALERT);
    }

    public void enterTextOnPromptAlert(String text){
        promptAlert(PROMPT_ALERT, text);
    }

    public void openNewTabAndNavigateBack(){
        click(NEW_TAB);
        switchBackToPreviousWindow();
    }

    public void openPopUpAndNavigateBack(){
        click(POP_UP_WINDOWS);
        switchBackToPreviousWindow();
    }

    public void hoverOnPointMe(){
        hover(POINT_ME);
        click(By.xpath("//div[@class='dropdown']//a[1]"));
    }

    public void clickCopyText(){
        dblClick(COPY_TEXT);
    }

    public void chooseFileShadowDom(String loc){
        SearchContext shadow = webElement(SHADOW_DOM).getShadowRoot();
        shadow.findElement(SHADOW_DOM_CHOOSE_FILE).sendKeys(getFileAbsolutePath(loc));
    }

    public void checkboxShadowDom(){
        SearchContext shadow = webElement(SHADOW_DOM).getShadowRoot();
        shadow.findElement(SHADOW_DOM_CHECKBOX).click();
    }

    public void inputShadowDom(String loc){
        SearchContext shadow = webElement(SHADOW_DOM).getShadowRoot();
        shadow.findElement(SHADOW_DOM_INPUT).sendKeys(loc);
    }

    public String getBlogLink(){
        SearchContext shadow = webElement(SHADOW_DOM).getShadowRoot();
        WebElement blog = shadow.findElement(SHADOW_DOM_BLOG);
        return blog.getAttribute("href");
    }

}
