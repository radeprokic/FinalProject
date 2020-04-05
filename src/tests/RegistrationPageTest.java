package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import pages.RegistrationPage;

import utils.ExcelUtils;

public class RegistrationPageTest {

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

	@Test
	public void RegistrationTesting() {
		
		driver.navigate().to(this.locators.getProperty("registrationUrl"));

		RegistrationPage newUser = new RegistrationPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);

		for (int i = 1; i < ExcelUtils.getRowNumber() - 1; i++) {
			driver.navigate().to(this.locators.getProperty("registrationUrl"));
			ExcelUtils.setRandomAt(i, 0);
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));
			RegistrationPage.setAllPass(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setLastName(ExcelUtils.getDataAt(i, 3));
			RegistrationPage.setEmail(ExcelUtils.getDataAt(i, 4));
			RegistrationPage.setPhone(ExcelUtils.getDataAt(i, 5));
			RegistrationPage.setAddress1(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress2(ExcelUtils.getDataAt(i, 7));
			RegistrationPage.setCity(ExcelUtils.getDataAt(i, 8));
			RegistrationPage.setState(ExcelUtils.getDataAt(i, 9));
			RegistrationPage.setZip(ExcelUtils.getDataAt(i, 10));
			RegistrationPage.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.selectFavoriteCategory(ExcelUtils.getDataAt(i, 13));
			RegistrationPage.enableMyList().click();
			RegistrationPage.enableMyList().click();
			RegistrationPage.getSaveAccauntInformation().click();
		}
		ExcelUtils.closeExcell();
		
		sa.assertTrue(RegistrationPage.getLogoImg().isDisplayed());
	}
		
	

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
