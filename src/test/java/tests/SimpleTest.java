package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class SimpleTest extends TestBase {

    @Test
    void findSelenideOnGitHub() {
        open("https://github.com/");
        $("[name='q']").setValue("selenide").pressEnter();
        $(byAttribute("href", "/selenide/selenide")).click();
        $(byAttribute("data-tab-item", "i4wiki-tab")).click();
        $(byText("Soft assertions")).click();
        $("#wiki-body").shouldHave(text("Using JUnit5 extend test class:"));
    }
}
