package test.task.base;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.*;
import test.task.managers.DriverManager;

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
