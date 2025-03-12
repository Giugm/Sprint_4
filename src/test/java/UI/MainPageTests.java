package UI;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class MainPageTests {
        // Создаём WebDriver
        private WebDriver driver;

        // Ссылка для драйвера
        private final String mainURL = "https://qa-scooter.praktikum-services.ru/";

        // Номера стрелочек в Вопросе о важном
        private int numberOfElement;

        // Ожидаемый текст в заголовке стрелочки
        private String textOfElements;

        // Ожидаемый текст в раскрытой стрелочке
        private String openAccordionText;


        // Конструктор класса MainPageQuestionsAboutImportantThings
        public MainPageTests(int numberOfElement, String textOfElements, String openAccordionText) {
            this.numberOfElement = numberOfElement;
            this.textOfElements = textOfElements;
            this.openAccordionText = openAccordionText;
        }

        @Parameterized.Parameters()
        public static Object[][] testData() {
            return new Object[][]{ // Цифра = номер акардиона в массиве. Первый текст = текст загаловка. Второй текст = текст в раскрытой стрелочке.
                    {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                    {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                    {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                    {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                    {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                    {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                    {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                    {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
            };
        }

        @Before
        public void startUp() {
            WebDriverManager.chromedriver().setup();
            //WebDriverManager.firefoxdriver().setup();
            // Инициализация WebDriver
            this.driver = new ChromeDriver();
            //this.driver = new FirefoxDriver();
            // Открытие главной страницы
            this.driver.get(this.mainURL);
        }

        @After
        public void tearDown() {
            this.driver.quit();
        }


        @Test
        public void checkAccordionIsCorrect() {
            Site.MainPage mainPage = new Site.MainPage(this.driver);

            mainPage.clickToCookie();
            mainPage.clickHeaderAccordion(this.numberOfElement);
            mainPage.waitForLoadAccordion(this.numberOfElement);

            if (mainPage.isAccordionDisplayed(this.numberOfElement)) {
                verifyAccordionHeader(mainPage);
                verifyAccordionContent(mainPage);
            } else {
                fail("Стрелочка #" + this.numberOfElement + " не открылась");
            }
        }
            private void verifyAccordionHeader(Site.MainPage mainPage) {
                String actualHeaderText = mainPage.getHeaderAccordion(this.numberOfElement);
                MatcherAssert.assertThat("Проблема с текстом в заголовке стрелочки #" + this.numberOfElement, actualHeaderText, equalTo(this.textOfElements));
            }

            private void verifyAccordionContent(Site.MainPage mainPage) {
                String actualAccordionText = mainPage.getAccordionText(this.numberOfElement);
                MatcherAssert.assertThat("Проблема с текстом внутри стрелочки #" + this.numberOfElement, actualAccordionText, equalTo(this.openAccordionText));
            }


        }



