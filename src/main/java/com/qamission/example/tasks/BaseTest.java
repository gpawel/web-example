package com.qamission.example.tasks;

import org.openqa.selenium.WebDriver;
import com.qamission.example.config.Config;
import com.qamission.example.driver.ChromeDriverFactory;
import com.qamission.example.model.Product;
import com.qamission.example.pages.StorePage;
import com.qamission.example.pages.SearchResultsPage;
import org.testng.annotations.*;

import java.util.List;

public class BaseTest {
    public static WebDriver driver;
    public static List<Product> productList;
    public static String query;
    public static StorePage storePage;
    public static SearchResultsPage resultsPage;
    @Parameters({ "query" })
    @BeforeMethod
    public void setUpSuite(@Optional("apples") String searchString) {
        Config.getConfig();
        query = searchString;
        driver = ChromeDriverFactory.createChromeDriver();
        driver.get("https://www.loblaws.ca/");
        storePage = new StorePage(driver);

    }

    @Test
    public void firtstTest() {
        System.out.println("Hello World");
    }

    @AfterMethod
    public void tearDownSuite() {
        driver.quit();
    }
}


