package com.CreateTestCasesDemo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import com.CreateTestCasesDemo.pages.SigninPageAssertCorrectEmail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SigninPageAssertCorrectEmailTest extends BaseWebDriver {

	private SigninPageAssertCorrectEmail signinPage = new SigninPageAssertCorrectEmail();

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
			() -> assertEquals(signinPage.getCorrectEmail(), signinPage.getReceivedEmail())
		);
	}
}
