package com;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Collab {
	WebDriver driver2;

	By username = By.id("username");
	By password = By.id("pwd");

	public Collab(WebDriver driver)
	{
		this.driver2=driver;
	}

	public void type() throws Exception
	{
		driver2.get("https://cu257.cloud.maa.collab.net/");
		driver2.findElement(username).sendKeys("admin");
		driver2.findElement(password).sendKeys("admin");
		driver2.findElement(password).sendKeys(Keys.ENTER);
		
		// ADDING MULTIPLE EXPECTED CONDITIONS FOR WAIT
		WebDriverWait wait = new WebDriverWait(driver2, 30);
		wait.until( ExpectedConditions.or
				(ExpectedConditions.titleContains("Login"), 
				  ExpectedConditions.titleContains("Welcome")));
		
		
		if(driver2.getPageSource().contains("Invalid Login"))
		{
			driver2.findElement(username).sendKeys("admin");
			driver2.findElement(password).sendKeys("Collabnet1!");
			driver2.findElement(password).sendKeys(Keys.ENTER);
		}
	}

	public void get_status() throws Exception 
	{
		List<WebElement> elements = new ArrayList<WebElement>();
		elements = driver2.findElements(By.tagName("a"));
		elements.addAll(driver2.findElements(By.tagName("img")));

		List<WebElement> elements_update = new ArrayList<>();
		for(WebElement ele : elements)
		{
			if(ele.getAttribute("href")!=null)
			{
				elements_update.add(ele);
			}
		} 
		System.out.println("NO OF LINKS "+elements_update.size());
		for(WebElement ele1 : elements_update)
		{
			String url = ele1.getAttribute("href");
			if (StringUtils.isNotEmpty(url)) {
				URL linkURL = null;
				try {
					linkURL = new URL(url);
					System.out.println("URL :"+url+" Status :"
							+getstatus(linkURL));				
				} catch (MalformedURLException e) {
					System.err.println("Invalid URL : "+ url);				
					e.printStackTrace();
				}
			} else {
				System.err.println("Element didn't have href : "+ ele1);				
			}
		}
	}

	public  int getstatus(URL U1) throws IOException {
		int status;
		HttpURLConnection connection = (HttpURLConnection)U1.openConnection();
		connection.connect();
		status=connection.getResponseCode();
		System.out.println(connection.getResponseMessage());
		return status;
	}

}
