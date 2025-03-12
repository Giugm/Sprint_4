package Site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class OrderPage {
    //WebDriver
    private final WebDriver driver;
    //Форма заказа
    private final By orderForm = By.className("Order_Form__17u6u");
    // Поле Имя
    private final By nameInput = By.xpath(".//div[@class='Input_InputContainer__3NykH']/input[contains(@placeholder,'Имя')]");
    // Поле Фамилия
    private final By surnameInput = By.xpath(".//div[@class='Input_InputContainer__3NykH']/input[contains(@placeholder,'Фамилия')]");
    // Поле Адрес: куда привезти
    private final By addressInput = By.xpath(".//div[@class='Order_Form__17u6u']/div[contains(@class,'Input_InputContainer')]/input[contains(@placeholder, 'Адрес')]");
    // Поле Станция метро
    private final By metroInput = By.xpath(".//div[@class='select-search__value']/input[contains(@placeholder,'Станция метро')]");
    // Лист метро
    private final By metroList = By.className("select-search__select");
    // Список станций метро
    private final By metroListItems = By.xpath(".//div[@class='select-search__select']/ul/li[@class='select-search__row']/button[contains(@class, 'Order_Select')]/div[contains(@class, 'Order_Text')]");
    // Поле Телефон: на него позвонит курьер
    private final By phoneInput = By.xpath("//div[@class='Input_InputContainer__3NykH']/input[contains(@placeholder,'Телефон: на него позвонит курьер')]");
    // Кнопка Далее
    private final By nextButton = By.xpath(".//div[contains(@class,'Order_NextButton')]/button[contains(@class,'Button_Button')]");
    // Поле Когда привезти самокат
    private final By dateInput = By.xpath(".//div[@class='react-datepicker__input-container']/input[contains(@placeholder,'Когда привезти самокат')]");
    // Выбранная дата в календаре
    private final By dateSelected = By.xpath("//div[contains(@class,'react-datepicker__day--selected')]");
    // Поле Срок аренды
    private final By rentalInput = By.xpath(".//div[contains(@class,'Order_Form')]/div[contains(@class,'Dropdown-root')]");
    // Список Срока аренды
    private final By rentalList = By.xpath(".//div[contains(@class,'Dropdown-root')]/div[@class='Dropdown-menu']/div[@class='Dropdown-option']");
    // Список доступных цветов
    private final By colorList = By.xpath(".//div[contains(@class,'Order_Checkboxes')]/label");
    // Поле комментария
    private final By commentInput = By.xpath(".//div[contains(@class, 'Input_InputContainer')]/input[contains(@placeholder, 'Комментарий')]");
    // Кнопка Заказать
    private final By orderButton = By.xpath(".//div[contains(@class,'Order_Button')]/button[not(contains(@class,'Button_Inverted'))]");
    // Кнопка "Да" в окне оформления заказа
    private final By yesButton = By.xpath(".//div[contains(@class, 'Order_Modal')]/div[contains(@class, 'Order_Buttons')]/button[not(contains(@class, 'Button_Inverted'))]");
    // Текст об успешном оформлении заказа
    private final By successMessage = By.xpath(".//div[contains(@class,'Order_Modal')]/div[contains(@class, 'Order_ModalHeader')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для ожидания загрузки формы заказа
    public void waitForLoadOrderForm() {
        new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(this.driver.findElement(this.orderForm)));
    }
    // Метод для ожидания загрузки какого либо элемента страницы
    private void waitForElementLoad(By elementToLoad) {
        new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(this.driver.findElement(elementToLoad)));
    }
    // Метод для установки значения в поле "Имя"
    public void setName(String name) {
        this.driver.findElement(this.nameInput).sendKeys(name);
    }
    // Метод для установки значения в поле "Фамилия"
    public void setSurnameInput(String surname) {
        this.driver.findElement(this.surnameInput).sendKeys(surname);
    }
    // Метод для установки значения в поле "Адрес"
    public void setAddressInput(String address) {
        this.driver.findElement(this.addressInput).sendKeys(address);
    }
    // Метод для выбора элемента из выпадающего списка
    private void chooseElementFromDropdown(By dropdownElements, String elementToChoose) {
        // Находим все элементы выпадающего списка
        List<WebElement> elements = this.driver.findElements(dropdownElements);
        // Проходим по каждому элементу
        for (WebElement element : elements) {
            // Если текст элемента совпадает с нужным
            if (element.getText().equals(elementToChoose)) {
                // Кликаем по элементу
                element.click();
                return; // Выходим из метода
            }
        }
    }
    //Метод для установки значения в поле "Станция метро"
    public void setMetroInput(String metro) {
        this.driver.findElement(this.metroInput).sendKeys(metro);
        this.waitForElementLoad(this.metroList);
        this.chooseElementFromDropdown(this.metroListItems, metro);
    }
    // Метод для установки значения в поле "Телефон"
    public void setPhoneInput(String phone) {
        this.driver.findElement(this.phoneInput).sendKeys(phone);
    }
    // Метод для нажатия на кнопку "Далее"
    public void clickNextButton() {
        this.driver.findElement(this.nextButton).click();
    }
    // Метод для установки значения в поле "Дата доставки"
    public void setDateInput(String date) {
        this.driver.findElement(this.dateInput).sendKeys(date);
        this.driver.findElement(this.dateSelected).click();
    }
    //Метод для установки значения в поле "Срок аренды"
    public void setRentalInput(String rental) {
        this.driver.findElement(this.rentalInput).click();
        this.chooseElementFromDropdown(this.rentalList, rental);
    }
    //Метод для установки значения в чек-боксы "Цвет самоката"
    public void setColorList(String color) {
        this.chooseElementFromDropdown(this.colorList, color);
    }
    // Метод для установки значения в поле "Комментарий"
    public void setCommentInput(String comment) {
        this.driver.findElement(this.commentInput).sendKeys(comment);
    }
    // Метод для нажатия на кнопку "Заказать"
    public void clickOrderButton() {
        this.driver.findElement(this.orderButton).click();
    }
    // Метод для нажатия на кнопку "Да" в окне оформления заказа
    public void clickYesButton() {
        this.driver.findElement(this.yesButton).click();
    }
    // Метод для получения текста об успешном оформлении заказа
    public String getSuccessMessage() {
        this.waitForElementLoad(this.successMessage);
        return this.driver.findElement(this.successMessage).getText();
    }
    public void makeOrder() {
        this.clickOrderButton();
        this.waitForElementLoad(this.yesButton);
        this.clickYesButton();
    }

}
