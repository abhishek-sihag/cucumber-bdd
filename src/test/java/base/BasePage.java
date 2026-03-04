package base;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utils.CommonUtil;
import utils.PropertyUtil;
import utils.WaitUtil;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BasePage {

    static final Logger LOG = LogManager.getLogger(BasePage.class);
    MyWebElement element;
    WebDriver driver;
    Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.element = new MyWebElementImpl(driver);
        actions = new Actions(driver);
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void switchBackToPreviousWindow() {
        element.switchBackToPreviousWindow();
    }

    public WebElement webElement(By by) {
        return element.findElement(by);
    }

    public List<WebElement> webElements(By by) {
        return element.findElements(by);
    }

    public void click(By by) {
        element.clickElement(by);
    }

    public void click(WebElement e) {
        element.clickElement(e);
    }

    public void clickNoWait(By by) {
        element.clickNoWait(by);
    }

    public void clickAllElements(List<WebElement> elementList) {
        if (elementList == null || elementList.isEmpty()) {
            throw new IllegalArgumentException("Element list cannot be null or empty");
        }
        actions.keyDown(Keys.CONTROL);
        elementList.forEach(actions::click);
        actions.keyUp(Keys.CONTROL).build().perform();
    }

    public void scrollTo(By by) {
        WebElement e = WaitUtil.waitForPresenceOfElement(driver,by);
        actions.scrollToElement(e).build().perform();
    }

    public void dblClick(By by) {
        actions.moveToElement(element.findElement(by)).doubleClick().build().perform();
    }

    public boolean isSelected(By by) {
        return element.findElement(by).isSelected();
    }

    public boolean isElementChecked(WebElement element) {
        return element.isSelected() ||
                element.getAttribute("class").contains("checked") ||
                "true".equals(element.getAttribute("aria-checked"));
    }

    public void check(By by) {
        WebElement e = webElement(by);
        if(!isElementChecked(e)) e.click();
    }

    public void uncheck(By by) {
        WebElement e = webElement(by);
        if(isElementChecked(e)) e.click();
    }

    public void enterText(By by, String text) {
        element.findElement(by).sendKeys(text+Keys.TAB);
    }

    public String getText(By by) {
        return element.findElement(by).getText();
    }

    public String getValue(By by) {
        return element.findElement(by).getAttribute("value");
    }

    public void selectOption(By by, String opt) {
        element.selectByVisibleText(by, opt);
    }

    public void selectOptionByValue(By by, String value) {
        element.selectByValue(by, value);
    }

    public void multipleOptionsSelect(By by, List<String> opts) {
        element.multiSelect(by, opts);
    }

    public void dragAndDrop(By src, By destination) {
        actions.dragAndDrop(element.findElement(src), element.findElement(destination));
    }

    public void hover(By by) {
        actions.moveToElement(element.findElement(by)).perform();
        LOG.info("hovered on: {}", by);
    }

    public void scrollToLocator(By by) {
        actions.scrollToElement(element.findElement(by)).build().perform();
        LOG.info("scrollToElement: {} completed!", by);
    }

    public void slide(By by, int xOffset, int yOffset) {
        actions.clickAndHold(element.findElement(by))
                .moveByOffset(xOffset, yOffset)
                .release().perform();
        LOG.info("clickAndHold: {} and moveByOffset x:{} y:{}",by, xOffset, yOffset);
    }

    public void clickScrollAndSelect(By click, By dropdown, By target) {
        actions.moveToElement(element.findElement(click)).click()
                .perform();
        LOG.info("scrolled to: {}", click);
        actions.moveToElement(element.findElement(dropdown))
                .scrollToElement(element.findElement(target))
                .click().perform();
        LOG.info("moveToElement: {} scrollToElement: {}; completed!", dropdown, target);
    }

    public boolean isDisplayed(By by) {
        return WaitUtil.waitIgnoringStale(driver, by);
    }

    public static String getFileAbsolutePath(String path) {
        return new File(path).getAbsolutePath();
    }

    private String combine(List<String> paths) {
        return String.join("\n", paths);
    }

    public void uploadFile(By by, String path) {
        element.findElement(by).sendKeys(getFileAbsolutePath(path));
    }

    public void uploadMultipleFiles(By by, List<String> paths) {
        String stringPaths = "";
        for(String path : paths) {
            stringPaths = String.join(stringPaths, getFileAbsolutePath(path));
        }
        uploadFile(by, stringPaths);
    }

    public List<Map<String, String>> tableData(By table) {
        WebElement h = element.findElement(table);
        List<WebElement> head = h.findElements(By.tagName("th"));
        List<String> headers = head
                .stream()
                .map(WebElement::getText)
                .toList();
        List<WebElement> rows = h.findElements(By.tagName("tr"));
        return rows.stream().skip(1)
                .map(row -> {
                    List<String> cell = row.findElements(By.tagName("td"))
                            .stream().map(WebElement::getText).toList();
                    LOG.debug("cell: {}", cell);
                    return IntStream.range(0, headers.size())
                            .boxed()
                            .collect(Collectors.toMap(headers::get,
                                    i -> (i<cell.size()) ? cell.get(i) : ""));
                }).toList();
    }

    public void selectDateFromCalendar(By by, String ddmmyyyy) {
        click(by);
        String d = ddmmyyyy.substring(0,2);
        if(d.startsWith("0")) d = d.substring(1);
        //date must be in ddmmyyyy
        String m = CommonUtil.getMonthName(ddmmyyyy);
        String y = ddmmyyyy.substring(4);
        selectOption(By.className("ui-datepicker-month"), m);
        selectOption(By.className("ui-datepicker-year"), y);
        click(By.xpath("//*[@id='ui-datepicker-div']//a[text()='"+d+"']"));
        LOG.info("Date selected: {} from calendar: {}",ddmmyyyy, by);
    }

    public void clickOkAlert(By by) {
        click(by);
        Alert alert = element.switchToAlert();
        alert.accept();
        LOG.info("Alert: {} accepted!",by);
    }

    public void clickCancelAlert(By by) {
        click(by);
        Alert alert = element.switchToAlert();
        alert.dismiss();
        LOG.info("Alert: {} canceled!",by);
    }

    public void promptAlert(By by, String text) {
        click(by);
        Alert alert = element.switchToAlert();
        alert.sendKeys(text);
        alert.accept();
        LOG.info("Text: {} sent on Alert: {} accepted!", text, by);
    }

    public String screenCapture(String name) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotDir = System.getProperty("user.dir") +
                PropertyUtil.getInstance().get("automation.output.screenshot");

        String destination = screenshotDir + name + "_" + timestamp + ".png";
        FileUtils.copyFile(src, new File(destination));
        return destination;
    }
}
