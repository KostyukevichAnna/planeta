package tests.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Шаги для проверки переходов по меню Витрины planeta.tc.
 */
public class planetaMenuPagesSteps {
    private static String[] itemInNewWindowList = {"Установить антивирус", "Справка и помощь", "Личный кабинет", "Телепрограмма", "Настроить программы"};
    private static String[] itemRedirectList = {"Подключитьсяк Планете"};

    /**
     * Определяет входит ли пункт меню в список страниц, которые открываются в новой вкладке.
     * @param itemName - название пункта меню.
     * @return - пункт меню входит в список или не в ходит.
     */
    private static boolean isItemInNewWindow(String itemName){
        for (String item : itemInNewWindowList)
            if (item.equals(itemName))
                return true;

        return false;
    }

    /**
     * Определяет входит ли пункт меню в список страниц, для которых происходит редирект.
     * @param itemName - название пункта меню.
     * @return - пункт меню входит в список или не в ходит.
     */
    private static boolean isItemRedirect(String itemName){
        for (String item : itemRedirectList)
            if (item.equals(itemName))
                return true;

        return false;
    }

    /**
     * Возвращает список ссылок пунктов меню.
     * @param pane - плашка меню.
     * @return - список ссылок пунктов меню.
     */
    public static ElementsCollection getMenuItemsCollection(SelenideElement pane){
        return pane.$$("a");
    }

    /**
     * Проверяет открытие страницы при клике на пункт меню в плашке.
     * @param element - пункт меню.
     */
    public static void checkOpenLink(SelenideElement element){
        String link = element.attr("href");
        String itemName = element.attr("innerText").replace("\n", "").replace("<br>", " ").trim();

        if(!isItemInNewWindow(itemName) && !isItemRedirect(itemName))
            checkOpenNewPage(url(), link);
    }

    /**
     * Проверяет открытие страницы при клике.
     * @param currentUrl - url текущей вкладки браузера.
     * @param link - url, по которому производился переход.
     */
    private static void checkOpenNewPage(String currentUrl, String link) {
        Assert.assertEquals(currentUrl, link, "Открыта страница с другим url.");
    }

    /**
     * Возвращает список пунктов меню, содержащий продукты.
     * @param pane - плашка меню.
     * @return - список пунктов меню, содержащий продукты.
     */
    public static ElementsCollection getProductMenuItemsCollection(SelenideElement pane){
        return pane.$("div.Grid-cell").$$("a");
    }

    /**
     * Проверяет наличие блока с текстом на странице при открытии пункта меню "Продукты".
     * @return - блок с текстом.
     */
    public static SelenideElement checkExistTextBlockProductPage(){
        SelenideElement textBlock = $(".SeoText");

        Assert.assertTrue(textBlock != null, "Отсутствует блок с тектстом.");

        return textBlock;
    }

    /**
     * Возвращает элемент, содержащий текст, из блока с текстом.
     * @param textBlock - блок с текстом.
     * @return - элемент, содержащий текст, из блока с текстом.
     */
    public static SelenideElement getTextFromTextBlock(SelenideElement textBlock){
        return textBlock.$("div[data-id='tariffs']");
    }

    /**
     * Проверяет соответствие текста ожидаемому результату.
     * @param textBlock - блок с текстом.
     */
    public static void checkTextBlockProductPage(SelenideElement textBlock){
        String expectedText = "Обратите внимание на раздел Акции. В нем содержится актуальная информация о специальных предложениях, скидках на оборудование и возможностях получения бонусных баллов — чатлов, с помощью которых вы можете компенсировать затраты по тарифам «Планеты» на интернет и другие услуги.";

        Assert.assertNotEquals(textBlock.attr("innerText"), expectedText, "Текст блока не соответствует ожидаемому.");
    }

    /**
     * Мэп с ссылками для ожидаемого результата.
     */
    private static Map<String, String> expectedLinks = new HashMap<String, String>();

    /**
     * Заполняет мэп с ожидаемым результатом.
     */
    private static void putExpectedLinks(){
        expectedLinks.put("Акции", "https://planeta.tc/ekb/articles/inet/actions");
        expectedLinks.put("оборудование", "https://planeta.tc/ekb/articles/inet/products#digital-home");
        expectedLinks.put("интернет", "https://planeta.tc/ekb/articles/inet/description");
    }

    /**
     * Проверяет наличие гиперссылок в тексте на соответствие ожидаемому результату.
     * @param textBlock - элемент, содержащий текст.
     */
    public static void checkExistLinkTextBlock(SelenideElement textBlock){
        putExpectedLinks();

        ElementsCollection links = textBlock.$$("a");

        String linkActual;
        String wordActual;
        String linkExpected;
        for (SelenideElement link : links) {
            linkActual = link.attr("href");
            wordActual = link.attr("innerText");
            linkExpected = expectedLinks.get(wordActual);

            Assert.assertTrue(linkExpected != null, "Не указана ссылка для слова " + wordActual + ".");
            Assert.assertEquals(linkActual, linkExpected);
        }
    }

    /**
     * Возвращает элемент пункта меню "Сравнить продукты".
     * @param pane - плашка меню.
     * @return - элемент пункта меню "Сравнить продукты".
     */
    public static SelenideElement getCompareProductMenuItem(SelenideElement pane){
        return pane.$$("a").find(attribute("innerText", " Сравнить продукты"));
    }

    /**
     * Возвращает список продуктов страницы "Сравнить продукты".
     * @return - список продуктов страницы "Сравнить продукты".
     */
    public static ElementsCollection getProductCollection(){
        return  $$("tr").exclude(attribute("class", "choice__header"));
    }

    /**
     * Кликает на ссылку с названием продукта.
     * @param productLink - элемент, содержащий ссылку на продукт.
     */
    public static void clickProductLink(SelenideElement productLink){
        productLink.click();
    }

    /**
     * Выполняет движение мышью по строке продукта.
     * @param product - строка продукта.
     */
    public static void mouseHoverProduct(SelenideElement product){
        product.hover();
    }

    /**
     * Возвращает элемент, содержащий кнопку "Выбрать".
     * @param product - строка продукта в таблице на странице "Сравнить продукты".
     * @return - элемент, содержащий кнопку "Выбрать".
     */
    public static SelenideElement getElementChooseButton(SelenideElement product){
        SelenideElement button = product.$(".choice__button-wrapper a");

        button.shouldBe(visible);

        return button;
    }

    /**
     * Возвращает гиперссылку на страницу продукта.
     * @param element - элемент, содержащий гиперссылку на продукт.
     * @return - гиперссылку на страницу продукта.
     */
    public static String getProductUrl(SelenideElement element){
        return element.attr("href");
    }

    /**
     * Проверяет отображение кнопки "Выбрать" на странице "Сравнить продукты".
     * @param button - элемент кнопки.
     */
    public static void checkExistChooseButton(SelenideElement button){
         Assert.assertTrue(button.isDisplayed(), "Не отображается кнопка \"Выбрать\"");
    }

    /**
     * Проверяет название кнопки на соответствие ожидаемому результату.
     * @param button - элемент кнопки.
     */
    public static void checkNameChooseButton(SelenideElement button){
        Assert.assertEquals(button.innerText(), "Выбрать", "Отображается неверное название кнопки.");
    }

    /**
     * Проверяет значение атрибута class кнопки "Выбрать".
     * @param button - элемент кнопки.
     */
    public static void checkClassChooseButton(SelenideElement button){
        Assert.assertEquals(button.attr("class"), "button-violet choice__button", "Кнопка \"Выбрать\" имеет неверное значение класса.");
    }

    /**
     * Проверяет открытие страницы при клике на гиперссылку с названием продукта на странице "Сравнить продукты".
     * @param productUrl - url, по которому происходиn открытие страницы.
     */
    public static void checkOpenProductLink(String productUrl){
        checkOpenNewPage(url(), productUrl);
    }
}
