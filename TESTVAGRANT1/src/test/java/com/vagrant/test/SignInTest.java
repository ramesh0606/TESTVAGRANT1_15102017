package com.vagrant.test;
import com.sun.javafx.PlatformUtil;
import com.vagrant.BaseProperity;
import com.vagrant.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SignInTest {

	public WebDriver driver = null;
	public BaseProperity pro = new BaseProperity();
	public Page page = null;

	@BeforeTest
	public void setup() throws Exception {
		
		page = new Page();
		this.driver = page.openBrowser();

	}

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		// click on Your Trip at right top corner
		page.click(pro.XPATHPRO.getProperty("YourTrip"));
		// click on SingIn to enter login credentials
		page.click(pro.XPATHPRO.getProperty("SingIn"));

		// switch to sing In window
		driver.switchTo().frame("modal_window");

		// without username and password.simply, click on Sing In button.
		WebDriverWait wait = new WebDriverWait(driver, 50);

		try {
			// waiting SingInButton to be displayed.
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[contains(text(),'Log in with Facebook')]")))
					.isDisplayed();
			// click on SingInButton
			page.click(pro.XPATHPRO.getProperty("signInButton"));
			String errorOnLogin = driver
					.findElement(
							By.xpath(pro.XPATHPRO.getProperty("errorOnLogin")))
					.getText();
			Assert.assertTrue(errorOnLogin
					.contains("There were errors in your submission"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			driver.quit();
		}

	}

}
