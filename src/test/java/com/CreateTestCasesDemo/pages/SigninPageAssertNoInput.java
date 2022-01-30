package com.CreateTestCasesDemo.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Test scenario: Submit no input data and verify the failed sign-in.

public class SigninPageAssertNoInput extends BasePage {
	private String title;
	private boolean signinProblemAlert;
	private String aAlertContentText;
  private String signInEmailMobilePhone = "";

	public String getTitle(){
		return title;
	}

	public boolean getSigninProblemAlert(){
		return signinProblemAlert;
	}

	public String getAAlertContentText(){
		return aAlertContentText;
	}

	public String signinAssertCorrecPageTitle() throws Exception {
		title = (String) jsExec.executeScript("return document.title");
		return title;
	};

	public void signinPageAssertNoInput() throws Exception {
		WebElement signinLink = driver.findElement(By.cssSelector("span#nav-link-accountList-nav-line-1"));
		WebElement signinDivID = driver.findElement(By.cssSelector("div#nav-flyout-ya-signin"));
		WebElement navActionButtonAnchor = signinDivID.findElement(By.cssSelector("a.nav-action-button"));
		WebElement navActionButton = navActionButtonAnchor.findElement(By.cssSelector("span.nav-action-inner"));

		javascriptExecutorMouseOver(signinLink);
		javascriptExecutorClick(navActionButton);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement signinInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#ap_email")));
		signinInput.sendKeys(signInEmailMobilePhone);

		WebElement signinContinueButton = driver.findElement(By.cssSelector("input[id='continue'][class='a-button-input'][type='submit']"));
		signinContinueButton.submit();

		// "Amazon Sign-In"
		title = (String) jsExec.executeScript("return document.title");

		WebElement authEmailMissingAlert = driver.findElement(By.cssSelector("div#auth-email-missing-alert"));

		signinProblemAlert = isElementPresent(authEmailMissingAlert);

		// "Enter your email or mobile phone number"
		WebElement aAlertContent = authEmailMissingAlert.findElement(By.cssSelector("div.a-alert-content"));
		aAlertContentText = aAlertContent.getText();
	}
}
