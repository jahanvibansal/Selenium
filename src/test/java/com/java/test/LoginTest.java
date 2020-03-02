package com.java.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest {

	static WebDriver driver;

	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test

	public void loginSuccessTest() throws IOException {

		driver.get("http://the-internet.herokuapp.com/login");
		System.out.println("Page opened");
		WebElement elem1 = driver.findElement(By.id("username"));
		elem1.sendKeys("tomsmith");
		driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
		;
		screenshotTest();
		driver.findElement(By.className("radius")).click();
		SoftAssert softassert = new SoftAssert();
		softassert.assertTrue(
				driver.findElement(By.id("flash-messages")).getText().contains("You logged into a secure area!"));

	}

	@Test
	@Ignore
	public void loginFailureTest() {
		driver.get("http://the-internet.herokuapp.com/login");
		driver.findElements(By.tagName("input")).get(0).sendKeys("Payal");
		driver.findElements(By.tagName("input")).get(1).sendKeys("Payal");
		driver.findElements(By.tagName("button")).get(0).click();
		driver.findElement(By.xpath("//input"));
		assertTrue(driver.findElement(By.id("flash-messages")).getText().contains("Your username is invalid!"));
	}

	@Test
	@Ignore
	public void explicitWaitTest() {
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// explicit wait
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.tagName("submit")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("finish")));

		String text = driver.findElement(By.id("finish")).getText();
		assertEquals("Hello World!", text);
	}

	@Test
	@Ignore
	public void explicitWaitAlertTest() {
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// explicit wait
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		assertTrue(text.contains("I am a JS Alert"));
		alert.accept();
		String ptext = driver.findElement(By.id("result")).getText();
		assertEquals(ptext, "You successfuly clicked an alert");
	}

	@Test
	@Ignore
	public void newWindowTest() {
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// explicit wait
		driver.get("https://the-internet.herokuapp.com/windows");
		driver.findElement(By.linkText("Click Here")).click();
		String firstWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String WindowItr : windows) {
			if (!WindowItr.equals(firstWindow)) {
				driver.switchTo().window(WindowItr);
				break;
			}
		}

		assertEquals("https://the-internet.herokuapp.com/windows/new", driver.getCurrentUrl());
	}

	@Test
	@Ignore
	public void iframeTest() throws InterruptedException {
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// explicit wait
		driver.get("http://the-internet.herokuapp.com/iframe");

		WebElement iframe = driver.findElement(By.id("mce_0_ifr"));
		driver.switchTo().frame(iframe);
		WebElement p = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
		p.clear();
		// p.sendKeys("hello world");//need javascript executor to type text
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].innerHTML='hello world'", p);
		assertEquals(p.getText(), "hello world");
	}

	@Test @Ignore
	public void infiniteScrollTest() {
		driver.get("https://the-internet.herokuapp.com/infinite_scroll");
		List<WebElement> paragraphs = driver.findElements(By.className("jscroll-added"));
		System.out.println("No of paragraphs: "+ paragraphs.size());
		while(paragraphs.size()<10) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			 paragraphs = driver.findElements(By.className("jscroll-added"));
				System.out.println("No of paragraphs: "+ paragraphs.size());
		}
		assertEquals(paragraphs.size(),10);
	}
	

	public void screenshotTest() throws IOException {
		File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path= "test.png";
		FileUtils.copyFile(srcFile, new File(path));
	}

	@AfterClass
	public static void destroy() {
		// driver.manage().window().fullscreen();
		driver.quit();
	}
}
