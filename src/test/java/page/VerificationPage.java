package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeFiled = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");
    private SelenideElement errorMsg = $("[data-test-id='error-notification'] .notification__content");

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
