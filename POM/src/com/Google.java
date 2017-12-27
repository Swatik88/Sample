package com;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Google {
	WebDriver driver1;
	
	By textfield = By.id("lst-ib");
	By search = By.id("_fZl");
	

	public Google(WebDriver driver)
	{
		this.driver1 = driver;
	}
	
	public void type() throws Exception
	{
		driver1.get("http://www.google.co.in");
		driver1.findElement(textfield).sendKeys("Collabnet");
		driver1.findElement(textfield).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
	}
}
