package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.testinfo.Info;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.testinfo.DataGenerator.meetingDate;
import static ru.netology.testinfo.DataGenerator.user;

public class FormTest {

    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @AfterAll
    public static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldPositiveTest() {
        Info userInfo = user("ru");
        String date = meetingDate(3);
        $("[data-test-id = city] input").setValue(userInfo.getCity());
        $("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id = date] input").setValue(date);
        $("[data-test-id = name] input").setValue(userInfo.getName());
        $("[data-test-id = phone] input").setValue(userInfo.getPhone());
        $("[data-test-id = agreement]").click();
        $$("button").find(Condition.exactText("Запланировать")).click();
        $("[data-test-id = success-notification").shouldHave(Condition.exactText("Успешно! " + "Встреча успешно запланирована на " + date),
                Duration.ofSeconds(15)).shouldBe(exist);
    }

    @Test
    public void shouldPositiveTestDateReplan() {
        Info userInfo = user("ru");
        String date = meetingDate(3);
        String replanDate = meetingDate(4);
        $("[data-test-id = city] input").setValue(userInfo.getCity());
        $("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id = date] input").setValue(date);
        $("[data-test-id = name] input").setValue(userInfo.getName());
        $("[data-test-id = phone] input").setValue(userInfo.getPhone());
        $("[data-test-id = agreement]").click();
        $$("button").find(Condition.exactText("Запланировать")).click();
        $("[data-test-id = success-notification").shouldHave(Condition.exactText("Успешно! " + "Встреча успешно запланирована на " + date),
                Duration.ofSeconds(15)).shouldBe(exist);
        open("http://localhost:9999/");
        $("[data-test-id = city] input").setValue(userInfo.getCity());
        $("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id = date] input").setValue(replanDate);
        $("[data-test-id = name] input").setValue(userInfo.getName());
        $("[data-test-id = phone] input").setValue(userInfo.getPhone());
        $("[data-test-id = agreement]").click();
        $$("button").find(Condition.exactText("Запланировать")).click();
        $("[data-test-id = replan-notification] .notification__title").shouldHave(Condition.exactText("Необходимо подтверждение"),
                Duration.ofSeconds(15)).shouldBe(exist);
        $$("button").find(Condition.exactText("Перепланировать")).click();
        $("[data-test-id = success-notification").shouldHave(Condition.exactText("Успешно! " + "Встреча успешно запланирована на " + replanDate),
                Duration.ofSeconds(15)).shouldBe(exist);
    }

    @Test
    public void shouldTestInvalidDateReplan() {
        Info userInfo = user("ru");
        String date = meetingDate(3);
        String replanDate = meetingDate(2);
        $("[data-test-id = city] input").setValue(userInfo.getCity());
        $("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id = date] input").setValue(date);
        $("[data-test-id = name] input").setValue(userInfo.getName());
        $("[data-test-id = phone] input").setValue(userInfo.getPhone());
        $("[data-test-id = agreement]").click();
        $$("button").find(Condition.exactText("Запланировать")).click();
        $("[data-test-id = success-notification").shouldHave(Condition.exactText("Успешно! " + "Встреча успешно запланирована на " + date),
                Duration.ofSeconds(15)).shouldBe(exist);
        open("http://localhost:9999/");
        $("[data-test-id = city] input").setValue(userInfo.getCity());
        $("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id = date] input").setValue(replanDate);
        $("[data-test-id = name] input").setValue(userInfo.getName());
        $("[data-test-id = phone] input").setValue(userInfo.getPhone());
        $("[data-test-id = agreement]").click();
        $$("button").find(Condition.exactText("Запланировать")).click();
        $("[data-test-id = date] .input__sub").shouldBe(visible).shouldHave(text("Заказ на выбранную дату невозможен"));
    }
}
