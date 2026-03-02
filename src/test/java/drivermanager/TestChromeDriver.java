package drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestChromeDriver extends ChromeDriver {

    public TestChromeDriver(ChromeOptions chromeOptions){
        super(chromeOptions);
    }

    public static WebDriver getChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--headless");
        return new ChromeDriver(chromeOptions);
    }
}
