package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class WaitUtil {
    public static final Logger LOG = LogManager.getLogger(WaitUtil.class);

    final static String FLUENT_WAIT_TIMEOUT = "fluent.wait.timeout";
    final static String FLUENT_WAIT_INTERVAL = "fluent.wait.interval";
    final static String EXPLICIT_WAIT_TIMEOUT = "explicit.wait";

    private static final Properties props = PropertyUtil.getInstance().getAll();
    static long timeout = Long.parseLong(props.getProperty(FLUENT_WAIT_TIMEOUT));
    static long pollMs  = Long.parseLong(props.getProperty(FLUENT_WAIT_INTERVAL));
    static long explicitTimeout = Long.parseLong(props.getProperty(EXPLICIT_WAIT_TIMEOUT));

    public static void waitForLoadState(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout)).until(
                d -> Objects.equals(((JavascriptExecutor) driver).executeScript("document.readyState"), "complete")
        );
    }
    public static WebElement waitForElementToBeClickable(WebDriver driver, By by){
        return new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout))
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(by)
                ));
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement e){
        return new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout))
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(e)
                ));
    }

    public static WebElement waitForPresenceOfElement(WebDriver driver, By by){
        return new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout))
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.presenceOfElementLocated(by)
                ));
    }

    public static void waitForPresenceOfElements(WebDriver driver, By by){
        new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout))
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(by)
                ));
    }

    public static WebElement waitForVisibilityOfElement(WebDriver driver, By by){
        return new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout))
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfElementLocated(by)
                ));
    }

    public static List<WebElement> waitForVisibilityOfElements(WebDriver driver, By by){
        return new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout))
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(by)
                ));
    }

    public static boolean waitIgnoringStale(WebDriver driver, By by) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollMs))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(d -> d.findElement(by).isDisplayed());
    }
}
