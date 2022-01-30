package com.CreateTestCasesDemo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import com.CreateTestCasesDemo.pages.SigninPageAssertNoInput;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SigninPageAssertNoInputTest extends BaseWebDriver {

	private SigninPageAssertNoInput signinPage = new SigninPageAssertNoInput();

	@Test
	@Order(1)
	public void signinAssertCorrecPageTitleTEST() throws Exception {
		String title = signinPage.signinAssertCorrecPageTitle();
		assertEquals("Amazon.com. Spend less. Smile more.", title);
	}

	@Test
	@Order(2)
	public void SigninPageAssertNoInputTEST() throws Exception {
		signinPage.signinPageAssertNoInput();
		assertAll(
			() -> assertEquals("Amazon Sign-In", signinPage.getTitle()),
			() -> assertTrue(signinPage.getSigninProblemAlert()),
			() -> assertEquals("Enter your email or mobile phone number", signinPage.getAAlertContentText())
		);
	}
}
