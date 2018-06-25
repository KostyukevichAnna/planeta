package tests.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static tests.testSuits.planetaConnectionSuite.testData;

/**
 * Шаги для проверки создания заявки на подключение к Планете.
 */
public class planetaConnectionSteps {
    /**
     * Заполняет мэп с тестовыми данными.
     */
    public static void getTestData(){
        Map<String, String> data1 = new HashMap<String, String>();

        data1.put("city", "Екатеринбург");
        data1.put("street", "Сулимова");
        data1.put("house", "36");
        data1.put("name", "test");
        data1.put("phone", "+79000000000");
        data1.put("email", "test@test.ru");

        testData.add(data1);
    }

    /**
     * Нажимает на кнопку "Включить Планету".
     */
    public static void clickConnectionRequestButton(){
        $("#request a").click();
    }

    /**
     * Выбирает город из списка "Населенный пункт".
     * @param city - название города.
     */
    public static void selectCity(String city){
        $("#wizard-city-select").click();
        $(".wizard-select__list").shouldBe(Condition.visible);

        ElementsCollection options = $$(".wizard-select__item");
        for (SelenideElement option : options){
            if (option.innerText().equals(city))
                option.click();
        }
    }

    /**
     * Вводит название улицы в поле "Улица".
     * @param street - название улицы.
     */
    public static void inputStreet(String street){
        $(".streetInputs input").setValue(street);
    }

    /**
     * Вводит номер дома в поле "Дом".
     * @param house - номер дома.
     */
    public static void inputHouse(String house){
        $(".houseInputs input").setValue(house);
    }

    /**
     * Нажимает кнопку "Проверить возможность".
     */
    public static void clickButtonCheckPossibility(){
        $("#step1_submit").click();
    }

    /**
     * Вводит имя в поле "Представьтесь".
     * @param name - имя.
     */
    public static void inputName(String name){
        $("#connect_first_name").setValue(name);
    }

    /**
     * Вводит телефон в поле "Телефон".
     * @param phone - телефон.
     */
    public static void inputPhone(String phone){
        $("#connect_phone").setValue(phone);
    }

    /**
     * Вводит E-mail в поле "E-mail".
     * @param email
     */
    public static void inputEmail(String email){
        $("#connect_email").setValue(email);
    }

    /**
     * Нажимает на кнопку "Отправить заявку".
     */
    public static void clickButtonSendRequest(){
        $("#step2_submit").click();
    }

    /**
     * Проверяет отображение сообщения: "Заявка принята".
     */
    public static void checkSendRequest(){
        String actualText = $(".wizard-right").getText().replace("\n", " ");
        Assert.assertTrue(actualText.contains("Заявка принята"), "Заявка не отправлена.");
    }

    /**
     * Нажимает кнопку "Планета", возвращается на главную страницу.
     */
    public static void clickHome(){
        $("a.logo").click();
    }
}
