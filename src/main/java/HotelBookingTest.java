import com.sun.javafx.PlatformUtil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotelBookingTest {

    WebDriver driver = new ChromeDriver();

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;
    
    @FindBy(xpath = "//*[@class= 'autoComplete']//*[contains(text(),'Indiranagar')]")
    private WebElement AutoComplete;
    
    @FindBy(xpath = "(//a[contains(text(),'20')])[1]")
    private WebElement fromDate;

    @FindBy(xpath = "(//a[contains(text(),'22')])[1]")
    private WebElement toDate;
    
    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    @FindBy(xpath = "//*[@class='searchSummary']")
    private WebElement searchResult;

    @Test
    public void shouldBeAbleToSearchForHotels() {
        setDriverPath();
        PageFactory.initElements(driver, this);

        driver.manage().window().maximize();
        driver.get("https://www.cleartrip.com/");
        
        hotelLink.click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");
        waitFor(5000);

        AutoComplete.click();
        
        fromDate.click();
        
        waitFor(2000);
        toDate.click();

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");

        searchButton.click();
        
        Assert.assertTrue(isElementPresent(searchResult));

        driver.quit();

    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
    
    private boolean isElementPresent(WebElement we) {
        try {
        	we.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
	
	    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
