package com.CreateTestCasesDemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

public class BasePage {

	protected static WebDriver driver;
	public JavascriptExecutor jsExec = (JavascriptExecutor) driver;

	public void setWebDriver(WebDriver driver) {
		BasePage.driver = driver;
	}

	public void javascriptExecutorMouseOver(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("MouseOver element with using javaScript mouseover");
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				(jsExec).executeScript(mouseOverScript,element);
				System.out.println("Able to mouseover on element");
			} else {
				System.out.println("Unable to mouseover on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to mouseover on element "+ e.getStackTrace());
		}
	}

	public void javascriptExecutorClick(WebElement element) throws Exception {
		try {
			if (isElementPresent(element)) {
				System.out.println("Clicking on element with using javaScript click");

				(jsExec).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}
	}

	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed() || element.isEnabled()) {
				flag = true;
			}
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}
}
