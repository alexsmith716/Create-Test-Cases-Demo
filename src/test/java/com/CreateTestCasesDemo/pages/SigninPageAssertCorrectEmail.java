package com.CreateTestCasesDemo.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Test scenario: Submit correct email input data and verify/assert the successful sign-in.

public class SigninPageAssertCorrectEmail extends BasePage {
	private String title;
	private boolean signinProblemAlert;
	private String aAlertHeadingText;
	private String aListItemText;
	private String correctEmail = "correctAmazonEmail@correctAmazonEmail.com";
	private String receivedEmail;

	public String getTitle(){
		return title;
	}

	public boolean getSigninProblemAlert(){
		return signinProblemAlert;
	}

	public String getAAlertHeadingText(){
		return aAlertHeadingText;
	}

	public String getAListItemText(){
		return aListItemText;
	}

	public String getCorrectEmail(){
		return correctEmail;
	}

	public String getReceivedEmail(){
		return receivedEmail;
	}

	public String signinAssertCorrecPageTitle() throws Exception {
		title = (String) jsExec.executeScript("return document.title");
		return title;
	};

	public void signinAssertCorrectEmail() throws Exception {
		WebElement signinLink = driver.findElement(By.cssSelector("span#nav-link-accountList-nav-line-1"));
		WebElement signinDivID = driver.findElement(By.cssSelector("div#nav-flyout-ya-signin"));
		WebElement navActionButtonAnchor = signinDivID.findElement(By.cssSelector("a.nav-action-button"));
		WebElement navActionButton = navActionButtonAnchor.findElement(By.cssSelector("span.nav-action-inner"));

		javascriptExecutorMouseOver(signinLink);
		javascriptExecutorClick(navActionButton);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement signinInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#ap_email")));
		signinInput.sendKeys(correctEmail);

		WebElement signinContinueButton = driver.findElement(By.cssSelector("input[id='continue'][class='a-button-input'][type='submit']"));
		signinContinueButton.submit();

		// "Amazon Sign-In"
		title = (String) jsExec.executeScript("return document.title");

		WebElement authPageletContainer = driver.findElement(By.cssSelector("div[class='a-section auth-pagelet-container']"));
		WebElement aSpacingBase = authPageletContainer.findElement(By.cssSelector("div[class='a-row a-spacing-base']"));

		WebElement aSpacingBaseSpan = aSpacingBase.findElement(By.cssSelector("span"));

		receivedEmail = aSpacingBaseSpan.getText();
	}
}
