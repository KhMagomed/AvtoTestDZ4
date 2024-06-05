import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.ExactText;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class AppDeliveryAppTestOne {
    private String generateData(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void positiveTest() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Воронеж");
        String Date = generateData(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Алексей Бабкин");
        $("[data-test-id='phone'] input").setValue("+79102436802");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + Date));
    }

    @Test
    public void tastTwoTest(){
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Во");
        $$(".menu-item__control").findBy(Condition.text("Воронеж")).click();
        $(".input__control").click();
        String Date = generateData(7, "dd.MM.yyyy");
        $("[data-test-id='date'] input").click();
        if (!generateData(3,"MM").equals(generateData(7,"MM"))){
            $("[data-step='1']").click();
        };
        $$(".calendar__day").findBy(Condition.text(generateData(7,"dd"))).click();
        $("[data-test-id='name'] input").setValue("Алексей Бабкин");
        $("[data-test-id='phone'] input").setValue("+79102436802");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + Date));

    }
}