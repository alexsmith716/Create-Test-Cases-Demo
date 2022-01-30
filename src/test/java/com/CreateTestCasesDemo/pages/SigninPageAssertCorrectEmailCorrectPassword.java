package com.CreateTestCasesDemo.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SigninPageAssertCorrectEmailCorrectPassword extends BasePage {
	private WebDriverWait wait;
	private String title;
	private String signInEmailMobilePhone = "correctAmazonEmail@correctAmazonEmail.com";
	private String receivedEmail;
	private String signInPassword = "C0rr3cT!!";
	private boolean navItemSwitchAccount;
	private boolean navItemSignout;
	private boolean navYourAmazon;
	private boolean windowLocationHref;

	public String getTitle(){
		return title;
	}

	public String getCorrectEmail(){
		return signInEmailMobilePhone;
	}

	public String getReceivedEmail(){
		return receivedEmail;
	}

	public boolean getNavItemSwitchAccount(){
		return navItemSwitchAccount;
	}

	public boolean getNavItemSignout(){
		return navItemSignout;
	}

	public boolean getNavYourAmazon(){
		return navYourAmazon;
	}

	public boolean getWindowLocationHref(){
		return windowLocationHref;
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

		title = (String) jsExec.executeScript("return document.title");

		WebElement authPageletContainer = driver.findElement(By.cssSelector("div[class='a-section auth-pagelet-container']"));
		WebElement aSpacingBase = authPageletContainer.findElement(By.cssSelector("div[class='a-row a-spacing-base']"));
		WebElement aSpacingBaseSpan = aSpacingBase.findElement(By.cssSelector("span"));

		receivedEmail = aSpacingBaseSpan.getText();

		WebElement inputApPassword = driver.findElement(By.cssSelector("input#ap_password"));

		inputApPassword.sendKeys(signInPassword);

		WebElement signInSubmitButton = driver.findElement(By.cssSelector("input[id='signInSubmit'][class='a-button-input'][type='submit']"));
		signInSubmitButton.submit();

		WebElement navItemSwitchAccountElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a#nav-item-switch-account")));
		navItemSwitchAccount = isElementPresent(navItemSwitchAccountElement);

		WebElement navItemSignoutElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a#nav-item-signout")));
		navItemSignout = isElementPresent(navItemSignoutElement);

		WebElement navYourAmazonElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a#nav-your-amazon")));
		navYourAmazon = isElementPresent(navYourAmazonElement);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		String returnWindowLocationHref = (String) jsExec.executeScript("return window.location.href");

		boolean c = returnWindowLocationHref.equals("https://www.amazon.com/?ref_=nav_signin&");
		boolean fs = returnWindowLocationHref.equals("https://www.amazon.com/?ref_=nav_ya_signin&");

		windowLocationHref = c || fs;
	}
}
