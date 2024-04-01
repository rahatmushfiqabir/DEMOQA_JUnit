import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class utils {
    public static void scroll(WebDriver driver, int w, int h){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy("+w+","+h+")", "");
    }
}
