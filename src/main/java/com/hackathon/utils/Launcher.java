package com.hackathon.utils;

/**
 * @author Tafseer Haider
 * 12/06/2020
 */
public class Launcher extends CommonActions
{
	public void launchAppliFashionApp (String strAppVersion)
	{
		String strAppUrl;
		if (strAppVersion.equalsIgnoreCase ("V1"))
		{
			strAppUrl = "https://demo.applitools.com/gridHackathonV1.html";
			System.out.println ("This automated test script will be executed on AppliFashion App V1 with URL: " + strAppUrl);
			loadPage (strAppUrl, Timer.getWaitTime ());
		}
		else if (strAppVersion.equalsIgnoreCase ("V2"))
		{
			strAppUrl = "https://demo.applitools.com/gridHackathonV2.html";
			System.out.println ("This automated test script will be executed on AppliFashion App V2 with URL: " + strAppUrl);
			loadPage (strAppUrl, Timer.getWaitTime ());
		}
	}

}
