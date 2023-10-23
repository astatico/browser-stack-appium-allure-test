package test.task.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static test.task.managers.DriverManager.getDriver;
import static test.task.managers.WebElementManager.click;
import static test.task.managers.WebElementManager.waitFor;

public class MainPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Search Wikipedia']")
    private WebElement searchField;

    @AndroidFindBy(id = "main_toolbar")
    private WebElement mainToolBar;

    public MainPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step("Wait until main page is appear")
    private void waitUntilPageLoaded() {
        waitFor(mainToolBar);
    }

    @Step("Skip language settings")
    public void selectSearchField() {
        waitUntilPageLoaded();
        click(searchField);
    }
}
