package test.task.managers;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class AppStartConfiguration {

    @AndroidFindBy(id = "imageViewCentered")
    private WebElement wikiLogo;

    @AndroidFindBy(id = "fragment_onboarding_skip_button")
    private WebElement skipButton;

    @Step("Ожидание появления страницы конфигурации")
    private void waitUntilConfigurationPageAppear() {

    }

    @Step("Пропустить настройку языка")
    public void skipConfiguration() {
        skipButton.click();
    }
}
