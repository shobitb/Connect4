package edu.nyu.pqs.ssb402.connectfour.player;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.ssb402.connectfour.model.Connect4;

public class ComputerPlayerTest {
	
	Connect4 connect4;
	Player player = PlayerFactory.createPlayer(PlayerType.COMPUTER, Color.YELLOW);

	@Before
	public void setUp() {
		connect4 = Connect4.getInstance();
		connect4.startGame(6, 7);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPlay_null() {
		connect4.startGame(6, 7);
		player.play(null);
	}
	@Test
	public void testPlay_basicMove() {
		connect4.startGame(6, 7);
		int row = ((ComputerPlayer) player).playForTest(connect4);
		assertEquals(row, 5);	
	}
	
	@Test
	public void testPlay_playToWinHorizontal() {
		connect4.startGame(6, 7);
		connect4.setBoard(5, 0, Color.YELLOW);
		connect4.setBoard(5, 1, Color.YELLOW);
		connect4.setBoard(5, 2, Color.YELLOW);
		int row = player.play(connect4);
		int col = player.getColumnForMove();
		assertEquals(row, 5);
		assertEquals(col,3);
	}
	
	@Test
	public void testPlay_playToWinHorizontalSomewhereInTheMiddle() {
		connect4.startGame(6, 7);
		for(int i = 5; i >= 4; i--) {
			for(int j = 0; j < 7; j++) {
				connect4.setBoard(i, j, Color.RED);
			}
		}
		
		for (int i = 0; i < 4; i++) {
			connect4.setBoard(i, 0, Color.RED);
		}
		
		for (int j = 6; j >=4 ; j--) {
			connect4.setBoard(3, j, Color.YELLOW);
		}
		
		int row = player.play(connect4);
		int col = player.getColumnForMove();
		assertEquals(row, 3);
		assertEquals(col, 3);
	}
	
	@Test
	public void testGetType() {
		PlayerType type = player.getType();
		assertEquals(PlayerType.COMPUTER, type);
	}
	
	@Test
	public void testNextColumnForMove() {
		player.setColumnForMove(3);
		assertEquals(player.getColumnForMove(),3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNextColumnForMove_illegalArgument() {
		player.setColumnForMove(-3);
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
	public void testEquals_instanceOfComputerPlayer() {
		Player player2 = PlayerFactory.createPlayer(PlayerType.COMPUTER, Color.YELLOW);
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
		Player player2 = PlayerFactory.createPlayer(PlayerType.COMPUTER, null);
	}
	
	@Test
	public void testEquals_differentColsForMove() {
		Player player2 = PlayerFactory.createPlayer(PlayerType.COMPUTER, Color.YELLOW);
		player2.setColumnForMove(1);
		player.setColumnForMove(2);
		boolean b = player2.equals(player);
		assertFalse(b);
	}
	
	@Test
	public void testEquals_sameObjectsInternally() {
		Player player2 = PlayerFactory.createPlayer(PlayerType.COMPUTER, Color.YELLOW);
		player2.setColumnForMove(1);
		player.setColumnForMove(1);
		boolean b = player2.equals(player);
		assertTrue(b);
	}
	
	@Test
	public void testHashCode_equalHashCode() {
		int hash1 = player.hashCode();
		Player player2 = PlayerFactory.createPlayer(PlayerType.COMPUTER, Color.YELLOW);
		int hash2 = player2.hashCode();
		assertEquals(hash1,hash2);
	}
	
	@Test
	public void testHashCode_unequalHashCode() {
		int hash1 = player.hashCode();
		Player player2 = PlayerFactory.createPlayer(PlayerType.COMPUTER, Color.BLUE);
		int hash2 = player2.hashCode();
		assertFalse(hash1 == hash2);
	}
	
	@Test
	public void testColor() {
		Color c = player.getColor();
		assertEquals(c, Color.YELLOW);
	}
	
	@Test
	public void testToString() {
		String s = player.toString();
		assertEquals(s,"ComputerPlayer [c=java.awt.Color[r=255,g=255,b=0], nextMoveCol=0]");
	}

}
