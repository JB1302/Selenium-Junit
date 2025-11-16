import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class loginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {

        // Configurar la ruta correcta para el ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
        ChromeOptions co = new ChromeOptions();

        co.addArguments("--start-maximized");

        // Inicializar el navegador
        driver = new ChromeDriver(co);
        driver.manage().window().maximize();

        // Iniciar Wait mientras carga
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Enviar la página para el testing
        driver.get("https://pawtify.onrender.com/login");
    }

    @Test
    public void loginCredencialesInvalidas() {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginEmail")));

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginPassword")));

        WebElement loginBtn = driver.findElement(By.xpath("/html/body/main/section/div/div/form/div[4]/button"));

        // Rellenar datos inválidos
        emailInput.sendKeys("correo_falso@prueba.com");
        passwordInput.sendKeys("123");
        loginBtn.click();

        // Esperar mensaje de error
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/section/div[1]/div")));

        assertTrue(errorMsg.getText().contains("Usuario o contraseña incorrectos."));

    }

    @After
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
