package testCase2;

import org.openqa.selenium.*;

public class HomePage {
	private WebDriver driver;
	
	By PollsButton = By.xpath("//nav[@class='nav-primary']//ul[@class='nav-site']//span[contains(text(),'Polls')]");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void ClickPollsButton() {
		driver.findElement(PollsButton).click();
	}

}
