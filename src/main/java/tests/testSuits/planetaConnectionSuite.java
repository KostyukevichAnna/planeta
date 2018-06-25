package tests.testSuits;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static tests.steps.planetaConnectionSteps.*;

/**
 * Сценарии для проверки создания заявки на подключение к Планете.
 */
public class planetaConnectionSuite {
    /**
     * Мэп с тестовыми данными.
     */
    public static List<Map<String, String>> testData = new ArrayList();

    /**
     * Запуск веб-драйвера Google Chrome, открытие сайта, заполнение тестовых данных.
     */
    @BeforeClass
    public void startChrome() {
        System.setProperty("selenide.browser", "chrome");
        open("https://planeta.tc/ekb");

        getTestData();
    }

    /**
     * Запуск веб-драйвера Firefox, открытие сайта, заполнение тестовых данных.
     */
//    @BeforeClass
    public void startFirefox() {
        System.setProperty("selenide.browser", "firefox");
        open("https://planeta.tc/ekb");

        getTestData();
    }

    /**
     * Запуск веб-драйвера Opera, открытие сайта, заполнение тестовых данных.
     */
//    @BeforeClass
    public void startOpera() {
        System.setProperty("selenide.browser", "opera");
        open("https://planeta.tc/ekb");

        getTestData();
    }

    /**
     * Проверяет создание заявки на подключение.
     */
    @Test
    public static void connectionRequestTest(){
        for (Map<String, String> data : testData) {
            clickConnectionRequestButton();

            selectCity(data.get("city"));
            inputStreet(data.get("street"));
            inputHouse(data.get("house"));
            clickButtonCheckPossibility();

            inputName(data.get("name"));
            inputPhone(data.get("phone"));
            inputEmail(data.get("email"));

            clickButtonSendRequest();
            checkSendRequest();

            clickHome();
        }
    }
}
