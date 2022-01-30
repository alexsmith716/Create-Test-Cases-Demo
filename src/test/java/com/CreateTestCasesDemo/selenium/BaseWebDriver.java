package com.CreateTestCasesDemo.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.CreateTestCasesDemo.pages.BasePage;

public class BaseWebDriver {

	private static WebDriver driver;
	protected static BasePage basePage;

	@BeforeAll
	public static void setUp(){
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com");

		if (!driver.getCurrentUrl().contains("amazon.com")){
			throw new IllegalStateException("Current URL is NOT https://www.amazon.com: " + driver.getCurrentUrl());
		}

		basePage = new BasePage();
		basePage.setWebDriver(driver);
	}

	@AfterAll
	public static void tearDown() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
