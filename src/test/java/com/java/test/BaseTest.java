/*
 * package com.java.test;
 * 
 * import org.openqa.selenium.WebDriver; import
 * org.testng.annotations.AfterMethod; import
 * org.testng.annotations.BeforeMethod; import org.testng.annotations.Optional;
 * import org.testng.annotations.Parameters;
 * 
 * public class BaseTest {
 * 
 * WebDriver driver;
 * 
 * @BeforeMethod
 * 
 * @Parameters("browser") protected void setup(@Optional("chrome") String
 * browser) { BrowserDriverFactory factory = new BrowserDriverFactory(browser);
 * driver = factory.createDriver(); try { Thread.sleep(3000); } catch
 * (InterruptedException e) { e.printStackTrace(); }
 * driver.manage().window().maximize(); }
 * 
 * @AfterMethod public void destroy() { driver.quit(); } }
 */