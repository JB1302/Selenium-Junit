import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

public class primeraPrueba {

    private WebDriver driver;

    //Se configura el driver + La pagina
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
        driver.get("https://demoqa.com/text-box");
    }

    @Test
    public void LlenarFormularioTextBox() {

        // Esperar que la página cargue
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Mapear los inputs
        WebElement nombre = wait.until(ExpectedConditions.elementToBeClickable(By.id("userName")));
        WebElement correo = driver.findElement(By.id("userEmail"));
        WebElement direccionActual = driver.findElement(By.id("currentAddress"));
        WebElement direccionPermanente = driver.findElement(By.id("permanentAddress"));
        WebElement botonEnviar = driver.findElement(By.id("submit"));

        // Popular los campos
        nombre.sendKeys("Ericka Chinchilla");
        correo.sendKeys("ericka@test.com");
        direccionActual.sendKeys("San José, Costa Rica");
        direccionPermanente.sendKeys("Cartago, Costa Rica");

        // Scroll hasta el botón por si está fuera de pantalla
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botonEnviar);

        // Hacer clic en Submit
        botonEnviar.click();

        // Esperar a que aparezca el contenedor con los resultados
        WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output")));

        // Buscar los textos dentro del contenedor
        WebElement resultadoNombre = output.findElement(By.id("name"));
        WebElement resultadoCorreo = output.findElement(By.id("email"));

        // Validar que contengan el texto esperado
        assertTrue(resultadoNombre.getText().contains("Ericka Chinchilla"));
        assertTrue(resultadoCorreo.getText().contains("ericka@test.com"));

        System.out.println("✅ Test completado: se llenaron los campos y se validó la salida correctamente.");
    }

    @After
    public void quitDriver() {
        if(driver != null) {
            driver.quit();
        }
    }
}

