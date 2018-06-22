package tests.testSuits;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static tests.steps.planetaMenuSteps.*;

/**
 * Сценарии для проверки меню Витрины planeta.tc.
 */
public class planetaMenuSuite {
    /**
     * Запуск веб-драйвера, открытие сайта.
     */
    @BeforeClass
    public void start() {
        System.setProperty("selenide.browser", "chrome");
        open("https://planeta.tc/ekb");
    }

    /**
     * Проверяет изменение цвета шрифта в меню.
     */
    @Test
    public void changeColorPlanetaMenuTest() {
        ElementsCollection elements = getElementsMenu();
        String color;
        for (SelenideElement element:elements) {
            color = getMenuColor(element);
            mouseHoverMenu(element);
            checkChangeColor(color, getMenuColor(element));
        }
    }

    /**
     * Проверяет наличие пунктов меню "Сравнить продукты", "Пополнить баланс" в плашке меню.
     */
    @Test
    public void menuItemPlanetaMenuTest() {
        ElementsCollection elements = getElementsMenu();

        SelenideElement element;
        SelenideElement menuPane = null;
        SelenideElement menuItemPane;
        for (int i = 0; i < elements.size(); i++){
            element = elements.get(i);

            if (i == 0) {
                clickMenuItem(element);
                menuPane = waitVisibleMenuPane();
            }
            else
                mouseHoverMenu(element);

            menuItemPane = getMenuItemPane(menuPane, element);

            checkMenuItemCompareProducts(menuItemPane);
            checkMenuItemTopUpBalance(menuItemPane);
        }
    }

    /**
     * Проверяет работу кнопки закрытия плашки меню.
     */
    @Test
    public void closeMenuPlanetaMenuTest() {
        ElementsCollection elements = getElementsMenu();

        SelenideElement menuPane;
        SelenideElement closeButton;
        for (SelenideElement element : elements) {
            clickMenuItem(element);
            menuPane = waitVisibleMenuPane();
            closeButton = getButtonCloseMenu(menuPane);
            clickButtonCloseMenu(closeButton);

            checkCloseMenuItem(menuPane);
        }
    }
}
