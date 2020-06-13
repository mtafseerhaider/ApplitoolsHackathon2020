package com.hackathon.base;

import org.openqa.selenium.Dimension;

/**
 * @author Tafseer Haider
 * 10/06/2020
 */
public enum DeviceTypes implements DeviceSetup
{
	LAPTOP
			{
				public Dimension getDisplayResolution ()
				{
					return new Dimension (1200, 700);
				}
			},
	TABLET
			{
				public Dimension getDisplayResolution ()
				{
					return new Dimension (800, 700);
				}
			},
	MOBILE
			{
				public Dimension getDisplayResolution ()
				{
					return new Dimension (500, 700);
				}
			}
}
