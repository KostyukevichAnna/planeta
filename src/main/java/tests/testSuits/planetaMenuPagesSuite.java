package tests.testSuits;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static tests.steps.planetaMenuPagesSteps.*;
import static tests.steps.planetaMenuSteps.*;

/**
 * Сценарии для проверки переходов по меню Витрины planeta.tc.
 */
public class planetaMenuPagesSuite {
    /**
     * Запуск веб-драйвера, открытие сайта.
     */
    @BeforeClass
    public void start() {
        System.setProperty("selenide.browser", "chrome");
        open("https://planeta.tc/ekb");
    }

    /**
     * Проверяет переход по каждой ссылке меню.
     */
    @Test
    public static void menuPageTest(){
        ElementsCollection elements = getElementsMenu();

        SelenideElement element;
        SelenideElement menuPane;
        SelenideElement menuItemPane;
        ElementsCollection menuItemsCollection;
        for (int i = 0; i < 1/*elements.size()*/; i++){
            element = elements.get(i);
            clickMenuItem(element);

            menuPane = waitVisibleMenuPane();
            menuItemPane = getMenuItemPane(menuPane, element);
            menuItemsCollection = getMenuItemsCollection(menuItemPane);

            SelenideElement item;
            for (int el = 0; el < 10/*menuItemsCollection.size()*/; el++){
                item = menuItemsCollection.get(el);

                clickMenuItem(item);
                checkOpenLink(item);

                back();
                clickMenuItem(element);

                waitVisibleMenuPane();
            }
        }
    }

    /**
     * Проверяет страницы пункта меню "Продукты".
     */
    @Test
    public static void productPageTest(){
        ElementsCollection elements = getElementsMenu();
        SelenideElement element = elements.get(0);

        clickMenuItem(element);

        SelenideElement menuPane = waitVisibleMenuPane();
        SelenideElement menuItemPane = getMenuItemPane(menuPane, element);
        ElementsCollection menuItemsCollection = getProductMenuItemsCollection(menuItemPane);

        SelenideElement pageLink;
        for (int i = 0; i < menuItemsCollection.size(); i++){
            pageLink = menuItemsCollection.get(i);

            clickMenuItem(pageLink);
            SelenideElement textBlock = checkExistTextBlockProductPage();
            SelenideElement text = getTextFromTextBlock(textBlock);
            checkTextBlockProductPage(text);
            checkExistLinkTextBlock(text);

            back();
            clickMenuItem(element);
            waitVisibleMenuPane();
        }
    }
}
