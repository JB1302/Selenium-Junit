import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class segundaPrueba {

    private WebDriver driver;

    @Before
    public void setUp() {

        // Configurar la ruta correcta para el ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
        ChromeOptions co = new ChromeOptions();

        // Opciones adicionales, maximizar la pantalla
        co.addArguments("--start-maximized");

        // Inicializar el navegador
        driver = new ChromeDriver(co);
        driver.manage().window().maximize();

        // Enviar la página para el testing
        driver.get("https://demoqa.com/radio-button");
    }

    // -----------------------------
    // TEST 1: Verificar visibilidad de los radio buttons
    // -----------------------------
    @Test
    public void VerificarCamposVisibles() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement labelYes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='yesRadio']")));
        WebElement labelImpressive = driver.findElement(By.xpath("//label[@for='impressiveRadio']"));
        WebElement labelNo = driver.findElement(By.xpath("//label[@for='noRadio']"));

        assertTrue(labelYes.isDisplayed());
        assertTrue(labelImpressive.isDisplayed());
        assertTrue(labelNo.isDisplayed());

        System.out.println("✅ Test 1: Los radio buttons están visibles correctamente");
    }

    // -----------------------------
    // TEST 2: Seleccionar 'Yes' y verificar resultado
    // -----------------------------
    @Test
    public void SeleccionarYes() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement botonYes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='yesRadio']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botonYes);
        botonYes.click();

        WebElement resultado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("text-success")));
        assertEquals("Yes", resultado.getText());

        System.out.println("✅ Test 2: Se seleccionó 'Yes' y el resultado fue validado correctamente");
    }
    @After
    public void quitDriver() {
        if(driver != null) {
            driver.quit();
        }
    }
}

 