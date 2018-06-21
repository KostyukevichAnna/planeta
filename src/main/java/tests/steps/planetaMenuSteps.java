package tests.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Шаги для сценариев проверки меню planeta.tc.
 */
public class planetaMenuSteps {
    /**
     * Время ожидания (милисекунды).
     */
    public static long timeOut = 3000;

    /**
     * Возвращает список элементов пунктов меню.
     * @return список элементов пунктов меню.
     */
    public static ElementsCollection getElementsMenu(){
        return $$("ul.NavigationMenu li");
    }

    /**
     * Возвращает цвет шрифта для пункта меню.
     * @param element - пункт меню.
     * @return цвет шрифта.
     */
    public static String getMenuColor(SelenideElement element){
        return element.$("span span").getCssValue("color");
    }

    /**
     * Выполняет движение мышью по елементу.
     * @param element - элемент.
     */
    public static void mouseHoverMenu(SelenideElement element){
        element.hover();
    }

    /**
     * Проверяет изменение цвета шрифта.
     * @param color - цвет шрифта первоначальный.
     * @param colorNew - цвет шрифта новый.
     */
    public static void checkChangeColor(String color, String colorNew){
        Assert.assertNotEquals(colorNew, color, "Не изменился цвет шрифта пункта меню.");
    }

    /**
     * Кликает по пункту меню.
     * @param element - элемент пункта меню.
     */
    public static void clickMenuItem(SelenideElement element) {element.click();}


    /**
     * Ожидает появления плашки меню.
      * @return - элемент плашки меню.
     */
    public static SelenideElement waitVisibleMenuPane(){
        return $("div.NavigationPanes").waitUntil(visible, timeOut);
    }

    /**
     * Получает элемент плашки меню для выбранного пункта меню.
     * @param menuPane - элемент плашки меню.
     * @param menuItem - выбранный пункт меню.
     * @return - элемент плашки меню для выбранного пункта меню.
     */
    public static SelenideElement getMenuItemPane(SelenideElement menuPane, SelenideElement menuItem){
        String itemDataTarget = menuItem.getAttribute("data-target");
        SelenideElement menuItemPane = menuPane.$("[data-id=" + itemDataTarget + "]");
        menuItemPane.waitUntil(attribute("class", "PanesItem PanesItem--active"), timeOut);
        return menuItemPane;
    }

    /**
     * Проверяет наличине пункта меню "Сравнить продукты" в плашке меню.
     * @param element - элемент плашки меню.
     */
    public static void checkMenuItemCompareProducts(SelenideElement element){
        Assert.assertNotNull(element.$$("a.Media").findBy(exactText("Сравнить продукты")), "Пункт меню \"Сравнить продукты не найден.\"");
    }

    /**
     * Проверяет наличине пункта меню "Пополнить баланс" в плашке меню.
     * @param element - элемент плашки меню.
     */
    public static void checkMenuItemTopUpBalance(SelenideElement element){
        Assert.assertNotNull(element.$$("a.Media.paymentLink").findBy(exactText("Пополнить баланс")), "Пункт меню \"Сравнить продукты не найден.\"");
    }

    /**
     * Возвращает кнопку закрытия плашки меню.
     * @param menuPane - плашка меню.
     * @return - элемент кнопки закрытия плашки меню.
     */
    public static SelenideElement getButtonCloseMenu(SelenideElement menuPane){
        return menuPane.$("div[data-click = NavigationHide]");
    }

    /**
     * Кликает по кнопке закрытия плашки меню.
     * @param element - кнопка закрытия плашки меню.
     */
    public static void clickButtonCloseMenu(SelenideElement element){
        element.click();
    }

    /**
     * Проверяет закрытие плашки меню.
     * @param element - элемент плашки меню.
     */
    public static void checkCloseMenuItem(SelenideElement element){
        element.waitUntil(hidden, timeOut);
        Assert.assertFalse(element.isDisplayed(), "Плашка меню не закрылась.");
    }
}
