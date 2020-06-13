package com.hackathon.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Tafseer Haider
 * 10/06/2020
 */
public enum DriverOptions implements DriverSetup
{
	FIREFOX
			{
				public WebDriver getWebDriverObject (DesiredCapabilities capabilities)
				{
					WebDriverManager.firefoxdriver().setup();
					FirefoxOptions options = new FirefoxOptions ();
					capabilities.setPlatform (Platform.ANY);
					options.merge (capabilities);

					return new FirefoxDriver (options);
				}
			},
	CHROME
			{
				public WebDriver getWebDriverObject (DesiredCapabilities capabilities)
				{
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions ();
					options.addArguments ("--incognito");
					options.addArguments ("--no-sandbox");
					options.addArguments ("--disable-gpu");
					options.addArguments ("--no-default-browser-check");
					options.setCapability ("applicationCacheEnabled", false);
					options.merge (capabilities);

					return new ChromeDriver (options);
				}
			},
	EDGE
			{
				public WebDriver getWebDriverObject (DesiredCapabilities capabilities)
				{
					WebDriverManager.edgedriver().setup();
					EdgeOptions options = new EdgeOptions ();
					options.setCapability("useAutomationExtension", false);
					options.merge (capabilities);

					return new EdgeDriver (options);
				}
			}
}
