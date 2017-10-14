package com.vagrant.test;
import com.sun.javafx.PlatformUtil;
import com.vagrant.BaseProperity;
import com.vagrant.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {

	public WebDriver driver = null;
	public BaseProperity pro = new BaseProperity();
	public Page page = null;

	@BeforeTest
	public void setup() throws Exception {

		page = new Page();
		this.driver = page.openBrowser();

	}

	@Test
	public void shouldBeAbleToSearchForHotels() {

		page.click(pro.XPATHPRO.getProperty("Hotels"));
		WebDriverWait wait = new WebDriverWait(driver, 50);
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//h1[text()='Search for hotels']")))
					.isDisplayed();

			page.input("//input[@id='Tags']",
					"Indiranagar, Bangalore");
			
			new Select(driver.findElement(By.id("travellersOnhome"))).selectByVisibleText("1 room, 2 adults");
			page.click(
					"//input[@id='SearchHotelsButton'][@value='Search hotels']");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}

}
