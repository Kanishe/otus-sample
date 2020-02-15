import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_Git {
    private WebDriver driver; //driver initialization
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSampleCase() throws Exception {
        driver.get("https://www.ozon.ru/"); //open page
        driver.findElement(By.name("search")).clear(); //field clearing
        driver.findElement(By.name("search")).sendKeys("java шилдт");
        driver.findElement(By.cssSelector("svg.ui-ai2")).click();
        driver.findElement(By.cssSelector("svg.ui-ai2")).click();

        //кнопка в корзину
        driver.findElement(By.xpath("//a[contains(text(), 'Шилдт') and contains(text(), 'Java')]/parent::div//button[@qa-id='tile-buy-button']")).click();
        // Можно смотреть просто просто по кнопке В корзину
        driver.findElement(By.cssSelector("[data-widget='cart'] .f-caption--bold")).click(); // Fixed the locator here (Replaced dynamic svg on the css class)
        //кнопка удалить из корзины
        driver.findElement(By.xpath("//div[@data-widget='split']//span[contains(text(), 'Удалить')]")).click(); // Fixed the locator here
        driver.findElement(By.xpath("//div[@class='modal-content']//button")).click(); // Fixed the locator here (Fixed this locator too)
    }

    @After
    public void tearDown() throws Exception {
        driver.quit(); //
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
