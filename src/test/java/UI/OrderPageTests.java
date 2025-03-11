package UI;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Site.MainPage;
import Site.OrderPage;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.firefox.FirefoxDriver;



    @RunWith(Parameterized.class)
    public class OrderPageTests {
        // Создаём WebDriver
        private WebDriver driver;

        // Ссылка для драйвера
        private final String mainURL = "https://qa-scooter.praktikum-services.ru/";

        // Переменные
        private final String name;
        private final String surname;
        private final String address;
        private final String metro;
        private final String phone;
        private final String date;
        private final String term;
        private final String color;
        private final String comment;

        // Сообщение для сравнения
        private final String expectedOrderSuccessText = "Заказ оформлен";

        public OrderPageTests(String name, String surname, String address, String metro, String phone, String date, String term, String color, String comment) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.metro = metro;
            this.phone = phone;
            this.date = date;
            this.term = term;
            this.color = color;
            this.comment = comment;
        }

        @Parameterized.Parameters()
        public static Object[][] testDataForOrder() {
            return new Object[][]{
                    {"Петр", "Лошкин", "ул. Верхняя Красносельская улица, д. 34,кв. 61", "Красносельская", "89125126121", "12.03.2025", "сутки", "чёрный жемчуг", "Привет!"},
                    {"Александр", "Александрович", "Большая Семёновская улица, д. 21, кв. 16", "Электрозаводская", "8913333333333", "15.03.2025", "пятеро суток", "серая безысходность", "Спасибо!"},
            };
        }

        @Before
        public void startUp() {
            WebDriverManager.chromedriver().setup();
            //WebDriverManager.firefoxdriver().setup();
            // Инициализация WebDriver
            this.driver = new ChromeDriver(); // Баг кнопки "Да на окне "Хотите оформить заказ?"
            //this.driver = new FirefoxDriver(); // У FireFox багов не обнаружило, тесты сработали
            // Открытие главной страницы
            this.driver.get(this.mainURL);
        }

        @After
        public void tearDown() {
            this.driver.quit(); //1
        }

        // Метод оформления заказа
        private void makeOrder(OrderPage orderPage) {
            orderPage.waitForLoadOrderForm();
            orderPage.setName(this.name);
            orderPage.setSurnameInput(this.surname);
            orderPage.setAddressInput(this.address);
            orderPage.setMetroInput(this.metro);
            orderPage.setPhoneInput(this.phone);
            orderPage.clickNextButton();

            orderPage.setDateInput(this.date);
            orderPage.setRentalInput(this.term);
            orderPage.setColorList(this.color);
            orderPage.setCommentInput(this.comment);
            orderPage.makeOrder();
        }


        // Тест на создание заказа нажатием на кнопку "Заказать" в шапке сайта
        @Test
        public void orderWithOrderButtonHeader() {
            MainPage mainPage = new MainPage(this.driver);
            OrderPage orderPage = new OrderPage(this.driver);
            mainPage.clickToCookie();
            mainPage.clickOrderButtonHeader();
            makeOrder(orderPage);
            MatcherAssert.assertThat("Заказ не создался, проблема в заказе", orderPage.getSuccessMessage(), containsString(this.expectedOrderSuccessText)
            );
        }
        // Тест на создание заказа нажатием на кнопку "Заказать" в центре сайта
        @Test
        public void orderWithСlickOrderButtonMiddle() {
            MainPage mainPage = new MainPage(this.driver);
            OrderPage orderPage = new OrderPage(this.driver);
            mainPage.clickToCookie();
            mainPage.clickOrderButtonMiddle();
            makeOrder(orderPage);
            MatcherAssert.assertThat("Заказ не создался, проблема в заказе", orderPage.getSuccessMessage(), containsString(this.expectedOrderSuccessText)
            );
        }


    }







