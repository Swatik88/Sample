package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Base {

	public static WebDriver driver;
	@BeforeClass
	public void set()
	{
		//				System.setProperty("webdriver.gecko.driver", 
		//						"C:\\From_Old_C\\Eclipse Workspace\\lib\\geckodriver.exe");
		//				DesiredCapabilities dc = DesiredCapabilities.firefox();
		//				dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		//				FirefoxProfile profile = new FirefoxProfile();
		//				profile.setAcceptUntrustedCertificates(true);
		//				dc.setCapability(FirefoxDriver.PROFILE, profile);
		//				driver=new FirefoxDriver(dc);
		//				System.out.println(driver);

		System.setProperty("webdriver.chrome.driver",
				"C:\\From_Old_C\\Eclipse Workspace"
						+ "\\lib\\chromedriver_win32\\chromedriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		driver = new ChromeDriver(cap);
		driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
		
		System.out.println(driver);
		Dimension dimension = new Dimension(1600,1600);
		driver.manage().window().setSize(dimension);	
	}

	@Test(enabled=false,priority=0)
	public void sample1() throws Exception
	{
		Google google = new Google(driver);
		google.type();
	}

	@Test(priority=1)
	public void sample2() throws Exception
	{
		Collab collab = new Collab(driver);
		collab.type();
		//			collab.get_status();
		// 	Page scrolling using JSExecutor
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,600)", "");
		Thread.sleep(3000);
		jsExecutor.executeScript("window.scrollBy(0,-400)", "");
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("(//p)[3]"));
		jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(3000);
		jsExecutor.executeScript("window.scrollBy(0,-400)","");
		Thread.sleep(3000);
		jsExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	@Test(priority=2)
	public void sample3() throws Exception
	{
		List <WebElement> ls = driver.findElements(By.tagName("a"));
		System.out.println("Total tags " + ls.size());
		java.util.Set<String> wh = driver.getWindowHandles();
		Iterator<String> iterator = wh.iterator();
		System.out.println("No of Windows " + wh.size());
		while (iterator.hasNext())
		{
			String string = iterator.next();
			System.out.println(string);
		}
		java.util.Set<Cookie> cook = driver.manage().getCookies();
		System.out.println("No of Cookies" + cook.size());
		Iterator<Cookie>iterator2= cook.iterator();
		while(iterator2.hasNext())
		{
			Cookie cookie = iterator2.next();
			System.out.println(cookie.toString());
		}
	}

	@Test(priority=3, enabled=false)    // CREATING A NEW TAB & SWITCHING BETWEEN TABS
	public void sample4() throws Exception
	{
		if(driver.toString().contains("FirefoxDriver"))
		{
			driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL+"t");
			Thread.sleep(3000);
			driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL+"w");
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"t");
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"1");
		}

		if(driver.toString().contains("Chrome"))
		{
			String currentwindow = driver.getWindowHandle();
			Actions actions = new Actions(driver);
			WebElement element = driver.findElement(By.linkText("Publishing"));
			actions.keyDown(Keys.SHIFT).keyDown(Keys.CONTROL).click(element).build().perform();

			ArrayList<String>  windows = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("No of Windows " + windows.size());
			System.out.println(windows.get(0)+"    "+ windows.get(1));
			driver.switchTo().window(windows.get(1));
			Thread.sleep(3000);
			driver.switchTo().window(currentwindow);
		}
	}
	
	@Test(priority=4)
	public void sample5() throws Exception 
	{
		WebElement element = driver.findElement(By.xpath("(//div[@class='panel-body in collapse'])[6]"));
		String string = element.getText();
		String[] str = string.split("\\s+", 30);
		System.out.println("Length " + str.length);
		System.out.println("The text is -------\n "+Arrays.toString(str));
		
		int  number;
		Map<String, Integer> map = new HashMap<>();
		for (int i=0;i<str.length;i++)
		{
			if(map.containsKey(str[i]))
			{
				 number  = map.get(str[i]);
				 map.put(str[i], number+1);
			}
			else
			{
				map.put(str[i], 1);
			}
		}
		System.out.println("THE MAP ---- \n"+ map);
	}
	
	@Test
	public void sample6() throws Exception 
	{
		driver.get("http://cu269.cloud.maa.collab.net/");		
	}
	
	@AfterClass
	public void close()throws Exception
	{	

	}
}
