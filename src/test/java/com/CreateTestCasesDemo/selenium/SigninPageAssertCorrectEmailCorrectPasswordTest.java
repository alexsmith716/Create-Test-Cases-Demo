package com.CreateTestCasesDemo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import com.CreateTestCasesDemo.pages.SigninPageAssertCorrectEmailCorrectPassword;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SigninPageAssertCorrectEmailCorrectPasswordTest extends BaseWebDriver {

	private SigninPageAssertCorrectEmailCorrectPassword signinPage = new SigninPageAssertCorrectEmailCorrectPassword();

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
			() -> assertEquals(signinPage.getCorrectEmail(), signinPage.getReceivedEmail()),
			() -> assertTrue(signinPage.getNavItemSwitchAccount()),
			() -> assertTrue(signinPage.getNavItemSignout()),
			() -> assertTrue(signinPage.getNavYourAmazon()),
			() -> assertTrue(signinPage.getWindowLocationHref())
		);
	}
}
