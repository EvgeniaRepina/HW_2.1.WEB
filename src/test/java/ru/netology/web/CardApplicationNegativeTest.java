package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardApplicationNegativeTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
//        System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe"); // запускдрайвера, сейчас
//        это делает драйверменеджер, см ниже
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
//отключаем графический интерфейс:
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

//        Для selenide headless-режим активируется в .appveyor.yml (см туда)

//        driver = new ChromeDriver(); // вариант если не отключаем графический интерфейс

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }


    @Test
    void shouldTestEmptyName() throws InterruptedException {
        driver.get("http://localhost:9999");

//        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Василий");
//        Thread.sleep(5000); замедление процесса Чтобы что-то успеть увидеть
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79270000000");
//        Thread.sleep(5000);
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
//        Thread.sleep(5000);
        driver.findElement(By.className("button__text")).click();
//        Thread.sleep(5000);

        WebElement form = driver.findElement(By.cssSelector(".input_invalid[data-test-id=name]"));
        String text = form.findElement(By.className("input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }
    @Test
    void shouldTestEmptyPhone() throws InterruptedException {
        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Василий");
//        Thread.sleep(5000); замедление процесса Чтобы что-то успеть увидеть
//        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79270000000");
//        Thread.sleep(5000);
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
//        Thread.sleep(5000);
        driver.findElement(By.className("button__text")).click();
//        Thread.sleep(5000);

        WebElement form = driver.findElement(By.cssSelector(".input_invalid[data-test-id=phone]"));
        String text = form.findElement(By.className("input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }
    @Test
    void shouldTestLatinName() throws InterruptedException {
        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("ghjkkklll");
//        Thread.sleep(5000); замедление процесса Чтобы что-то успеть увидеть
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79270000000");
//        Thread.sleep(5000);
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
//        Thread.sleep(5000);
        driver.findElement(By.className("button__text")).click();
//        Thread.sleep(5000);

        WebElement form = driver.findElement(By.cssSelector(".input_invalid[data-test-id=name]"));
        String text = form.findElement(By.className("input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }
}
