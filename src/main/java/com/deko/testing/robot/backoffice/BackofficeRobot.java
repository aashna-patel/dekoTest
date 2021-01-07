package com.deko.testing.robot.backoffice;

import com.deko.testing.robot.BaseRobot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BackofficeRobot extends BaseRobot {

    public BackofficeRobot(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "LoginForm")
    private WebElement loginForm;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/form/div[2]/input")
    private WebElement usernameField;

    @FindBy(xpath = "/html/body/div/div/div/div/div/form/div[3]/input")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@class = \"btn btn-link forgotten-link\"]")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//*[@id=\"ng-app\"]/div[1]/div/div/div/div/form/div[6]/button")
    private WebElement signInButton;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/form/div[1]/div/div/p")
    private WebElement signInError;

    //DONE: locate this webelement which will need to be used in later methods and tests.
    @FindBy(css = "input[placeholder='Username']")
    private WebElement resetPasswordField;

    //DONE: locate this webelement which will need to be used in later methods and tests.
    @FindBy(xpath = "//span[contains(text(),'Reset')]")
    private WebElement resetButton;

    //todo: locate this webelement which will need to be used in later methods and tests.
    @FindBy()
    private WebElement resetSignInButton;

    //todo: locate this webelement which will need to be used in later methods and tests.
    @FindBy()
    private WebElement resetSuccessText;

    @FindBy(id = "top-bar")
    private WebElement backOfficeDashboardTopBar;

    private final String baseUrl = "https://release.dekopay.org/backoffice/v2/#/\n"; //insert provided test url here

    public BackofficeRobot openBackofficeLoginPage() {
        goTo(baseUrl);
        wait.until(ExpectedConditions.visibilityOf(this.loginForm));
        return this;
    }

    public BackofficeRobot fillLoginUsername(String username) {
        type(usernameField, username);
        return this;
    }

    public BackofficeRobot fillLoginPassword(String password) {
        type(passwordField, password);
        return this;
    }

    public BackofficeRobot submitLoginForm() {
        click(signInButton);
        waitUntilNotVisible(signInButton);
        return this;
    }

    public BackofficeRobot clickForgottenPasswordLink() {
        click(forgotPasswordLink);
        waitUntilURLContains("reset");
        return this;
    }

    public BackofficeRobot resetPassword() {
        //todo: Complete this method
        return this;
    }

    public boolean verifySignInError(String text) {
        if (verifySignInError("Sorry, the details you provided were incorrect.")) {
            //todo: Complete this method, so that tests can pass in expected error text
            return true;
        }
        return false;
    }


    public boolean verifyBackofficeUrl() {
        if (verifyURLContains("backoffice")) {
            return true;
        }
        return false;
    }

    public boolean verifySuccessfulLogin() {
        //todo: Complete this verify method, to be used by test class
        return false;
    }

    public boolean verifyResetPasswordSuccess() {
        //todo: Complete this verify method, to be used by test class
        return false;
    }
}
