package com.CreateTestCasesDemo.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Test scenario: Submit correct email input data and incorrect password data and verify/assert the unsuccessful sign-in.

public class SigninPageAssertCorrectEmailIncorrectPassword extends BasePage {
	private WebDriverWait wait;
	private String title;
	private boolean signinProblemAlert;
	private String aAlertHeadingText;
	private String aListItemText;
	private String signInEmailMobilePhone = "correctAmazonEmail@correctAmazonEmail.com";
	private String receivedEmail;
	private String signInPassword = "Incorr3cT!!";
	private boolean authPasswordMissingAlertPresent;

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
		return signInEmailMobilePhone;
	}

	public String getReceivedEmail(){
		return receivedEmail;
	}

	public boolean getAuthPasswordMissingAlertPresent(){
		return authPasswordMissingAlertPresent;
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

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement signinInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#ap_email")));
		signinInput.sendKeys(signInEmailMobilePhone);

		WebElement signinContinueButton = driver.findElement(By.cssSelector("input[id='continue'][class='a-button-input'][type='submit']"));
		signinContinueButton.submit();

		// "Amazon Sign-In"
		title = (String) jsExec.executeScript("return document.title");

		WebElement authPageletContainer = driver.findElement(By.cssSelector("div[class='a-section auth-pagelet-container']"));
		WebElement aSpacingBase = authPageletContainer.findElement(By.cssSelector("div[class='a-row a-spacing-base']"));
		WebElement aSpacingBaseSpan = aSpacingBase.findElement(By.cssSelector("span"));

		receivedEmail = aSpacingBaseSpan.getText();

		WebElement inputApPassword = driver.findElement(By.cssSelector("input#ap_password"));

		inputApPassword.sendKeys(signInPassword);

		WebElement signInSubmitButton = driver.findElement(By.cssSelector("input[id='signInSubmit'][class='a-button-input'][type='submit']"));
		signInSubmitButton.submit();

		WebElement authErrorMessageBox = driver.findElement(By.cssSelector("div#auth-error-message-box"));

		signinProblemAlert = isElementPresent(authErrorMessageBox);

		WebElement aBoxInner = authErrorMessageBox.findElement(By.cssSelector("div[class='a-box-inner a-alert-container']"));

		// "There was a problem"
		WebElement aAlertHeading = aBoxInner.findElement(By.cssSelector("h4"));

		aAlertHeadingText = aAlertHeading.getText();

		// "Your password is incorrect"
		WebElement aAlertContent = aBoxInner.findElement(By.cssSelector("div.a-alert-content"));

		WebElement aAlertContentUl = aAlertContent.findElement(By.cssSelector("ul > li:nth-of-type(1)"));

		WebElement aListItem = aAlertContentUl.findElement(By.cssSelector("span.a-list-item"));
		aListItemText = aListItem.getText();
	}
}
