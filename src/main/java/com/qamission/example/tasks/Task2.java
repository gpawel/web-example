package com.qamission.example.tasks;

import com.qamission.example.model.Price;
import com.qamission.example.model.Product;
import com.qamission.example.model.Unit;
import com.qamission.example.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.Test;

import java.util.List;

public class Task2 extends BaseTest {

    @Test
    public void testPriceReduction() {
        resultsPage = storePage.search(query);
        SearchResultsPage onSalePage = resultsPage.filterByPriceReduction();
        onSalePage.loadAllPages();
        List<Product> onSaleProducts = onSalePage.getAllFoundProducts();

        for (Product productOnSale : onSaleProducts) {
            if (productOnSale.isOnSale()) {
                Assert.assertEquals(productOnSale.getSallingPriceNow().getValue(), productOnSale.getSallingPiceWas().getValue()-productOnSale.getSaveValue(),0.001);
                List<Price> comparisonPrices = productOnSale.getComparisonPrices();
                if (comparisonPrices.size() < 2) continue;
                Price comPriceLeft = comparisonPrices.get(0);
                Price comPriceRight = comparisonPrices.get(1);
                if (comPriceLeft.getUnit() == Unit.KG && comPriceRight.getUnit() == Unit.LB) {
                    Assert.assertEquals(comPriceLeft.getPricePer(Unit.KG), comPriceRight.getPricePer(Unit.KG), 0.001);
                }
            }
            else throw new TestException("Product with id "+productOnSale.getId()+" is not on Sale");
        }


    }
}
