package edu.nyu.pqs.ssb402.connectfour.player;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.ssb402.connectfour.model.Connect4;
import edu.nyu.pqs.ssb402.connectfour.player.Player;
import edu.nyu.pqs.ssb402.connectfour.player.PlayerFactory;
import edu.nyu.pqs.ssb402.connectfour.player.PlayerType;

public class HumanPlayerTest {
	
	Connect4 connect4;
	Player player = PlayerFactory.createPlayer(PlayerType.HUMAN, Color.RED);
	
	@Before
	public void before() {
		connect4 = Connect4.getInstance();
		connect4.startGame(6, 7);
	}

	@Test
	public void testPlay() {
		player.setColumnForMove(4);
		int row = player.play(connect4);
		assertEquals(row, 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPlay_nullModel() {
		player.setColumnForMove(4);
		int row = player.play(null);
		assertEquals(row, 5);
	}
	
	@Test
	public void testGetType() {
		PlayerType type = player.getType();
		assertEquals(PlayerType.HUMAN, type);
	}

	@Test
	public void testNextColumnForMove() {
		player.setColumnForMove(3);
		assertEquals(player.getColumnForMove(),3);
	}
	
	@Test
	public void testEquals_sameObject() {
		boolean b = player.equals(player);
		assertTrue(b);
	}
	
	@Test
	public void testEquals_nullObject() {
		boolean b = player.equals(null);
		assertFalse(b);
	}
	
	@Test
	public void testEquals_instanceOfHumanPlayer() {
		Player player2 = PlayerFactory.createPlayer(PlayerType.HUMAN, Color.RED);
		boolean b = player.equals(player2);
		assertTrue(b);
	}
	
	@Test
	public void testEquals_instanceOfSomethingElse() {
		Color c = Color.RED;
		boolean b = player.equals(c);
		assertFalse(b);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEquals_nullColorForOnePlayer() {
		Player player2 = PlayerFactory.createPlayer(PlayerType.HUMAN, null);
	}
	
	@Test
	public void testEquals_differentColsForMove() {
		Player player2 = PlayerFactory.createPlayer(PlayerType.HUMAN, Color.RED);
		player2.setColumnForMove(1);
		player.setColumnForMove(2);
		boolean b = player2.equals(player);
		assertFalse(b);
	}
	
	@Test
	public void testEquals_sameObjectsInternally() {
		Player player2 = PlayerFactory.createPlayer(PlayerType.HUMAN, Color.RED);
		player2.setColumnForMove(1);
		player.setColumnForMove(1);
		boolean b = player2.equals(player);
		assertTrue(b);
	}
	
	@Test
	public void testHashCode_equalHashCode() {
		int hash1 = player.hashCode();
		Player player2 = PlayerFactory.createPlayer(PlayerType.HUMAN, Color.RED);
		int hash2 = player2.hashCode();
		assertEquals(hash1,hash2);
	}
	
	@Test
	public void testHashCode_unequalHashCode() {
		int hash1 = player.hashCode();
		Player player2 = PlayerFactory.createPlayer(PlayerType.HUMAN, Color.BLUE);
		int hash2 = player2.hashCode();
		assertFalse(hash1 == hash2);
	}
	
	@Test
	public void testToString() {
		String s = player.toString();
		assertEquals(s,"HumanPlayer [c=java.awt.Color[r=255,g=0,b=0], nextMoveCol=0]");
	}

}
