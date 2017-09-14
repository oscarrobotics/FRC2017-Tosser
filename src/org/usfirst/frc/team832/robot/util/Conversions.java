package org.usfirst.frc.team832.robot.util;

public class Conversions {
	public static double inchesToTicks(double inches)
	{
		return(inches * 128 / ( 3 * Math.PI));
	}
	
	public static double ticksToInches(double ticks)
	{
		return(ticks * (3 * Math.PI) / 128);
	}
}
