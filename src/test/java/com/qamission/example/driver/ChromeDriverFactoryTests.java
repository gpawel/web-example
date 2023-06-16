package com.qamission.example.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qamission.example.BaseUnitTest;
import com.qamission.example.config.Config;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ChromeDriverFactoryTests extends BaseUnitTest {
    @Test
    public void startChromeDriver() {
        Assert.assertNotNull(driver);
        driver.manage().window().maximize();
        driver.get("https://www.loblaws.ca/");
        Wait wait = new WebDriverWait(driver, Duration.ofMillis(4 * Config.getConfig().getPauseInTest()));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".logo")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("site-content")));
        WebElement element = driver.findElement(By.xpath("//a[text()='Store Locator']"));
        Assert.assertEquals(element.getText(), "Store Locator");
    }
    @AfterMethod
    public void tearDownTestMethod() {}
}
