package hooks;

import drivermanager.DriverFactory;
import drivermanager.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.PropertyUtil;
import utils.WaitUtil;

public class Hooks {

    public static final Logger LOG = LogManager.getLogger(Hooks.class);
    final String autoPageUrl = PropertyUtil.getInstance().get("auto.page.url");

    @Before
    public void setUp() {
        WebDriver driver = DriverFactory.initDriver();
        DriverManager.setDriver(driver);
        LOG.info("Driver initialized!");
        driver.get(autoPageUrl);
        //WaitUtil.waitForLoadState(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot)
                    DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }

        DriverManager.closeDriver();
    }
}
