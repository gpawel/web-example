package com.qamission.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorePage extends Page {
    private static Logger log = LoggerFactory.getLogger(StorePage.class);

    private By content = By.id("site-content");
    private By queryField = By.cssSelector(".search-form__typeahead__search-input__input");
    private By logo = By.cssSelector(".logo");
    private By quickLinks = By.cssSelector(".block-wrapper.block-wrapper--block-type-block-cta-row.block-wrapper--block-style-vertical");
    private By signIn = By.cssSelector(".sign-in.account__login-link");
    private By chooseLocation = By.cssSelector("a.store-locator-link.booking-selector__item.booking-selector__item--with-fulfillment-widget");

    public StorePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        driver.get("https://www.loblaws.ca/");
        driver.manage().window().maximize();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(content));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(queryField));
    }

    public SearchResultsPage search(String product) {
        WebElement element = driver.findElement(queryField);
        element.clear();
        element.sendKeys(product);
        element.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver, product);
    }

    public StorePage goHomePage() {
        scrollToTheTop();
        driver.findElement(logo).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(quickLinks));
        return this;
    }

    public SignIn signIn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(signIn));
        driver.findElement(signIn).click();
        return new SignIn(driver);
    }

    public LocationSelector openLocationSelector() {
        wait.until(ExpectedConditions.presenceOfElementLocated(chooseLocation));
        wait.until(ExpectedConditions.elementToBeClickable(chooseLocation));
        driver.findElement(chooseLocation).click();
        return new LocationSelector(driver);
    }



}
