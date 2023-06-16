package com.qamission.example.tasks;

import com.qamission.example.model.Product;
import com.qamission.example.pages.SearchResultsPage;
import com.qamission.example.pages.ShoppingCart;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task3 extends BaseTest {

    @Test
    public void addItemToShoppingCart() {
        SearchResultsPage resultsPage = storePage.search(query);
        Product productInCart = resultsPage.getProdcutInfoByIndex(1);
        ShoppingCart cart = resultsPage.addItemToShoppingCart(1);
        String inCartProdName = cart.getProductNameAt(1);
        double inCartProdPrice = cart.getProductPriceAt(1);
        Assert.assertEquals(inCartProdName, productInCart.getProductName(),"Names should match");
        Assert.assertEquals(inCartProdPrice,productInCart.getSallingPriceNow().getValue(),0.001);
    }
}
