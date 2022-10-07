package week5.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.sukgu.Shadow;

public class FillMandatoryFields extends BaseClass {

	@BeforeTest
	public void setExcelFileName() {
		fileName = "Fill_Mandatory_Fields";
	}

	@Test(dataProvider = "fetchData")

	public void Fill_Mandatory_Fields(String shortDescription) throws InterruptedException {

		Shadow sh = new Shadow(driver);
		Thread.sleep(30000);

		// Click All and Enter Knowledge in filter navigator and press enter
		WebElement All = sh.findElementByXPath("//div[text()='All']");
		All.click();
		Thread.sleep(5000);

		WebElement filter = sh.findElementByXPath("//input[@id='filter']");
		filter.sendKeys("Knowledge");
		Thread.sleep(5000);

		WebElement catalog = sh.findElementByXPath("//mark[text()='Knowledge']");
		catalog.click();
		Thread.sleep(15000);

		WebElement frame = sh.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);
		Thread.sleep(2000);

		// Create new Article
		WebElement article = driver.findElement(By.xpath("//span[text()='Create an Article']"));
		article.click();

		// Select knowledge base as IT and category as IT- java and Click Ok
		Thread.sleep(5000);
		WebElement knowledgeBase = driver
				.findElement(By.xpath("//button[@id='lookup.kb_knowledge.kb_knowledge_base']"));
		knowledgeBase.click();
		Thread.sleep(5000);

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));

		driver.findElement(By.xpath("//a[text()='IT']")).click();
		Thread.sleep(5000);

		driver.switchTo().window(windows.get(0));
		driver.switchTo().frame(frame);
		Thread.sleep(2000);

		WebElement category = driver.findElement(By.xpath("//button[@id='lookup.kb_knowledge.kb_category']"));
		category.click();
		Thread.sleep(10000);

		WebElement cell1 = driver
				.findElement(By.xpath("//table[@id='window.kb_categories_dialog']//span[text()='IT']"));
		cell1.click();
		Thread.sleep(10000);

		WebElement cell2 = driver.findElement(By.xpath("//span[text()='Java']"));
		cell2.click();
		Thread.sleep(10000);

		driver.findElement(By.xpath("//button[@id='ok_button']")).click();
		Thread.sleep(5000);

		// Update the other mandatory fields
		WebElement description = driver.findElement(By.xpath("//input[@id='kb_knowledge.short_description']"));
		description.sendKeys(shortDescription);
		Thread.sleep(10000);

		// Select the submit option
		driver.findElement(By.xpath("//button[@id='sysverb_insert_bottom']")).click();
		Thread.sleep(5000);
	}

}
