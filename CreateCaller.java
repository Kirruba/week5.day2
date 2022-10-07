package week5.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.sukgu.Shadow;

public class CreateCaller extends BaseClass {

	@BeforeTest
	public void setExcelFileName() {
		fileName = "Create_Caller";
	}

	@Test(dataProvider = "fetchData")
	public void Create_Caller(String FirstName, String LastName, String Email, String BizPhone, String MobPhone,
			String Value) throws InterruptedException {

		Shadow sh = new Shadow(driver);
		Thread.sleep(30000);

		// Click All and Enter Callers in filter navigator and press enter
		WebElement All = sh.findElementByXPath("//div[text()='All']");
		All.click();
		Thread.sleep(5000);

		WebElement filter = sh.findElementByXPath("//input[@id='filter']");
		filter.sendKeys("Callers");
		Thread.sleep(5000);

		WebElement caller = sh.findElementByXPath("//mark[text()='Callers']");
		caller.click();
		Thread.sleep(15000);

		WebElement frame = sh.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);
		Thread.sleep(2000);

		// Create new Caller by filling all the fields and click 'Submit'
		WebElement newButton = driver.findElement(By.xpath("//button[@id='sysverb_new']"));
		newButton.click();

		driver.findElement(By.xpath("//input[@id='sys_user.first_name']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@id='sys_user.last_name']")).sendKeys(LastName);
		Thread.sleep(5000);

		WebElement title = driver.findElement(By.xpath("//span[@class='icon icon-lightbulb']"));
		title.click();
		Thread.sleep(5000);

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));

		driver.findElement(By.xpath("//a[text()='System Administrator']")).click();
		Thread.sleep(5000);

		driver.switchTo().window(windows.get(0));
		driver.switchTo().frame(frame);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='sys_user.email']")).sendKeys(Email);
		driver.findElement(By.xpath("//input[@id='sys_user.phone']")).sendKeys(BizPhone);
		driver.findElement(By.xpath("//input[@id='sys_user.mobile_phone']")).sendKeys(MobPhone);
		Thread.sleep(5000);

		driver.findElement(By.xpath("//button[@id='sysverb_insert_bottom']")).click();
		Thread.sleep(5000);

		WebElement text = driver.findElement(By.xpath("//div[@class='outputmsg_text']"));
		String successMsg = text.getText();
		System.out.println(successMsg);

		// Search and verify the newly created Caller
		WebElement sel = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		sel.click();
		Select dd = new Select(sel);
		dd.selectByValue(Value);
		Thread.sleep(2000);

		WebElement search = driver.findElement(By.xpath("//input[@class='form-control']"));
		search.sendKeys(FirstName);
		search.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		String firstName = driver.findElement(By.xpath("//table[@id='sys_user_table']/tbody/tr/td[4]")).getText();
		// System.out.println(firstName);

		String lastName = driver.findElement(By.xpath("//table[@id='sys_user_table']/tbody/tr/td[3]")).getText();
		// System.out.println(lastName);

		if (firstName.equals(FirstName) && lastName.equals(LastName)) {
			System.out.println("Newly created Caller is verified successfully");
		} else {
			System.out.println("New Caller is not verified");
		}

	}

}
