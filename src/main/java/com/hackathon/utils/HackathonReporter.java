package com.hackathon.utils;

import com.hackathon.base.DeviceTypes;
import com.hackathon.base.DriverOptions;
import java.io.BufferedWriter;
import java.io.FileWriter;
import static com.hackathon.base.DeviceTypes.LAPTOP;
import static com.hackathon.base.DriverOptions.CHROME;

/**
 * @author Tafseer Haider
 * 12/06/2020
 */
public class HackathonReporter
{
	private        DeviceTypes   selectedDeviceType;
	private DriverOptions selectedDriverOption;

	public HackathonReporter ()
	{
		DeviceTypes deviceType = LAPTOP;
		DriverOptions driverOption = CHROME;
		String device = System.getProperty ("deviceType", deviceType.toString ().toUpperCase ());
		String browser = System.getProperty ("browserType", driverOption.toString ().toUpperCase ());
		try
		{
			deviceType = DeviceTypes.valueOf (device.toUpperCase ());
		}
		catch (IllegalArgumentException ignored)
		{
			System.err.println ("Unknown device specified, defaulting to '" + deviceType + "'...");
		}
		catch (NullPointerException ignored)
		{
			System.err.println ("No device specified, defaulting to '" + deviceType + "'...");
		}
		try
		{
			driverOption = DriverOptions.valueOf (browser.toUpperCase ());
		}
		catch (IllegalArgumentException ignored)
		{
			System.err.println ("Unknown driver specified, defaulting to '" + driverOption + "'...");
		}
		catch (NullPointerException ignored)
		{
			System.err.println ("No driver specified, defaulting to '" + driverOption + "'...");
		}
		selectedDeviceType = deviceType;
		selectedDriverOption = driverOption;
	}

	/**
	 * A Helper to print the test result in the following format:
	 * Task: <Task Number>, Test Name: <Test Name>, DOM Id:: <id>, Browser: <Browser>, Viewport: <Width x Height>, Device<Device type>, Status: <Pass | Fail>
	 *
	 * Example: Task: 1, Test Name: Search field is displayed, DOM Id: DIV__customsear__41, Browser: Chrome, Viewport: 1200 x 700, Device: Laptop, Status: Pass
	 *
	 * @param task                    int - 1, 2 or 3
	 * @param testName               string - Something meaningful. E.g. 1.1 Search field is displayed
	 * @param domId                   string - DOM ID of the element
	 * @param comparisonResult        boolean - The result of comparing the "Expected" value and the "Actual" value.
	 * @return			  boolean - returns the same comparison result back so that it can be used for further Assertions in the test code.
	 */

	public boolean reportResult (int task, String testName, String appVersion, String domId, boolean comparisonResult)
	{
		try (var writer = new BufferedWriter (new FileWriter ("Traditional-" + appVersion + "-TestResults.txt", true)))
		{
			writer.write (
					   "Task: " + task + ", " +
						   "Test Name: " + testName + ", " +
						   "DOM Id: " + domId + ", " +
						   "Browser: " + selectedDriverOption.name ().toUpperCase () + ", " +
						   "Viewport: " +  selectedDeviceType.getDisplayResolution ().getWidth () + "X" + selectedDeviceType.getDisplayResolution ().getHeight () + ", " +
						   "Device: " + selectedDeviceType.name ().toUpperCase () + ", " +
						   "Status: " + (comparisonResult ? "Pass" : "Fail"));
			writer.newLine ();
		}
		catch (Exception e)
		{
			System.out.println ("Error writing to report file");
			e.printStackTrace ();
		}

		return comparisonResult;
	}

}
