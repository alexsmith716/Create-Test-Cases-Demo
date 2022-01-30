package com.CreateTestCasesDemo.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Test scenario: Submit incorrect/malformed/problem mobile phone number input data and verify/assert the failed sign-in.
// Example input generates same response: "718555123", "7185551234"

public class SigninPageAssertProblemPhone extends BasePage {
	private String title;
	private boolean signinProblemAlert;
	private String aAlertHeadingText;
	private String aListItemText;
  private String signInEmailMobilePhone = "718555123";

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

	public String signinAssertCorrecPageTitle() throws Exception {
		title = (String) jsExec.executeScript("return document.title");
		return title;
	};

	public void signinAssertProblemMobilePhone() throws Exception {
		WebElement signinLink = driver.findElement(By.cssSelector("span#nav-link-accountList-nav-line-1"));
		WebElement signinDivID = driver.findElement(By.cssSelector("div#nav-flyout-ya-signin"));
		WebElement navActionButtonAnchor = signinDivID.findElement(By.cssSelector("a.nav-action-button"));
		WebElement navActionButton = navActionButtonAnchor.findElement(By.cssSelector("span.nav-action-inner"));

		javascriptExecutorMouseOver(signinLink);
		javascriptExecutorClick(navActionButton);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement signinInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#ap_email")));
		signinInput.sendKeys(signInEmailMobilePhone);
		//signinInput.sendKeys("7185551234");

		WebElement signinContinueButton = driver.findElement(By.cssSelector("input[id='continue'][class='a-button-input'][type='submit']"));
		signinContinueButton.submit();

		// "Amazon Sign-In"
		title = (String) jsExec.executeScript("return document.title");

		WebElement authErrorMessageBox = driver.findElement(By.cssSelector("div#auth-error-message-box"));

		signinProblemAlert = isElementPresent(authErrorMessageBox);

		WebElement aBoxInner = authErrorMessageBox.findElement(By.cssSelector("div[class='a-box-inner a-alert-container']"));

		// "Incorrect phone number"
		WebElement aAlertHeading = aBoxInner.findElement(By.cssSelector("h4"));

		aAlertHeadingText = aAlertHeading.getText();

		// "We cannot find an account with that mobile number"
		WebElement aAlertContent = aBoxInner.findElement(By.cssSelector("div.a-alert-content"));

		WebElement aAlertContentUl = aAlertContent.findElement(By.cssSelector("ul > li:nth-of-type(1)"));

		WebElement aListItem = aAlertContentUl.findElement(By.cssSelector("span.a-list-item"));
		aListItemText = aListItem.getText();
	}
}
