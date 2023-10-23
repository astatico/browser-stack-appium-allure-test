package test.task.pages;

import test.task.managers.DriverManager;

public abstract class BasePage {

    public BasePage() {
        new DriverManager().createDriver();
    }
}