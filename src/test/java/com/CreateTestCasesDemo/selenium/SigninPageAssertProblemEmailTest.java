package com.CreateTestCasesDemo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import com.CreateTestCasesDemo.pages.SigninPageAssertProblemEmail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SigninPageAssertProblemEmailTest extends BaseWebDriver {

	private SigninPageAssertProblemEmail signinPage = new SigninPageAssertProblemEmail();

	@Test
	@Order(1)
	public void signinAssertCorrecPageTitleTEST() throws Exception {
		String title = signinPage.signinAssertCorrecPageTitle();
		assertEquals("Amazon.com. Spend less. Smile more.", title);
	}

	@Test
	@Order(2)
	public void signinAssertProblemEmailTEST() throws Exception {
		signinPage.signinAssertProblemEmail();
		assertAll(
			() -> assertEquals("Amazon Sign-In", signinPage.getTitle()),
			() -> assertTrue(signinPage.getSigninProblemAlert()),
			() -> assertEquals("There was a problem", signinPage.getAAlertHeadingText()),
			() -> assertEquals("We cannot find an account with that email address", signinPage.getAListItemText())
		);
	}
}
