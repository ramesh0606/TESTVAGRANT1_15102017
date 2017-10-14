package com.vagrant.test;
import com.sun.javafx.PlatformUtil;
import com.vagrant.BaseProperity;
import com.vagrant.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightBookingTest {

	public WebDriver driver = null;
	public BaseProperity pro = new BaseProperity();
	public Page page = null;

	@BeforeTest
	public void setup() throws Exception {

		page = new Page();
		this.driver = page.openBrowser();

	}

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		page.click(pro.XPATHPRO.getProperty("Oneway"));
		page.input(pro.XPATHPRO.getProperty("From"), "Bangalore");

		// wait for the auto complete options to appear for the origin

		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1"))
				.findElements(By.tagName("li"));
		originOptions.get(0).click();

		// wait for the auto complete options to appear for the destination
		page.input(pro.XPATHPRO.getProperty("To"), "Delhi");

		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver
				.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

		driver.findElement(By
				.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a"))
				.click();

		// all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();
		WebDriverWait wait = new WebDriverWait(driver, 50);
		boolean searchSummary = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.className("searchSummary")))
				.isDisplayed();
		System.out.println("searchSummary: -> " + searchSummary);
		Assert.assertTrue(searchSummary);

		// close the browser
		driver.quit();

	}

}
