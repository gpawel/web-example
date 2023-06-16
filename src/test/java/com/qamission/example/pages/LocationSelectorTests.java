package com.qamission.example.pages;

import com.qamission.example.BaseUnitTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LocationSelectorTests extends BaseUnitTest {
    @Test
    public void chooseLocation() {
        StorePage storePage = new StorePage(driver);
        LocationSelector selector = storePage.openLocationSelector();
        selector.searchLocation("queen street west");
        selector.pickUpLocation(1);
        selector.openLocationDetails();
        String locationAddress = selector.getAddress();
        Assert.assertTrue(locationAddress.toLowerCase().contains("queen"));
        StoreFlyer flyer = selector.openStoreFlyer();
        Assert.assertTrue(flyer.getFlyerForLocation().toLowerCase().contains("queen street west"));
    }
}
