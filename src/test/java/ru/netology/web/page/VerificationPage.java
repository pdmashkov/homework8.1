package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeFiled = $("[ru.netology.web.data-ru.netology.web.test-id='code'] input");
    private SelenideElement verifyButton = $("[ru.netology.web.data-ru.netology.web.test-id='action-verify']");
    private SelenideElement errorMsg = $("[ru.netology.web.data-ru.netology.web.test-id='error-notification'] .notification__content");

    public VerificationPage() {
        codeFiled.shouldBe(Condition.visible);
    }

    public DashboardPage validVerify(DataHelper.AuthInfo authInfo) {
        DataHelper.VerificationCode code = DataHelper.getCode(authInfo);

        codeFiled.setValue(code.getCode());
        verifyButton.click();

        return new DashboardPage();
    }

    public void invalidVerify(String code) {
        codeFiled.setValue(code);
        verifyButton.click();
    }

    public void findError() {
        errorMsg.shouldBe(Condition.visible);
        errorMsg.shouldHave(Condition.text("Ошибка! Неверно указан код! Попробуйте ещё раз."));
    }
}
