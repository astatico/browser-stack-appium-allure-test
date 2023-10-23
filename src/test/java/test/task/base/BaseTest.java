package test.task;

import io.qameta.allure.Step;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.*;
import test.task.managers.DriverManager;
import test.task.pages.AppStartConfigurationPage;

@NoArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    private final DriverManager driverManager = new DriverManager();

    @BeforeAll
    public void setup() {
        driverManager.createDriver();
    }

    @AfterAll
    public void tearDown() {
        driverManager.closeDriver();
    }
}
