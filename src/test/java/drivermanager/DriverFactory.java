package drivermanager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.PropertyUtil;

public class DriverFactory {

    static final Logger LOG = LogManager.getLogger(DriverFactory.class);
    static final String browser = PropertyUtil.getInstance().get("browser");

    public static WebDriver initDriver() {
        WebDriver driver = null;
        if (browser.equalsIgnoreCase("Chrome")) {
            driver = TestChromeDriver.getChromeDriver();
            LOG.debug("Chrome-Driver setup completed!");
        } else {
            driver = TestEdgeDriver.getEdgeDriver();
            LOG.debug("Edge-Driver setup completed!");
        }
        DriverManager.setDriver(driver);
        return driver;
    }
}
