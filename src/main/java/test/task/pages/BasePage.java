package test.task.pages;

import org.junit.BeforeClass;
import org.testng.annotations.BeforeSuite;
import test.task.managers.DriverManager;

import static test.task.managers.DriverManager.getDriver;

public abstract class Page {

    public Page() {
        if (getDriver() == null) {
            new DriverManager().createDriver();
        }
    }
}