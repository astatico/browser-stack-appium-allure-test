package test.task.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static test.task.managers.DriverManager.getDriver;
import static test.task.managers.WebElementManager.getText;
import static test.task.managers.WebElementManager.waitFor;

public class ArticlePage extends BasePage {

    @AndroidFindBy(id = "page_contents_container")
    private WebElement pageContentsContainer;

    @AndroidFindBy(xpath = "//android.widget.TextView")
    private WebElement articleHeader;

    public ArticlePage() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step("Wait until article page is appear")
    private void waitUntilPageLoaded() {
        waitFor(pageContentsContainer);
    }

    @Step("Verify selected article opened")
    public void verifyArticleOpened(String articleName) {
        waitUntilPageLoaded();
        Assert.assertEquals(getText(articleHeader), articleName);
    }
}
