package Site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {

    // WebDriver
    private final WebDriver driver;
    //Заголовок стрелочек
    private final By headerAccordion = By.className("accordion__heading");
    // Текст в раскрывающемся списке стрелочек
    private final By accordionText = By.xpath(".//div[@class='accordion__panel']/p");
    // Кнопка для оформления заказа в шапке сайта
    private final By orderButtonHeader = By.xpath("./html/body/div/div/div/div[1]/div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    // Кнопка для оформления заказа в центре сайта
    private final By orderButtonMiddle = By.xpath(".//div[contains(@class, 'Home_Road')]/div[contains(@class, 'Home_Finish')]/button");
    // Кнопка для принятия куки
    private final By cookieButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    // Метод для ожидания загрузки аккордеона
    public void waitForLoadAccordion(int index) {
        new WebDriverWait(this.driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(this.driver.findElements(this.accordionText).get(index)));
    }
    // Метод для нажатия на заголовок стрелочек
        public void clickHeaderAccordion(int index) {
        this.driver.findElements(this.headerAccordion).get(index).click();
    }
    // Метод для получения текста в заголовке стрелочек
        public String getHeaderAccordion(int index) {
        return this.driver.findElements(this.headerAccordion).get(index).getText();
    }
    // Метод для получения текста из раскрывающегося списка
        public String getAccordionText(int index) {
        return this.driver.findElements(this.accordionText).get(index).getText();
    }
    // Метод для проверки раскрытия блока аккордеона
    public boolean isAccordionDisplayed(int index) {
        return this.driver.findElements(this.accordionText).get(index).isDisplayed();
    }
    // Метод для нажатия на кнопку оформления заказа в шапке сайта
    public void clickOrderButtonHeader() {
        this.driver.findElement(this.orderButtonHeader).click();
    }
    // Метод для нажатия на кнопку оформления заказа в центре сайта
    public void clickOrderButtonMiddle() {
        this.driver.findElement(this.orderButtonMiddle).click();
    }
    public void clickToCookie() {
        this.driver.findElement(this.cookieButton).click();
    }


}
