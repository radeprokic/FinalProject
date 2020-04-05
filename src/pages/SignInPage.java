package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelUtils;

public class SignInPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public SignInPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;

		// User name
	}

	public WebElement getUserName() {
		return this.driver.findElement(By.xpath(locators.getProperty("username")));
	}

	public void setUserName(String username) {
		WebElement user = this.getUserName();
		user.clear();
		user.sendKeys(username);
	}

	// Password
	public WebElement getPassword() {
		return this.driver.findElement(By.xpath(locators.getProperty("password")));
	}

	public void setPassword(String password) {
		WebElement Password = this.getPassword();
		Password.clear();
		Password.sendKeys(password);
	}

	// Log in Button

	public WebElement getLoginButton() {
		return this.driver.findElement(By.xpath(locators.getProperty("logInButton")));
	}

	public void clickLoginButton() {
		this.getLoginButton().click();
	}

	// register
	public WebElement getRegisterButton() {
		return this.driver.findElement(By.xpath(locators.getProperty("registerButton")));
	}

	public void clickRegisterButton() {
		this.getRegisterButton().click();
	}
	// Sign in

	public void signIn(String username, String password) {
		this.setUserName(username);
		this.setPassword(password);
		this.clickLoginButton();
	}

	public WebElement getSignOut() {
		return this.driver.findElement(By.xpath(locators.getProperty("signOut")));
	}

	// true or false sign in

	public boolean signedIn() {
		try {
			this.driver.findElement(By.xpath(locators.getProperty("signOut")));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// sign out

	public void signOut() {
		this.getSignOut().click();
	}

}