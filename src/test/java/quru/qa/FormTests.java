package quru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillForm() {

        //Переменные
        String name = "Тест";
        String lastname = "Гуру";
        String email = "test_quru@gmail.com";
        String gender = "Male";
        String phone = "9505555555";
        String year = "1970";
        String subject = "Hindi";
        String address = "г.Первый ул.Вторая д.1 кв. 2";
        String state = "NCR";
        String city = "Delhi";
        String happy_text = "Thanks for submitting the form";

        //Старт теста
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").setValue(name);
        $("#lastName").setValue(lastname);
        $("#userEmail").setValue(email);
        $(byText("Male")).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText("August")).click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--002").click();
        $("#subjectsInput").sendKeys(subject);
        $("#subjectsInput").pressEnter();
        $(byText("Sports")).click();
        $("input#uploadPicture").uploadFile(new File("src/test/resources/File.jpeg"));
        $("#currentAddress").setValue(address);
        $("#stateCity-wrapper").click();
        $("#state").$(byText(state));
        $("#stateCity-wrapper").click();
        $("#city").$(byText(city));
        $("#submit").click();

        //Проверки
        $("#example-modal-sizes-title-lg").shouldHave(Condition.text(happy_text));
        $(".table-responsive").shouldHave(Condition.text(name), Condition.text(lastname),
                  Condition.text(email), Condition.text(gender), Condition.text(phone),
                  Condition.text("02 August,1970"), Condition.text("File.jpeg"), Condition.text(subject),
                  Condition.text(address), Condition.text(state), Condition.text(city));

        //Закрыть модалку
        $("#closeLargeModal").click();

    }
}
