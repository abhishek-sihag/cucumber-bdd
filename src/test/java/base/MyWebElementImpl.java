package base;

import io.cucumber.java.eo.Se;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import utils.PropertyUtil;
import utils.WaitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MyWebElementImpl implements MyWebElement {

    public static final Logger LOG = LogManager.getLogger(MyWebElementImpl.class);

    private final WebDriver driver;
    private Select select;

    static final String MAXIMUM_RETRY = PropertyUtil.getInstance().get("max.retry");
    private final int maxRetry = Integer.parseInt(MAXIMUM_RETRY);

    public MyWebElementImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void selectByVisibleText(By by, String text) {
        new Select(findElement(by)).selectByVisibleText(text);
    }

    @Override
    public void selectByValue(By by, String text) {
        new Select(findElement(by)).selectByValue(text);
    }

    private Select ensureMultiSelect(By by) {
        select = new Select(findElement(by));
        if (!select.isMultiple()) {
            throw new UnsupportedOperationException(
                    "Dropdown located by " + by + " is NOT a multi-select <select> element.");
        }
        return select;
    }

    @Override
    public void multiSelect(By by, List<String> opts) {
        select = ensureMultiSelect(by);
        for(String opt: opts) {
            select.selectByVisibleText(opt);
        }
    }

    @Override
    public String currentWindow() {
        return driver.getWindowHandle();
    }

    @Override
    public Set<String> allWindows() {
        return driver.getWindowHandles();
    }

    @Override
    public List<WebElement> findElements(By by) {
        int retry = 0;
        while (retry < maxRetry) {
            try {
                List<WebElement> elements = WaitUtil.waitForVisibilityOfElements(driver, by);
                if (!elements.isEmpty()) {
                    LOG.debug("findElements found {} elements for: {}", elements.size(), by);
                } else {
                    LOG.warn("No elements found for: {}", by);
                }
                return elements;
            } catch (TimeoutException | StaleElementReferenceException e) {
                retry++;
                LOG.warn("Retry {}/{} - Unable to locate elements: {}", retry, maxRetry, by);
            } catch (InvalidSelectorException e) {
                LOG.error("Invalid locator: {}", by, e);
                throw e;
            }
        }
        LOG.error("Elements not found after {} retries: {}", maxRetry, by);
        return Collections.emptyList();
    }

    @Override
    public WebElement findElement(By by) {
        int retry = 1;
        while (retry < maxRetry) {
            try {
                WebElement element = WaitUtil.waitForVisibilityOfElement(driver, by);
                LOG.debug("findElement success on attempt {} for: {}", retry, by);
                return element;
            } catch (TimeoutException | StaleElementReferenceException e) {
                retry++;
                LOG.warn("Retry {}/{} - Unable to locate element: {}", retry, maxRetry, by);
            } catch (InvalidSelectorException e) {
                LOG.error("Invalid locator: {}", by, e);
                throw e;
            }
        }
        throw new NoSuchElementException("Element not found after " + maxRetry + " attempts: " + by);
    }

    @Override
    public void clickElement(By by){
        int retry = 1;
        while(retry < maxRetry) {
            try {
                WaitUtil.waitForElementToBeClickable(driver, by).click();
                LOG.info("\t\tClick attempt on: {} successful!", by);
                return;
            } catch (Exception e) {
                retry++;
            }
        }
    }

    @Override
    public void clickElement(WebElement element) {
        int retry = 1;
        while(retry < maxRetry) {
            try {
                WaitUtil.waitForElementToBeClickable(driver, element).click();
                LOG.info("\t\tClick attempt: {} successful!", element);
                return;
            } catch (Exception e) {
                retry++;
            }
        }
    }

    @Override
    public void clickNoWait(By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", by);
    }

    @Override
    public void switchTo(String win) {
        driver.switchTo().window(win);
    }

    @Override
    public Alert switchToAlert() {
        return driver.switchTo().alert();
    }

    @Override
    public void switchBackToPreviousWindow(){
        List<String> windows = new ArrayList<>(allWindows());
        String curr = currentWindow();
        for(String win: windows){
            if(!curr.equals(win)) {
                switchTo(curr);
                break;
            }
        }
    }
}
