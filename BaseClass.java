package week5.day2;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public ChromeDriver driver;
	String fileName;

	@Parameters({ "url", "username", "password" })

	@BeforeMethod
	public void preConditions(String url, String username, String password) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.get(url);
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys(username);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(password);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		Thread.sleep(5000);

	}

	@AfterMethod
	public void postConditions() {
		driver.close();
	}

	@DataProvider(name = "fetchData")
	public String[][] fetchData() throws IOException {

		return ReadExcelData.readExcel(fileName);

	}

}
