package com.atmecs.testBrowserStack;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TestBrowserStackOnAndroid {
	
	AndroidDriver<MobileElement> driver;

	public static final String USERNAME = "username";
	public static final String ACCESS_KEY = "#######";
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws MalformedURLException {

		URL url = new URL(URL);

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("device", "Samsung Galaxy S8");
		caps.setCapability("browserName", "Samsung Galaxy S8");
	    caps.setCapability("os_version", "7.0");
	    caps.setCapability("name", "Bstack-[Android] Sample Test");
		caps.setCapability("realMobile", "true");

		driver = new AndroidDriver<MobileElement>(url, caps);

		System.out.println("Capability:" + caps.toString());

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testBrowserStack() throws InterruptedException {

		String navigateUrl = "http://www.google.com";
		driver.get(navigateUrl);
		System.out.println("Navigate to URL::==" + navigateUrl);
		String keyword = "BrowserStack";
		System.out.println("Searching for keywords ::==" + keyword);
		MobileElement seachBox = driver.findElement(By.name("q"));
		seachBox.sendKeys(keyword);
		Thread.sleep(5000);
	}

	@AfterTest(alwaysRun = true)
	public void teardown() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}
}
