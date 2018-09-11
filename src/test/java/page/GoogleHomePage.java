package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleHomePage extends BasePage {

    @FindBy(xpath = "//input[@role='combobox']")
    private WebElement searchField;

    public GoogleHomePage (WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(searchField, 10);
    }

    public GoogleSearchResultsPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        return new GoogleSearchResultsPage(browser);
    }

    public boolean isLoaded() {
        return searchField.isDisplayed()
                && getCurrentPageTitle().contains("Google");
    }
}
