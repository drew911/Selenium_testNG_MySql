import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Testas {
    public WebDriver driver;


    public void pirmasTestukas() throws InterruptedException {
        driver.get("http://motoangaras.lt/#servisas");
        WebElement form = driver.findElements(By.tagName("form")).get(0);
        WebElement name = form.findElement(By.id("name"));
        WebElement email = form.findElement(By.id("email"));
        WebElement message = form.findElement(By.id("message"));
        WebElement button = form.findElements(By.tagName("button")).get(0);
        name.sendKeys("Jonas");
        email.sendKeys("Jonas@kapitonas.lt");
        message.sendKeys("fake message");
        Thread.sleep(2000);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        Thread.sleep(500);
        button.click();

        String msg = driver.findElements(By.className("success_message")).get(0).getText();
        Assert.assertEquals("Laiškas sėkmingai išsiųstas.",msg);

    }

    @Test
     public void skelbiuLt(){
        List<String> urls = new ArrayList<>();
            for (int i = 1; i < 90; i++){
                driver.get("https://www.skelbiu.lt/skelbimai/komunikacijos/mobilus-telefonai/"+i);
                WebElement table = driver.findElement(By.id("itemsList")).findElements(By.tagName("ul")).get(0);
                List<WebElement> cards = table.findElements(By.tagName("li"));
                for (int a = 0; a < cards.size(); a++) {
                    String url =    cards.get(a).findElements(By.tagName("a")).get(0).getAttribute("href");
                    if(!url.contains("www.kainos.lt")) {
                        urls.add(url);
                        System.out.println(url);
                    }
                }
            }
        }



    @BeforeClass
    public void ignitionStart(){
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver93.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }


    @AfterClass
    public void end(){
//        driver.quit();
    }

 public void duombazesExample() throws SQLException {
     Connection c = connect();
     Statement stmt = null;

     stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     String sql2 = "INSERT INTO `test` (`id`, `name`) VALUES (NULL, 'n2');";
     stmt.executeUpdate(sql2);
 }
    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("", "", "");
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
