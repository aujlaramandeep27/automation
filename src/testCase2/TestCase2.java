package testCase2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCase2 {
	
	private WebDriver driver;
	private String baseUrl = "https://slashdot.org/";
	
	@Before
	public void setup() {
		// Optional. If not specified, WebDriver searches the PATH for chromedriver.
		System.setProperty("webdriver.chrome.driver", "./src/drivers/chromedriver");
		driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.get(baseUrl);
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void browseSlashdot() {
		assertEquals("Slashdot: News for nerds, stuff that matters", driver.getTitle());
	}
	
	@Test
	public void printArticlesCount() {
		System.out.println("Number of articles on the page: " + driver.findElements(By.tagName("article")).size());
	}
	
	@Test
	public void printUniqueIconsAndTheirOccurrence() {
		List<WebElement> icons = driver.findElements(By
	    		.xpath("//article//span[@class='topic']/a/img"));
	    
		List<String> iconTitles = new ArrayList<String>();
	    
	    for(int i=0; i<icons.size(); i++) {
	    	iconTitles.add(icons.get(i).getAttribute("title"));
	    }
	    
	    Set<String> distinctIconTitles = new HashSet<>(iconTitles);
        for (String iconTitle: distinctIconTitles) {
            System.out.println("Icon '" + iconTitle + "' occurs " + Collections.frequency(iconTitles, iconTitle) + " times.");
        }
		
	}
	
	@Test
	public void voteForARandomPoll() {
		navigateToPollsPage();
		voteAndSubmitPoll();
	}
	
	@Test
	public void printTotalNumberOfVotes() {
		navigateToPollsPage();
		voteAndSubmitPoll();
		
		WebElement totalVotes = new WebDriverWait(driver, 5)
		        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath
		        		("//div[@class='totalVotes']")));
		
	    System.out.println(totalVotes.getText());
		
	}
	
	private void navigateToPollsPage() {
		WebElement pollsButton = driver.findElement(By.xpath
				("//nav[@class='nav-primary']//ul[@class='nav-site']//span[contains(text(),'Polls')]"));
		    
	    pollsButton.click();
	}
	
	private void voteAndSubmitPoll() {
		WebElement pollRadioButton = new WebDriverWait(driver, 5)
		        .until(ExpectedConditions.elementToBeClickable(By.xpath
		        		("//form[@id='pollBooth']//input[@type='radio']")));
		
	    pollRadioButton.click();
	    
	    WebElement submitPollButton = driver.findElement(By.xpath
	    		("//form[@id='pollBooth']//button[@type='submit']"));
	    submitPollButton.click();
	}

}