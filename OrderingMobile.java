package week5.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.sukgu.Shadow;

public class OrderingMobile extends BaseClass {

	@BeforeTest
	public void setExcelFileName() {
		fileName = "Service_Now";
	}

	@Test(dataProvider = "fetchData")
	public void Ordering_Mobile(String Color, String Storage) throws InterruptedException {

		Shadow sh = new Shadow(driver);
		Thread.sleep(30000);

		// Click All
		WebElement All = sh.findElementByXPath("//div[text()='All']");
		All.click();
		Thread.sleep(15000);

		// Select Service catalog
		WebElement serviceCatalog = sh.findElementByXPath("//span[text()='Service Catalog']");
		serviceCatalog.click();
		Thread.sleep(15000);

		WebElement catalog = sh.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(catalog);
		Thread.sleep(2000);

		// Click on Mobiles
		sh.findElementByXPath("//h2[contains(text(),'Mobiles')]").click();

		// Select Apple iphone6s
		driver.findElement(By.xpath("(//h2)[5]//strong[text()='Apple iPhone 6s']")).click();

		// Update color field to rose gold and storage field to 128GB
		WebElement color = driver.findElement(By.xpath("//select[@id='IO:60b15e33d7033100a9ad1e173e24d4a3']"));
		Select dd = new Select(color);
		dd.selectByValue(Color);

		WebElement storage = driver.findElement(By.xpath("//select[@id='IO:e0b15e33d7033100a9ad1e173e24d4a1']"));
		Select ds = new Select(storage);
		ds.selectByValue(Storage);

		// Select Order now option
		driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();
		String requestNo = driver.findElement(By.xpath("//a[@id='requesturl']/b")).getText();
		System.out.println(requestNo);
		String orderStatus = driver.findElement(By.xpath("//div[@class='notification notification-success']/span"))
				.getText();

		// Verify order is placed and copy the request number
		if (orderStatus.contains("submitted")) {
			System.out.println("Order placed successfully");

		} else {
			System.out.println("Order is not placed successfully");
		}

	}

}
