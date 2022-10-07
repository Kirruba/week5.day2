package week5.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.sukgu.Shadow;

public class CreateProposal extends BaseClass {

	@BeforeTest
	public void setExcelFileName() {
		fileName = "Create_Proposal";
	}

	@Test(dataProvider = "fetchData")
	public void Create_Proposal(String Template) throws InterruptedException {

		Shadow sh = new Shadow(driver);
		Thread.sleep(30000);

		// Click All and Enter Proposal in filter navigator and press enter
		WebElement All = sh.findElementByXPath("//div[text()='All']");
		All.click();
		Thread.sleep(5000);

		WebElement filter = sh.findElementByXPath("//input[@id='filter']");
		filter.sendKeys("Proposal");
		Thread.sleep(5000);

		WebElement proposal = sh.findElementByXPath("//a[@aria-label='My Proposals']");
		proposal.click();
		Thread.sleep(15000);

		WebElement frame = sh.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);
		Thread.sleep(2000);

		// Click new and fill mandatory fields and click 'Submit' Button.
		WebElement newButton = driver.findElement(By.xpath("//button[@id='sysverb_new']"));
		newButton.click();

		String number = driver.findElement(By.xpath("//input[@id='std_change_proposal.number']")).getAttribute("value");
		System.out.println(number);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='std_change_proposal.short_description']")).sendKeys(Template);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@id='sysverb_insert_bottom']")).click();
		Thread.sleep(5000);

		// Verify the successful creation of new Proposal
		WebElement sel = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		sel.click();
		Select dd = new Select(sel);
		dd.selectByValue("number");
		Thread.sleep(2000);

		WebElement search = driver.findElement(By.xpath("//input[@class='form-control']"));
		search.sendKeys(number);
		search.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		String template = driver.findElement(By.xpath("//table[@id='std_change_proposal_table']/tbody/tr/td[4]"))
				.getText();
		if (template.contains(Template)) {
			System.out.println("New Proposal is created successfully");
		} else {
			System.out.println("New Proposal is not created");
		}
	}

}
