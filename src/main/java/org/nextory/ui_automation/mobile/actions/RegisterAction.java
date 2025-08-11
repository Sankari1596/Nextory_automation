package org.nextory.ui_automation.mobile.actions;

import org.nextory.ui_automation.mobile.pages.RegisterPage;

public class RegisterAction extends RegisterPage {
    
    
    
    public void registerNewAccount(){
        clickSignUpButton();
        enterName();
        clickContinue();
        selectCountry();
        clickContinue();
        enterRandomEmailAddress();
        enterPassword();
        clickContinue();
        //selectLanguage();

        selectACountry();
        clickContinue();
        selectBookOptions();
        clickContinue();
        setReadOptions();
        clickContinue();
        selectWhyReadOption();
        clickContinue();
        setDailyHabitOption();
        clickYesButton();
        allowNotifications();
        selectCategoryOptions();
        clickContinue();
        clickGetStartedButton();
    }
    
}
