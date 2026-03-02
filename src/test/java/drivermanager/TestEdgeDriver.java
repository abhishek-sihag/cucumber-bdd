package drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class TestEdgeDriver extends EdgeDriver {

    public static WebDriver getEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        return new EdgeDriver(options);
    }
}
