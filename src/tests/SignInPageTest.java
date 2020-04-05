package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;
import pages.SignInPage;
import utils.ExcelUtils;

public class SignInPageTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;


	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators = new Properties();
		locators.load(new FileInputStream("config/locators.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//
	@Test
	public void signInTestOneUserExcel() throws InterruptedException {
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);
		SoftAssert sa = new SoftAssert();

		SignInPage signIn = new SignInPage(driver, locators, waiter);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(this.locators.getProperty("signInUrl"));
			String username = ExcelUtils.getDataAt(i, 0);
			String password = ExcelUtils.getDataAt(i, 1);

			signIn.signIn(username, password);
			sa.assertTrue(signIn.signedIn());
			signIn.signOut();
		}
		sa.assertAll();
	}

	@AfterClass
	public void afterClass() {

		ExcelUtils.closeExcell();
		driver.close();

	}
}