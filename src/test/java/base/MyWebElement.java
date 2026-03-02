package base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Set;

public interface MyWebElement {

    public WebElement findElement(By by);
    public List<WebElement> findElements(By by);
    public void clickElement(By by);
    public void selectByVisibleText(By by, String text);
    public void multiSelect(By by, List<String> opts);
    public String currentWindow();
    public Set<String> allWindows();
    public void switchTo(String win);
    public Alert switchToAlert();
    public void switchBackToPreviousWindow();
}
