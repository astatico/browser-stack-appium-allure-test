package test.task.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.task.managers.WebElementManager;
import test.task.utils.Waits;

import java.util.List;
import java.util.NoSuchElementException;

import static test.task.managers.DriverManager.getDriver;

public class SearchPage extends BasePage {

    @AndroidFindBy(id = "search_container")
    private WebElement searchContainer;

    @AndroidFindBy(id = "search_src_text")
    private WebElement searchField;

    @AndroidFindBy(id = "page_list_item_title")
    private List<WebElement> topics;

    public SearchPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step("Wait until search page is appear")
    private void waitUntilPageLoaded() {
        WebElementManager.waitFor(searchContainer);
    }

    @Step("Enter Text in search field")
    public void inputTextToSearchField(String text) {
        waitUntilPageLoaded();
        WebElementManager.sendKeys(searchField, text);
    }

    @Step("Choose required topic")
    public void chooseTopic(String topic) {
        waitUntilPageLoaded();

        topics.stream()
                .filter(webElement -> webElement.getText().equals(topic))
                .findFirst()
                .ifPresent(WebElementManager::click);
    }
}
