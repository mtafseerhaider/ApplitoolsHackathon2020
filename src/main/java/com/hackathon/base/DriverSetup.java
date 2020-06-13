package com.hackathon.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Tafseer Haider
 * 10/06/2020
 */
public interface DriverSetup
{
	WebDriver getWebDriverObject (DesiredCapabilities capabilities);
}
