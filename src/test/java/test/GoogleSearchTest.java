package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleSearchResultsPage;

import java.util.List;

public class GoogleSearchTest extends BaseTest {

    @Test
    public void basicSearchTest() {
        String searchTerm = "Selenium";
        Assert.assertTrue(googleHomePage.isLoaded(), "User is not on Home page.");

        GoogleSearchResultsPage googleSearchResultsPage = googleHomePage.search(searchTerm);
        Assert.assertTrue(googleSearchResultsPage.isLoaded(), "Search page is not loaded.");
        Assert.assertEquals(googleSearchResultsPage.getSearchResultsCount(), 10,
                "There are not 10 search results on Search page.");
        List<String> searchResults = googleSearchResultsPage.getSearchResultsList();

        for (String searchResult: searchResults) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm),
                    "searchTerm "+searchTerm+" not found in: \n"+searchResult);
        }
        googleSearchResultsPage.clickOnTheNextSearchPage();

        Assert.assertTrue(googleSearchResultsPage.isLoaded(), "Search page is not loaded.");
        Assert.assertEquals(googleSearchResultsPage.getSearchResultsCount(), 10,
                "There are not 10 search results on Search page.");

        for (String searchResult: searchResults) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm),
                    "searchTerm "+searchTerm+" not found in: \n"+searchResult);
        }
    }
}
