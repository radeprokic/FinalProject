package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public HomePage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getEnterTheStore() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("enterTheStore")));
	}

	public void clickEnterTheStore() {
		this.getEnterTheStore().click();
	}

	public WebElement getSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signIn")));
	}

	public boolean homePage() {
		boolean displayed = false;
		if (this.getSignIn().isDisplayed()) {
			displayed = true;
		}
		return displayed;
	}

}