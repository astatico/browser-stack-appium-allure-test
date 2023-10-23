package test.task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.task.base.BaseTest;
import test.task.pages.AppStartConfigurationPage;
import test.task.pages.ArticlePage;
import test.task.pages.MainPage;
import test.task.pages.SearchPage;

public class WikipediaTest extends BaseTest {

    private static final String ARTICLE_NAME = "123rd Airlift Wing";

    private final AppStartConfigurationPage appStartConfigurationPage = new AppStartConfigurationPage();
    private final SearchPage searchPage = new SearchPage();
    private final ArticlePage articlePage = new ArticlePage();

    private MainPage mainPage;

    @BeforeAll
    void skipConfig() {
        mainPage = appStartConfigurationPage.skipConfiguration();
    }

    @Test
    @DisplayName("Verify an article found after searching it")
    void verifyArticleFoundAfterSearchingIt() {
        mainPage.selectSearchField();
        searchPage.inputTextToSearchField("123");
        searchPage.chooseTopic(ARTICLE_NAME);
        articlePage.verifyArticleOpened(ARTICLE_NAME);
    }
}
