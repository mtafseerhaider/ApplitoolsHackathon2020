package com.hackathon.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Tafseer Haider
 * 12/06/2020
 */
public class ConfigsReader
{
	public static Properties properties;

	public static Properties readProperties (String filePath)
	{
		try
		{
			FileInputStream fileInputStream = new FileInputStream (filePath);
			properties = new Properties ();
			properties.load (fileInputStream);
		}
		catch (IOException e)
		{
			e.printStackTrace ();
		}

		return properties;
	}

	public static String getProperty (String key)
	{
		return properties.getProperty (key);
	}
}
