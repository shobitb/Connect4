package edu.nyu.pqs.ssb402.connectfour.player;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

import edu.nyu.pqs.ssb402.connectfour.player.ComputerPlayer;
import edu.nyu.pqs.ssb402.connectfour.player.HumanPlayer;
import edu.nyu.pqs.ssb402.connectfour.player.Player;
import edu.nyu.pqs.ssb402.connectfour.player.PlayerFactory;
import edu.nyu.pqs.ssb402.connectfour.player.PlayerType;

public class PlayerFactoryTest {

	@Test
	public void testCreatePlayer_Human() {
		Player player = PlayerFactory.createPlayer(PlayerType.HUMAN, Color.RED);
		assertTrue(player instanceof HumanPlayer);
	}
	
	@Test
	public void testCreatePlayer_Computer() {
		Player player = PlayerFactory.createPlayer(PlayerType.COMPUTER, Color.RED);
		assertTrue(player instanceof ComputerPlayer);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreatePlayer_nullPlayer() {
		Player player = PlayerFactory.createPlayer(null, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreatePlayer_nullColor() {
		Player player = PlayerFactory.createPlayer(PlayerType.HUMAN, null);
	}

}
