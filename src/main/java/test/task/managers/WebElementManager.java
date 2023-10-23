package test.task.managers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.task.utils.Waits;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebElementManager {

    public static WebElement waitFor(WebElement element) {
        return Waits.until(ExpectedConditions.visibilityOf(element));
    }

    public static void click(WebElement element) {
        waitFor(element).click();
    }

    public static void sendKeys(WebElement element, String text) {
        waitFor(element).sendKeys(text);
    }

    public static String getText(WebElement element) {
        return waitFor(element).getText();
    }
}
