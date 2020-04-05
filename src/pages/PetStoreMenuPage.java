package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreMenuPage {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public PetStoreMenuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getSignInButton() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signIn")));
	}

	public void clickSingIn() {
		this.getSignInButton().click();
	}

	public WebElement getSingInMessage() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signInMessage")));
	}

	public List<WebElement> getCenterNaviLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("centerNavigationLinks")));
	}

	public List<WebElement> getLeftNavLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("leftNavigationLinks")));
	}

	public List<WebElement> getImageNavLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("imageNavigationLinks")));
	}

	public boolean checkCenterNaviLinks() {
		List<WebElement> links = this.getCenterNaviLinks();
		boolean itIsOk = true;
		for (int i = 0; i < links.size(); i++) {
			WebElement link = links.get(i);
			link.getAttribute("href");
			int status = this.verifyURLStatus(link.getAttribute("href"));
			if (status > 400) {
				itIsOk = false;
			}
		}
		return itIsOk;
	}

	public boolean checkCenterNaviLinksCategory() {
		List<WebElement> links = this.getCenterNaviLinks();
		boolean category = true;
		for (int i = 0; i < links.size(); i++) {
			String hrefName = links.get(i).getAttribute("href").toLowerCase();
			links.get(i).click();
			String categoryName = this.driver.findElement(By.xpath(this.locators.getProperty("categoryName")))
					.getText();
			if (!hrefName.contains(categoryName.toLowerCase())) {
				category = false;
				break;
			}
			this.driver.navigate().to(this.locators.getProperty("petStoreHomePageUrl"));
			links = this.getCenterNaviLinks();

		}
		return category;
	}

	public boolean checkLeftNaviLinks() {
		List<WebElement> links = this.getLeftNavLinks();
		boolean itIsOk = true;
		for (int i = 0; i < links.size(); i++) {
			WebElement link = links.get(i);
			link.getAttribute("href");
			int status = this.verifyURLStatus(link.getAttribute("href"));
			if (status > 400) {
				itIsOk = false;
			}
		}
		return itIsOk;
	}

	public boolean checkLeftNaviLinksCategory() {
		List<WebElement> links = this.getLeftNavLinks();
		boolean category = true;
		for (int i = 0; i < links.size(); i++) {
			String hrefName = links.get(i).getAttribute("href").toLowerCase();
			links.get(i).click();
			String categoryName = this.driver.findElement(By.xpath(this.locators.getProperty("categoryName")))
					.getText();
			if (!hrefName.contains(categoryName.toLowerCase())) {
				category = false;
				break;
			}
			this.driver.navigate().to(this.locators.getProperty("petStoreHomePageUrl"));
			links = this.getCenterNaviLinks();

		}
		return category;
	}

	public boolean checkImageNaviLinks() {
		List<WebElement> links = this.getImageNavLinks();
		boolean itIsOk = true;
		for (int i = 0; i < links.size(); i++) {
			WebElement link = links.get(i);
			link.getAttribute("href");
			int status = this.verifyURLStatus(link.getAttribute("href"));
			if (status > 400) {
				itIsOk = false;
			}
		}
		return itIsOk;
	}

	public boolean checkImageNaviLinksCategory() {
		List<WebElement> links = this.getImageNavLinks();
		boolean category = true;
		for (int i = 0; i < links.size(); i++) {
			String hrefName = links.get(i).getAttribute("href").toLowerCase();
			links.get(i).click();
			String categoryName = this.driver.findElement(By.xpath(this.locators.getProperty("categoryName")))
					.getText();
			if (!hrefName.contains(categoryName.toLowerCase())) {
				category = false;
				break;
			}
			this.driver.navigate().to(this.locators.getProperty("petStoreHomePageUrl"));
			links = this.getCenterNaviLinks();

		}
		return category;
	}

	public int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean isSingInPageValid() {
		boolean displayed = false;
		if (this.getSingInMessage().isDisplayed()) {
			displayed = true;
		}
		return displayed;
	}

}