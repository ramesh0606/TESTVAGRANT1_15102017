package com.vagrant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.javafx.PlatformUtil;

public class Page {

	public static WebDriver driver = null;
	public static BaseProperity pro = new BaseProperity();

	static {
		try {
			pro.loadProperity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public WebDriver setDriverPath() {

		if (pro.CONFIGPRO.getProperty("browserType")
				.equals("Chrome")) {
			if (PlatformUtil.isMac()) {
				System.setProperty(
						"webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\chromedriver");
				return new ChromeDriver();
			} else if (PlatformUtil.isWindows()) {
				System.setProperty(
						"webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\chromedriver.exe");
				return new ChromeDriver();
			} else if (PlatformUtil.isLinux()) {
				System.setProperty(
						"webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\chromedriver_linux");
				return new ChromeDriver();
			}
		}
		return null;
	}

	public void click(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	public void input(String xpath, String value) {
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}

	public WebDriver openBrowser() throws Exception {

		// driver initialization.
		this.driver = setDriverPath();
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Education\\eclipse\\workspace\\TESTVAGRANT1\\chromedriver.exe");
			this.driver = new ChromeDriver();
		}
		driver.get(
				pro.CONFIGPRO.getProperty("CLEARTRIP_URL"));
		driver.manage().timeouts().implicitlyWait(5,
				TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;

	}

}
