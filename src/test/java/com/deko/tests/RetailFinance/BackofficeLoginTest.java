package com.deko.tests.RetailFinance;

import com.deko.testing.robot.backoffice.BackofficeRobot;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class BackofficeLoginTest {
    private WebDriver driver;

    @BeforeTest
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    @AfterTest
    public void testTearDown() {
        driver.close();
    }

    @AfterSuite
    public void suiteTearDown() {
        driver.quit();
    }

    @Test
    public void backOfficeLoginPageLoadSuccess() {
        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .openBackofficeLoginPage()
                .verifyBackofficeUrl();

        Assert.assertTrue(robot.verifyBackofficeUrl());
    }

    @Test
    public void invalidLogin() {
        backOfficeLoginPageLoadSuccess();
        driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys("Patel.Aashna");
        driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("Deko20QA");
        driver.findElement(By.cssSelector(".btn.btn-sm.btn-primary")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String errorMessage = driver.findElement(By.cssSelector("p[data-ng-if='Response.Message']")).getText();
        String message = "Sorry, the details you provided were incorrect.";
        Assert.assertEquals(errorMessage, message);
    }

    @Test
    public void validLogin() {
        backOfficeLoginPageLoadSuccess();
        driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys("Aashna.Patel");
        driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("DekoQA20");
        driver.findElement(By.cssSelector(".btn.btn-sm.btn-primary")).click();
    }
    //DONE: Write the rest of the tests for the backoffice login page here.
    //DONE: Chrome driver should spin up, pass all tests identified (unless you find a bug?) and quit.
}
