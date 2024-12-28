package com.base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class Base {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Set the path to ChromeDriver
        WebDriverManager.chromedriver().setup(); // Automatically downloads the correct version of chromedriver
        driver = new ChromeDriver();
        // Configure the browser
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogleSearch() {
        // Navigate to Google
        driver.get("https://www.google.com");

        // Find the search box, input text, and submit the query
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver tutorial");
        searchBox.submit();

        // Verify the search results
        WebElement results = driver.findElement(By.id("search"));
        Assert.assertTrue(results.isDisplayed(), "Search results are not displayed!");

        // Print the titles of the first few results
        driver.findElements(By.cssSelector("h3")).stream().limit(5).forEach(
                result -> System.out.println(result.getText())
        );
    }

    @AfterMethod
    public void teardown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
