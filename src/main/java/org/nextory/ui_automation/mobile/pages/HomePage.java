package org.nextory.ui_automation.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Allure;
import org.junit.Assert;
import org.nextory.ui_automation.mobile.base.BaseClassMobile;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BaseClassMobile {


    public String homePage = "//android.view.View[contains(@resource-id,'TopHeader')]/android.widget.TextView";
    public String clickSearchBar = "//android.view.View[@resource-id=\"TabBar.search\"]";
    public String tabSearchInput = "//android.widget.EditText[@resource-id=\"Search.button\"]";
    public String enterSearchInput = "//android.widget.EditText[@resource-id=\"SearchInputField\"]";
    public String selectSuggestion = "//android.widget.TextView[contains(@text,'harry potter and the chamber of secrets')]";
    public String selectBook = "//android.view.View[@resource-id=\"product-item-0\"]";


    public void validateHomeScreen(){
        Allure.step("Navigated to Home screen");
        wait(TWO_SECOND);
        Assert.assertTrue(isDisplayed(homePage));
        System.out.println(getText(homePage));

    }

    public void clickSearchBar(){
        Allure.step("Click Search Bar in Home page");
        click(clickSearchBar);
    }

    public void searchForABook(){
        Allure.step(" Enter 'Harry Potter' in the search field.");
        click(tabSearchInput);
        WebElement searchField = getDriver().findElement(By.xpath(enterSearchInput));
        searchField.sendKeys("harry potter");
    }


    public void selectBookFrommSuggestions() {
        Allure.step("Select Harry Potter and the Chamber of Secrets");
        try {
            WebElement element = driver.get().findElement(By.xpath(selectSuggestion));
            element.click();
        } catch (Exception e) {
            System.out.println("The selected Suggestions is not available");
        }
    }


    public void openBook(){
        Allure.step("Open book from Home page");
        click(selectBook);
    }









}
