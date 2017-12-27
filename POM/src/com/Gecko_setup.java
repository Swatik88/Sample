package com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Gecko_setup {

	public static void main(String[] args) throws Exception
		
	{
		Gecko_setup gp = new Gecko_setup();
		gp.desired_capabilities();
		gp.setting_path();
	}
	
	private void desired_capabilities() throws Exception
	{
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		WebDriver driver=new FirefoxDriver(capabilities);
		driver.get("http://google.com");
		Thread.sleep(3000);
	}
	private void setting_path() throws Exception
	{
		java.io.File file = new java.io.File("C:/From_Old_C/Eclipse Workspace"
				+ "/lib/geckodriver-v0.19.1-win64/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
		WebDriver driver=new FirefoxDriver();
		driver.get("http://google.com");
	}

}
