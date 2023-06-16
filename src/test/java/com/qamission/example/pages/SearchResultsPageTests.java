package com.qamission.example.pages;

import com.qamission.example.model.Catalogue;
import org.openqa.selenium.WebElement;
import com.qamission.example.BaseUnitTest;
import com.qamission.example.model.Price;
import com.qamission.example.model.Product;
import com.qamission.example.model.Unit;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;


public class SearchResultsPageTests extends BaseUnitTest {

    private StorePage storePage;


    @BeforeMethod
    public void setUpTestMethod() {
        storePage = new StorePage(driver);
        storePage.goHomePage();
    }

    @Test
    public void createSearchResultsPageTest() {
        SearchResultsPage resultsPage = storePage.search("apples");
        Assert.assertNotNull(resultsPage);
    }

    @Test
    public void getTotlaFindings() {
        SearchResultsPage resultsPage = storePage.search("apples");
        int total = resultsPage.getTotalFound();
        Assert.assertEquals(total, 513);
    }

    @Test
    public void getItemsOnPageSinglePage() {
        SearchResultsPage resultsPage = storePage.search("asdf");
        int itemsOnPage = resultsPage.getNumberOfItemsOnPages();
        Assert.assertEquals(itemsOnPage, resultsPage.getLastItemIndexOnPage());
    }

    @Test
    public void getItemsOnPageMultiPage() {
        SearchResultsPage resultsPage = storePage.search("apples");
        int itemsOnPage = resultsPage.getNumberOfItemsOnPages();
        Assert.assertEquals(itemsOnPage, 48);
    }

    @Test
    public void loadAllPages() {
        SearchResultsPage resultsPage = storePage.search("milk");
        int total = resultsPage.getTotalFound();
        resultsPage.loadAllPages();
        int lastIndex = resultsPage.getLastItemIndexOnPage();
        Assert.assertEquals(lastIndex, total);
    }

    @Test
    public void noPages() {
        SearchResultsPage resultsPage = storePage.search("asdf");
        resultsPage.loadAllPages();
        int lastIndex = resultsPage.getLastItemIndexOnPage();
        Assert.assertEquals(lastIndex, resultsPage.getLastItemIndexOnPage());
    }

    @Test
    public void sortOnePageList() {
        SearchResultsPage resultsPage = storePage.search("appicot");
        resultsPage.loadAllPages();
        List<WebElement> webElementsList = resultsPage.getAllFoundProductElements();
        ProductInfoParser parser = new ProductInfoParser(driver);
        List<Product> productList = parser.getProducts(webElementsList);
        Collections.sort(productList);
        Collections.reverse(productList);


        Assert.assertEquals(productList.size(), resultsPage.getLastItemIndexOnPage());
        resultsPage.sortDesc();

        Product topProductInList = productList.get(0);
        Product topProductOnPage = parser.parseProductById(topProductInList.getId());
        Assert.assertEquals(topProductOnPage.getProductIndex(), 1);

        Product cheapestProdInList = productList.get(productList.size() - 1);
        Product cheapestProdOnPage = parser.parseProductById(cheapestProdInList.getId());
        Assert.assertEquals(cheapestProdOnPage.getProductIndex(), 47);


    }

    @Test
    public void testScroll() {
        SearchResultsPage resultsPage = storePage.search("milk");
        resultsPage.loadMore(3);
        resultsPage.scrollToTheTop();
        resultsPage.sortDesc();
    }

    @Test
    public void sortAllProdsList() {
        SearchResultsPage resultsPage = storePage.search("apples");
        resultsPage.loadAllPages();
        List<WebElement> webElementsList = resultsPage.getAllFoundProductElements();
        ProductInfoParser parser = new ProductInfoParser(driver);
        List<Product> productList = parser.getProducts(webElementsList);
        Catalogue catalogue = new Catalogue(productList);
        catalogue.sortProductsDesc();
        resultsPage.scrollToTheTop();

        Assert.assertEquals(productList.size(), resultsPage.getLastItemIndexOnPage());
        resultsPage.sortDesc();
        resultsPage.loadAllPages();

        Product topProductInList = productList.get(0);
        Product topProductOnPage = parser.parseProductById(topProductInList.getId());
        Assert.assertEquals(topProductOnPage.getProductIndex(), 1);

        Product cheapestProdInList = productList.get(productList.size() - 1);
        Product cheapestProdOnPage = parser.parseProductById(cheapestProdInList.getId());
        Assert.assertEquals(cheapestProdOnPage.getProductIndex(), resultsPage.getLastItemIndexOnPage());
    }

    @Test
    public void testPriceReductionFilter() {
        storePage.goHomePage();
        SearchResultsPage resultsPage = storePage.search("milk");
        resultsPage.filterByPriceReduction();
    }

    @Test
    public void testPriceReduction() {
        SearchResultsPage resultsPage = storePage.search("apples");

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

    ////div[@data-track-product-id='21217222_EA']

    @Test
    public void testPriceReductionNewProduct() {
        SearchResultsPage resultsPage = storePage.search("apples");

        SearchResultsPage onSalePage = resultsPage.filterByPriceReduction();
        onSalePage.loadAllPages();
        ProductInfoParser parser = new ProductInfoParser(driver);
        Product newProduct = parser.parseProductById("21217222_EA");


    }
}
