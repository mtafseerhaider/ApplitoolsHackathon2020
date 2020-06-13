package com.hackathon.constants;

/**
 * @author Tafseer Haider
 * 10/06/2020
 */
public class GLOBAL
{
	public static final String  APP_VERSION             = System.getProperty ("appVersion", "V2");
	public static final Boolean APPLITOOLS_ENABLE_EYES = Boolean.getBoolean ("enableEyes");
	public static final String  CONFIG_FILE             = System.getProperty ("user.dir") + "/src/resources/configs/secrets.properties";
}
