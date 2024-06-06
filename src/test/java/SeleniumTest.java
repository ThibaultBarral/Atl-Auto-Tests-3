import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Indiquez le chemin vers le driver de votre navigateur
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Exécute le navigateur en mode headless
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testSearch() {
        driver.get("https://www.google.com");

        // Vérifie le titre de la page
        String pageTitle = driver.getTitle();
        assertEquals("Google", pageTitle);

        // Trouve la barre de recherche et effectue une recherche
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("automatisation des tests logiciels");
        searchBox.submit();

        // Attendre que les résultats soient affichés
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));

        // Vérifie que les résultats contiennent le terme de recherche
        WebElement searchResults = driver.findElement(By.id("search"));
        assertTrue(searchResults.getText().contains("automatisation des tests logiciels"));

        // Vérifie la présence d'un élément spécifique (par exemple un lien vers Wikipedia)
        WebElement specificElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Wikipedia")));
        assertTrue(specificElement.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}