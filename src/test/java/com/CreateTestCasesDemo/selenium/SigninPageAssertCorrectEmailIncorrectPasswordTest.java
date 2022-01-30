package com.CreateTestCasesDemo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import com.CreateTestCasesDemo.pages.SigninPageAssertCorrectEmailIncorrectPassword;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SigninPageAssertCorrectEmailIncorrectPasswordTest extends BaseWebDriver {

	private SigninPageAssertCorrectEmailIncorrectPassword signinPage = new SigninPageAssertCorrectEmailIncorrectPassword();

	@Test
	@Order(1)
	public void signinAssertCorrecPageTitleTEST() throws Exception {
		String title = signinPage.signinAssertCorrecPageTitle();
		assertEquals("Amazon.com. Spend less. Smile more.", title);
	}

	@Test
	@Order(2)
	public void signinAssertCorrectEmailTEST() throws Exception {
		signinPage.signinAssertCorrectEmail();
		assertAll(
			() -> assertEquals("Amazon Sign-In", signinPage.getTitle()),
			() -> assertTrue(signinPage.getSigninProblemAlert()),
			() -> assertEquals("There was a problem", signinPage.getAAlertHeadingText()),
			() -> assertEquals("Your password is incorrect", signinPage.getAListItemText())
		);
	}
}
