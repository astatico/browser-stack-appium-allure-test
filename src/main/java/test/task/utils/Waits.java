package test.task.utils;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static test.task.managers.DriverManager.getAndroidDriver;

@UtilityClass
public class Wait {

    private static final int DEFAULT_TIMEOUT_IN_SECONDS = 5;

    public <T> T until(ExpectedCondition<T> isTrue, long timeOutInSeconds) {
        return new WebDriverWait(getAndroidDriver(), Duration.ofSeconds(timeOutInSeconds)).until(isTrue);
    }

    public <T> T until(ExpectedCondition<T> isTrue) {
        return new WebDriverWait(getAndroidDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT_IN_SECONDS)).until(isTrue);
    }
}
