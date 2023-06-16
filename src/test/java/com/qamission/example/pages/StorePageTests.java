package com.qamission.example.pages;

import com.qamission.example.BaseUnitTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StorePageTests extends BaseUnitTest {


    @Test
    public void createLoblawsPage() {
        StorePage lbl = new StorePage(driver);
        Assert.assertNotNull(lbl);
    }

    @Test
    public void testGoHome() {
        StorePage storePage = new StorePage(driver);
        storePage.search("milk");
        storePage.goHomePage();
    }




}
