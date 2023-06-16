package com.qamission.example.tasks;

import com.qamission.example.pages.StorePage;
import com.qamission.example.pages.LocationSelector;
import com.qamission.example.pages.StoreFlyer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task4 extends BaseTest {
    @Test
    public void chooseLocation() {
        storePage = new StorePage(driver);
        LocationSelector selector = storePage.openLocationSelector();
        selector.searchLocation("queen street west");
        selector.pickUpLocation(1);
        selector.openLocationDetails();
        String locationAddress = selector.getAddress();
        Assert.assertTrue(locationAddress.toLowerCase().contains("queen"),"Location address should have 'queen'");
        StoreFlyer flyer = selector.openStoreFlyer();
        Assert.assertTrue(flyer.getFlyerForLocation().toLowerCase().contains("queen street west"));
    }
}
