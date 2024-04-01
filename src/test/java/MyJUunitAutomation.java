import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJUunitAutomation {

    WebDriver driver;
    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @Test
    public void writeSomething(){
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys("Rahat Mushfiq");
        driver.findElement(By.id("userEmail")).sendKeys("abir_rahat@yahoo.com");
        driver.findElement(By.id("currentAddress")).sendKeys("Dhaka Bangladesh");
        driver.findElement(By.id("permanentAddress")).sendKeys("Dhaka Bangladesh");
        utils.scroll(driver,0,500);
//        driver.findElement(By.id("submit")).click();
        driver.findElements(By.cssSelector("[type='button']")).get(1).click();

//        List<WebElement> btnSubmit;

        String nameActual = driver.findElement(By.id("name")).getText();
        String nameExpected = "Rahat Mushfiq";
        Assertions.assertTrue(nameActual.contains(nameExpected));
    }
    @Test
    public void buttonClick(){
        driver.get("https://demoqa.com/buttons");
//        driver.findElement(By.id("vqfwQ")).click();
        Actions actions = new Actions(driver);
        List <WebElement> buttons = driver.findElements(By.className("btn-primary"));
        actions.doubleClick(buttons.get(0)).perform();
        actions.contextClick(buttons.get(1)).perform();
        actions.click(buttons.get (2)).perform();
    }

    @Test
    public void handleMultipleTap() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id("tabButton")).click();
        Thread.sleep(3000);
        ArrayList<String> w = new ArrayList(driver.getWindowHandles());
//switch to open tab
        driver.switchTo().window(w.get(1));
//        System.out.println("New tab title: " + driver.getTitle());
        String text = driver.findElement(By.id("sampleHeading")).getText();
        Assertions.assertEquals(text,"This is a sample page");
        driver.close();
        driver.switchTo().window(w.get(0));
    }

    @Test
    public void handleMultipleWindow(){
        driver.get("https://demoqa.com/browser-windows");

//Thread.sleep(5000);
//WebDriverWait wait = new WebDriverWait(driver, 30);
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("windowButton")));
        driver.findElement(By.id(("windowButton"))).click();
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                String text= driver.findElement(By.id("sampleHeading")).getText();
                Assertions.assertTrue(text.contains("This is a sample page"));
            }

        }
        driver.close();
        driver.switchTo().window(mainWindowHandle);

    }

    @AfterAll
    public void closeDriver(){

    }
}
