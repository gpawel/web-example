package com.qamission.example.pages;

import com.qamission.example.BaseUnitTest;
import org.testng.annotations.Test;

public class SignInTests extends BaseUnitTest {
    @Test
    public void testSignIn() {
        StorePage storePage = new StorePage(driver);
        SignIn signInPage = storePage.signIn();
        signInPage.login();
    }
}
