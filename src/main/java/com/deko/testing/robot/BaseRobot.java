package com.deko.testing.robot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class BaseRobot {

    /*

    This is the BaseRobot class, where all parent methods will be written
    for all different applications and pages to use. This gives us consistency across different projects etc.

    Please use these methods where applicable (You will not need to use all of these methods).

    You may even wish to add a method here to reduce code duplication.

     */

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BaseRobot(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public BaseRobot closeWebBrowser() {
        driver.close();
        return this;
    }

    protected void switchFrame(String frame) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    protected void switchToDefaultFrame() {
        //find all frames in the page source, if there aren't any frames found, we don't do the switch because this would cause a switch frame error
        final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        if (iframes.size() <= 0){
            return;
        }
        driver.switchTo().defaultContent();
    }

    protected BaseRobot goTo(String url) {
        driver.get(url);
        return this;
    }

    protected String getCurrentURL() {
        String currentURL = driver.getCurrentUrl();
        return currentURL;
    }

    protected boolean verifyURL(String url) {
        if (getCurrentURL().equalsIgnoreCase(url)) {
            return true;
        }
        return false;
    }

    protected boolean WebElementContains(WebElement element, String text) {
        waitUntilVisible(element);
        if (element.getText().contains(text)) {
            return true;
        }
        System.out.println("Expected: " + text + "\n" + "Actual: " + element.getText());
        return false;
    }

    protected boolean verifyURLContains(String url) {
        if (getCurrentURL().contains(url)) {
            return true;
        }
        System.out.println("Expected: " + url + "\n" + "Actual: " + this.getCurrentURL());
        return false;
    }

    protected BaseRobot click(WebElement element) {
        element.click();
        return this;
    }

    protected BaseRobot actionClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        return this;
    }

    protected BaseRobot click(String path) {
        click(driver.findElement(By.cssSelector(path)));
        return this;
    }

    protected BaseRobot clickXPath(String enterXpath) {
        click(driver.findElement(By.xpath(enterXpath)));
        return this;
    }

    protected BaseRobot clickNTimes(String path, Integer times) {
        for (int i = 0; i < times; i++) {
            click(path);
        }
        return this;
    }

    protected BaseRobot type(String element, String text) {
        type(driver.findElement(By.cssSelector(element)), text);
        return this;
    }

    protected BaseRobot type(WebElement element, String text) {
        element.sendKeys(text);
        return this;
    }

    protected BaseRobot clear(WebElement element, String path) {
        element.clear();
        return this;
    }

    protected BaseRobot implicitWait(Integer seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        return this;
    }

    protected BaseRobot selectFromDropdown(String path, String value) {
        Select select = new Select(driver.findElement(By.cssSelector(path)));
        select.selectByVisibleText(value);
        return this;
    }

    protected BaseRobot selectFromDropdown(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
        return this;
    }

    protected BaseRobot selectValueFromRadioButtonList(List<WebElement> element, String value) {
        for (WebElement el : element) {
            if (el.getAttribute("value").equals(value)) {
                if (!el.isSelected()) {
                    el.click();
                }
                break;
            }
        }
        return this;
    }

    protected BaseRobot waitUntilElementInListIsVisible(List<WebElement> element) {
        for (WebElement el : element) {
            wait.until(ExpectedConditions.visibilityOf(el));
            break;
        }
        return this;
    }

    protected BaseRobot waitUntilElementIsVisible(String id) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        return this;
    }

    protected BaseRobot waitUntilVisible(String path) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(path)));
        return this;
    }

    protected BaseRobot waitUntilVisible(WebElement WebElement) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(WebElement));
        return this;
    }

    protected BaseRobot waitUntilClickable(WebElement WebElement) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(WebElement));
        return this;
    }

    protected BaseRobot waitUntilNotVisible(WebElement WebElement) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOf(WebElement));
        return this;
    }

    public boolean waitUntilURLContains(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlContains(text));
        return false;
    }

    public boolean isElementVisible(WebElement Webelement) {
        if (Webelement.isDisplayed()) {
            return true;
        }
        return false;
    }

}
