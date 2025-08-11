package org.nextory.ui_automation.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Allure;
import org.nextory.ui_automation.mobile.base.BaseClassMobile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class RegisterPage extends BaseClassMobile {


    public String signUpButton="//*[contains(@resource-id,'SignupButton')]";
    public String enterName = "//android.widget.EditText";
    public String clickContinueButton = "//*[contains(@resource-id,'ContinueButton')]";
    public String selectCountry = "//*[contains(@resource-id,'MarketItem') and @text='Sweden']";
    public String enterEmail = "//*[contains(@resource-id,'EmailInputField')]";
    public String enterPassword = "//*[contains(@resource-id,'PasswordInputField')]";


    public String selectLanguage = "(//*[contains(@resource-id,'radio_button')])[8]//*[contains(@text,'Spanish')]";
    public String defaultLanguage = "(//*[contains(@resource-id,'radio_button')])[1]//*[contains(@text,'Swedish')]";

    public String select5to12Books = "//android.view.View[@resource-id=\"Onboarding.ReadingHabit.Question.1.AnswerButton.3\"]";
    public String selectReadOption = "//android.view.View[@resource-id=\"Onboarding.ReadingHabit.Question.2.AnswerButton.4\"]";
    public String selectWhyReadOption = "//android.view.View[@resource-id=\"Onboarding.ReadingHabit.Question.3.AnswerButton.1\"]";
    public String selectDailyHabit = "//android.view.View[@resource-id=\"Onboarding.ReadingHabit.Question.4.AnswerButton.1\"]";
    public String clickYesButton = "//android.view.View[@resource-id=\"Onboarding.ReadingHabit.ContinueButton.4\"]";
    public String selectCategory1 = "//android.view.View[@resource-id=\"onboarding.category.category_item_298\"]";
    public String selectCategory2 = "//android.view.View[@resource-id=\"onboarding.category.category_item_161\"]";
    public String clickGetStartedButton = "//android.view.View[@resource-id=\"onboarding.notifications.allow\"]";
    public String acceptAllowButton = "//*[contains(@resource-id,'permission_allow_button')]";



    public void allowNotifications() {
        try {
            if (isDisplayed(acceptAllowButton)) {
                click(acceptAllowButton);
            }
        } catch (Exception e) {
            System.out.println("No notifications to Accept ");
        }
    }

    public void selectACountry() {
        Allure.step("Select language");
        try {
            driver.get().findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Spanish\"))"
            ));
            WebElement element = driver.get().findElement(By.xpath("//*[contains(@text,\"Spanish\")]"));
            element.click();
        } catch (Exception e) {
            System.out.println("The country selected is not available");
        }
    }

    public void clickSignUpButton(){
        Allure.step("Click SignUp Button");
        click(signUpButton);
    }

    public void enterName(){
        Allure.step("Enter Name");
        //enterText(enterName,testdata.get("enterName"));
        enterText(enterName,"John");
    }

    public void clickContinue(){
        Allure.step("Click Continue Button");
        click(clickContinueButton);
    }

    public void selectCountry(){
        Allure.step("Select Country");
        click(selectCountry);
    }

    public void enterEmailAddress(){
        Allure.step("Enter Email address");
        enterText(enterEmail,testdata.get("enterEmailId"));
    }

    public void enterRandomEmailAddress() {
        Allure.step("enterGuestEmailAddress");
        enterText(enterEmail, BaseClassMobile.emailAddressGenerator(6)+"@nextory.com");
    }

    public void enterPassword(){
        Allure.step("Enter Password");
        enterText(enterPassword,"Testing123@");
    }


    public void selectBookOptions(){
        Allure.step("Select 5-12 books");
        click(select5to12Books);
    }

    public void setReadOptions(){
        Allure.step("Select Before  I Sleep");
        click(selectReadOption);
    }

    public void selectWhyReadOption(){
        Allure.step("Select Chill and unwind");
        click(selectWhyReadOption);
    }

    public void setDailyHabitOption(){
        Allure.step(" Select 10 min/day");
        click(selectDailyHabit);
    }

    public void clickYesButton(){
        Allure.step("Click Yes, set a daily habit Button");
        click(clickYesButton);
        wait(THREE_SECOND);
    }


    public void selectCategoryOptions(){
        Allure.step("Select categories");
        scrollToBottomPageOnce();
        click(selectCategory1);
        click(selectCategory2);
    }

    public void clickGetStartedButton(){
        Allure.step("Click Get Started Button");
        click(clickGetStartedButton);
    }














}
