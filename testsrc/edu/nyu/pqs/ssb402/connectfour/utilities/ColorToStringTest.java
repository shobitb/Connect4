package edu.nyu.pqs.ssb402.connectfour.utilities;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.nyu.pqs.ssb402.connectfour.utilities.ColorToString;

public class ColorToStringTest {

	@Test
	public void testGetString_validColorRed() {
		Color c = Color.RED;
		String color = ColorToString.getString(c);
		assertEquals(color,"Red");
	}
	
	@Test
	public void testGetString_validColorYellow() {
		Color c = Color.YELLOW;
		String color = ColorToString.getString(c);
		assertEquals(color,"Yellow");
	}
	
	@Test
	public void testGetString_null() {
		Color c = null;
		String color = ColorToString.getString(c);
		assertEquals(color,null);
	}
}
