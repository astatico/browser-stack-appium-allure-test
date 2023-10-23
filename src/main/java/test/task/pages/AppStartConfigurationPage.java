package test.task.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static test.task.managers.DriverManager.getDriver;
import static test.task.managers.WebElementManager.click;
import static test.task.managers.WebElementManager.waitFor;

public class AppStartConfigurationPage extends BasePage {

    @AndroidFindBy(id = "imageViewCentered")
    private WebElement wikiLogo;

    @AndroidFindBy(id = "fragment_onboarding_skip_button")
    private WebElement skipButton;

    public AppStartConfigurationPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step("Wait until configuration page is appear")
    private void waitUntilPageLoaded() {
        waitFor(wikiLogo);
    }

    @Step("Skip language settings")
    public MainPage skipConfiguration() {
        waitUntilPageLoaded();
        click(skipButton);
        return new MainPage();
    }
}
