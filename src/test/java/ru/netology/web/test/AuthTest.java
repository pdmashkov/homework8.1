package ru.netology.web.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import ru.netology.web.data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.LoginPage;

public class AuthTest {
    private DataHelper.AuthInfo authInfo;

    @BeforeEach
    public void setUp() {
        Selenide.open("http://localhost:9999");

        authInfo = DataHelper.getAuthInfo();
    }

    @Test
    public void shouldSuccessAuth() {
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.validVerify(authInfo);
    }

    @Test
    public void shouldFailedAfterThreeBadInputCode() {
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);

        String badCode = DataHelper.getBadCode().getCode();

        verificationPage.invalidVerify(badCode);
        verificationPage.findError();

        verificationPage.invalidVerify(badCode);
        verificationPage.findError();

        verificationPage.invalidVerify(badCode);
        verificationPage.findError();
    }

    @AfterAll
    public static void tearDown() {
        SQLHelper.deleteTestData();
    }
}
