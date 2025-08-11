package org.nextory.ui_automation.mobile.tests;

import org.nextory.ui_automation.mobile.actions.RegisterAction;
import org.nextory.ui_automation.mobile.base.BaseClassMobile;
import org.nextory.ui_automation.mobile.pages.BookDetailPage;
import org.nextory.ui_automation.mobile.pages.HomePage;
import org.testng.annotations.Test;

public class AudioPlayerBookTest extends BaseClassMobile {

    RegisterAction registerAction = new RegisterAction();
    HomePage homePage = new HomePage();
    BookDetailPage bookDetailPage = new BookDetailPage();


    @Test
    public void playAndPauseBook(){
        registerAction.registerNewAccount();
        homePage.validateHomeScreen();
        homePage.clickSearchBar();
        homePage.searchForABook();
        homePage.selectBookFrommSuggestions();
        homePage.openBook();
        bookDetailPage.validateBookTitle();
        bookDetailPage.clickListen();
        //bookDetailPage.clickPauseButton();
        bookDetailPage.minimizePlayer();
        bookDetailPage.validateMiniPlayer();
    }
}
