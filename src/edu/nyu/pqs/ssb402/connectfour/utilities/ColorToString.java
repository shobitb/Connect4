package edu.nyu.pqs.ssb402.connectfour.utilities;

import java.awt.Color;

/**
 * This is a helper class that returns the String when a Color is passed to it.
 * It consists of only a single static method getString(Color)
 * 
 * @author shobit
 * 
 */
public class ColorToString {

	/**
	 * This method returns the color in String when passed a Color c
	 * 
	 * @param c
	 *            - a Color
	 * @return string that represents that color
	 */
	public static String getString(Color c) {
		if (c == Color.RED) {
			return "Red";
		} else if (c == Color.YELLOW) {
			return "Yellow";
		}
		return null;
	}
}
