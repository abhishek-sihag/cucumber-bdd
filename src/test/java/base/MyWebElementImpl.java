package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import utils.PropertyUtil;
import utils.WaitUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyWebElementImpl implements MyWebElement {

    public static final Logger LOG = LogManager.getLogger(MyWebElementImpl.class);

    private final WebDriver driver;
    private Select select;

    static final String MAXIMUM_RETRY = PropertyUtil.getInstance().get("max.retry");

    public MyWebElementImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void selectByVisibleText(By by, String text) {
        new Select(findElement(by)).selectByVisibleText(text);
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
        try {
            List<WebElement> we = WaitUtil.waitForVisibilityOfElements(driver, by);
            if(!we.isEmpty()) {
                LOG.debug("\t\tfindPageElements found:{} elements for: {}", we.size(), by.toString());
            }else {
                LOG.warn("\tNo elements found for: {}", by.toString());
            }
            return we;
        }catch (TimeoutException e){
            LOG.error("Timeout locating elements: {}", by, e);
            return null;
        } catch (InvalidSelectorException e) {
            LOG.error("Invalid locators: {}", by, e);
            throw e;
        }
    }

    @Override
    public WebElement findElement(By by) {
        try {
            WebElement element = WaitUtil.waitForVisibilityOfElement(driver, by);
            LOG.debug("\t\tfindElement completed for: {}", by.toString());
            return element;
        } catch (TimeoutException e) {
            LOG.error("Timeout locating element: {}", by, e);
            return null;
        } catch (InvalidSelectorException e) {
            LOG.error("Invalid locator: {}", by, e);
            throw e;
        }
    }

    @Override
    public void clickElement(By by){
        int maxRetry = Integer.parseInt(MAXIMUM_RETRY);
        for(int i = 1; i <= maxRetry; i++) {
            try {
                WaitUtil.waitForElementToBeClickable(driver, by).click();
                LOG.info("\t\tClick attempt on: {} successful!", by);
                return;
            } catch (Exception e) {
                if(i==maxRetry){
                    LOG.warn("\tClick failed after attempt #{} for locator: {}", i, by);
                    throw new ElementClickInterceptedException("Click failed on: " + by, e);
                }
            }
        }
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
