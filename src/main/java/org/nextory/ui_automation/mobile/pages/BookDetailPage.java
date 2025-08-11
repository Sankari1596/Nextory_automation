package org.nextory.ui_automation.mobile.pages;

import io.qameta.allure.Allure;
import org.junit.Assert;
import org.nextory.ui_automation.mobile.base.BaseClassMobile;
import org.openqa.selenium.By;

public class BookDetailPage extends BaseClassMobile {


    public String validateBookName = "//android.widget.TextView[@resource-id=\"Bookcard.BookTitle\"]";
    public String clickListenButton = "//android.view.View[@resource-id=\"BookCard.ListenButton\"]";
    public String clickPauseButton = "//android.view.View[@resource-id=\"Player.PauseButton\"]/android.widget.Button";
    public String clickMinimizeArrow = "//android.view.View[@resource-id=\"DownArrow_button\"]";
    public String isBookTitleDisplayed = "//*[contains(@resource-id,'MiniPlayer')]//android.widget.TextView[1]";
    public String isProgressDisplayed = "//*[contains(@resource-id,'MiniPlayer')]//android.widget.TextView[contains(@text,':')]";



    public void validateBookTitle(){
        Allure.step("Validate Book Title");
        String expectedTitle = "Harry Potter and the Chamber of Secrets";
        String actualTitle = getDriver()
                .findElement(By.xpath(validateBookName))
                .getText()
                .trim()
                .replace("\n", " ")
                .replaceAll("\\s+", " ");

        System.out.println("Expected: " + expectedTitle);
        System.out.println("Actual:   " + actualTitle);

        Assert.assertTrue(actualTitle.equalsIgnoreCase(expectedTitle));
    }


    public void clickListen(){
       Allure.step("Click Listen Button");
       click(clickListenButton);
   }



    public void clickPauseButton(){
       Allure.step("Click Pause button");
       click(clickPauseButton);
    }


    public void minimizePlayer(){
       Allure.step("Minimize mini player");
       click(clickMinimizeArrow);
    }

    public void validateMiniPlayer() {
        Allure.step("Validate Mini player");
        boolean isTitlePresent = getDriver().findElement(By.xpath(isBookTitleDisplayed)).isDisplayed();
        Assert.assertTrue(isTitlePresent);
        boolean isProgressPresent = getDriver().findElement(By.xpath(isProgressDisplayed)).isDisplayed();
        Assert.assertTrue(isProgressPresent);
    }















}
