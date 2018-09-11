package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class GoogleSearchResultsPage extends BasePage {

    @FindBy(xpath = "//a[@id='pnnext']")
    private WebElement nextPage;

    @FindBy(xpath = "//div[@id='resultStats']")
    private WebElement resultStats;

    @FindBy(xpath = "//div[@class='srg']/div[@class='g']")
    private List<WebElement> searchResults;

    public GoogleSearchResultsPage (WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(resultStats, 30);
    }

    public boolean isLoaded() {
        return resultStats.isDisplayed()
                && getCurrentPageUrl().contains("/search");
    }

    public int getSearchResultsCount() {
        return searchResults.size();
    }

    public List<String> getSearchResultsList() {
        List<String> searchResultsList = new ArrayList<String>();
        for (WebElement searchResult: searchResults) {
            ((JavascriptExecutor)browser).executeScript("arguments[0].scrollIntoView();", searchResult);
            searchResultsList.add(searchResult.getText());
        }
        return searchResultsList;
    }

    public GoogleSearchResultsPage clickOnTheNextSearchPage () {
        nextPage.click();
        return new GoogleSearchResultsPage(browser);
    }
}
