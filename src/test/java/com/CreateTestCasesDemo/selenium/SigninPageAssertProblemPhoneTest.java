package com.CreateTestCasesDemo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import com.CreateTestCasesDemo.pages.SigninPageAssertProblemPhone;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SigninPageAssertProblemPhoneTest extends BaseWebDriver {

	private SigninPageAssertProblemPhone signinPage = new SigninPageAssertProblemPhone();

	@Test
	@Order(1)
	public void signinAssertCorrecPageTitleTEST() throws Exception {
		String title = signinPage.signinAssertCorrecPageTitle();
		assertEquals("Amazon.com. Spend less. Smile more.", title);
	}

	@Test
	@Order(2)
	public void signinAssertProblemMobilePhoneTEST() throws Exception {
		signinPage.signinAssertProblemMobilePhone();
		assertAll(
			() -> assertEquals("Amazon Sign-In", signinPage.getTitle()),
			() -> assertTrue(signinPage.getSigninProblemAlert()),
			() -> assertEquals("Incorrect phone number", signinPage.getAAlertHeadingText()),
			() -> assertEquals("We cannot find an account with that mobile number", signinPage.getAListItemText())
		);
	}
}
