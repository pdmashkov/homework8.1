package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[ru.netology.web.data-ru.netology.web.test-id='login'] input");
    private SelenideElement passwordField = $("[ru.netology.web.data-ru.netology.web.test-id='password'] input");
    private SelenideElement actionButton = $("[ru.netology.web.data-ru.netology.web.test-id='action-login']");

    public VerificationPage validLogin(DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        actionButton.click();

        return new VerificationPage();
    }
}
