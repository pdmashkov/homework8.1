package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement header = $("[ru.netology.web.data-ru.netology.web.test-id='dashboard']");

    public DashboardPage() {
        header.shouldBe(Condition.visible);
    }
}
