package tests.testSuits;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.open;
import static tests.steps.planetaConnectionSteps.getTestData;
import static tests.steps.planetaMenuPagesSteps.*;
import static tests.steps.planetaMenuSteps.*;

/**
 * Сценарии для проверки переходов по меню Витрины planeta.tc.
 */
public class planetaMenuPagesSuite {
    /**
     * Запуск веб-драйвера Chrome, открытие сайта.
     */
    @BeforeClass
    public void startChrome() {
        System.setProperty("selenide.browser", "chrome");
        open("https://planeta.tc/ekb");
    }

    /**
     * Запуск веб-драйвера Firefox, открытие сайта.
     */
//    @BeforeClass
    public void startFirefox() {
        System.setProperty("selenide.browser", "firefox");
        open("https://planeta.tc/ekb");

        getTestData();
    }

    /**
     * Запуск веб-драйвера Opera, открытие сайта.
     */
//    @BeforeClass
    public void startOpera() {
        System.setProperty("selenide.browser", "opera");
        open("https://planeta.tc/ekb");

        getTestData();
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
//        for (SelenideElement element : elements)
        for (int i = 0; i < 1/*elements.size()*/; i++){
            element = elements.get(i);
            clickMenuItem(element);

            menuPane = waitVisibleMenuPane();
            menuItemPane = getMenuItemPane(menuPane, element);
            menuItemsCollection = getMenuItemsCollection(menuItemPane);

            SelenideElement item;
//            for (SelenideElement item : menuItemsCollection)
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

         for (SelenideElement pageLink : menuItemsCollection){
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

    /**
     * Проверяет страницу пункта меню "Сравнить продукты".
     */
    @Test
    public static void compareProductPageTest(){
        ElementsCollection elements = getElementsMenu();

        SelenideElement menuPane;
        SelenideElement menuItemPane;
        SelenideElement compareProductMenuItem;
        SelenideElement chooseButton;
        String productUrl;

        for (SelenideElement element : elements){
            clickMenuItem(element);

            menuPane = waitVisibleMenuPane();
            menuItemPane = getMenuItemPane(menuPane, element);
            compareProductMenuItem = getCompareProductMenuItem(menuItemPane);

            clickMenuItem(compareProductMenuItem);

            ElementsCollection productsCollection = getProductCollection();

            for (SelenideElement product : productsCollection) {
                mouseHoverProduct(product);

                chooseButton = getElementChooseButton(product);

                checkExistChooseButton(chooseButton);
                checkNameChooseButton(chooseButton);
                checkClassChooseButton(chooseButton);

                productUrl = getProductUrl(chooseButton);

                clickProductLink(chooseButton);

                checkOpenProductLink(productUrl);

                back();
            }
        }
    }
}
