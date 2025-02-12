package testingPages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


import java.time.Duration;
import java.util.NoSuchElementException;

public class LandingPage extends BasePage {
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//li/a[text()='MP3 Players']")
    private WebElement dropdownMenuMP3;

    @FindBy(xpath = "//a[text()='Show All MP3 Players']")
    private WebElement buttonSeeAllMP3;

    @FindBy(xpath = "//input[@name='search']")
    WebElement inputSearch;

    @FindBy(xpath = "//button[@class='btn btn-light btn-lg']")
    private WebElement buttonSearch;

    public void selectAllMp3() {

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(dropdownMenuMP3)).click();
        wait.until(ExpectedConditions.visibilityOf(buttonSeeAllMP3));
        buttonSeeAllMP3.click();
    }

    public void enterSearch(String toSearch) {
        inputSearch.sendKeys(toSearch);
    }

    public void clickSearch() {
        buttonSearch.click();
    }
}
