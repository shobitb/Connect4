package edu.nyu.pqs.ssb402.connectfour.model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.ssb402.connectfour.model.Connect4;

public class Connect4Test {
	
	Connect4 connect4;
	boolean b;
	
	@Before
	public void before() {
		connect4 = Connect4.getInstance();
		b = connect4.startGame(6,7);
	}

	@Test
	public void testGetInstance() {
		assertFalse(connect4 == null);
	}
	
	@Test
	public void testStartGame() {
		assertTrue(b);
	}
	
	@Test
	public void testMakeMove() {
		int row = connect4.makeMove(4, Color.RED);
		assertEquals(row,5);
	}
	
	@Test
	public void testMakeMove_checkFullColumn() {
		for(int i = 0; i < 6; i++) {
			connect4.makeMove(0, Color.RED);
		}
		int row = connect4.makeMove(0, Color.RED);
		assertEquals(row,-1);
	}
	
	@Test
	public void testReset() {
		int row1 = connect4.makeMove(0, Color.RED);
		int row2 = connect4.makeMove(0, Color.YELLOW);
		int row3 = connect4.makeMove(2, Color.GREEN);
		int row4 = connect4.makeMove(3, Color.BLUE);
		assertEquals(row1,5);
		assertEquals(connect4.getColorAt(row1, 0),Color.RED);
		assertEquals(row2,4);
		assertEquals(connect4.getColorAt(row2, 0),Color.YELLOW);
		assertEquals(row3,5);
		assertEquals(connect4.getColorAt(row3, 2),Color.GREEN);
		assertEquals(row4,5);
		assertEquals(connect4.getColorAt(row4, 3),Color.BLUE);
		connect4.reset();
		for(int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				assertEquals(connect4.getColorAt(i,j),null);
			}
		}
	}
	
	@Test
	public void testCheckHorizontal_centerSameColor() {
		connect4.makeMove(2,Color.RED);
		connect4.makeMove(3,Color.RED);
		connect4.makeMove(4,Color.RED);
		connect4.makeMove(5,Color.RED);
		assertTrue(connect4.isThereAWinner(5, 2) == Color.RED);
		assertTrue(connect4.isThereAWinner(5, 3) == Color.RED);
		assertTrue(connect4.isThereAWinner(5, 4) == Color.RED);
		assertTrue(connect4.isThereAWinner(5, 5) == Color.RED);
	}
	
	@Test
	public void testCheckHorizontal_leftEndSameColor() {
		connect4.makeMove(0,Color.RED);
		connect4.makeMove(1,Color.RED);
		connect4.makeMove(2,Color.RED);
		connect4.makeMove(3,Color.RED);
		assertTrue(connect4.isThereAWinner(5, 0) == Color.RED);
		assertTrue(connect4.isThereAWinner(5, 1) == Color.RED);
		assertTrue(connect4.isThereAWinner(5, 2) == Color.RED);
		assertTrue(connect4.isThereAWinner(5, 3) == Color.RED);	
	}
	
	@Test
	public void testCheckHorizontal_rightEndSameColor() {
		connect4.makeMove(3,Color.RED);
		connect4.makeMove(4,Color.RED);
		connect4.makeMove(5,Color.RED);
		connect4.makeMove(6,Color.RED);
		assertTrue(connect4.isThereAWinner(5, 3) == Color.RED);
		assertTrue(connect4.isThereAWinner(5, 4) == Color.RED);
		assertTrue(connect4.isThereAWinner(5, 5) == Color.RED);
		assertTrue(connect4.isThereAWinner(5, 6) == Color.RED);	
	}
	
	@Test
	public void testCheckHorizontal_differentColor() {
		connect4.makeMove(0,Color.RED);
		connect4.makeMove(1,Color.RED);
		connect4.makeMove(2,Color.YELLOW);
		connect4.makeMove(3,Color.RED);
		assertFalse(connect4.isThereAWinner(5, 0) == Color.RED);
		assertFalse(connect4.isThereAWinner(5, 1) == Color.RED);
		assertFalse(connect4.isThereAWinner(5, 2) == Color.YELLOW);
		assertFalse(connect4.isThereAWinner(5, 3) == Color.RED);	
	}
	
	@Test
	public void testCheckVertical_centerColumnSameColor() {
		connect4.makeMove(3,Color.RED);
		connect4.makeMove(3,Color.RED);
		connect4.makeMove(3,Color.RED);
		connect4.makeMove(3,Color.RED);
		assertTrue(connect4.isThereAWinner(5, 3) == Color.RED);
		assertTrue(connect4.isThereAWinner(4, 3) == Color.RED);
		assertTrue(connect4.isThereAWinner(3, 3) == Color.RED);
		assertTrue(connect4.isThereAWinner(2, 3) == Color.RED);
	}
	
	@Test
	public void testCheckVertical_leftColumnSameColor() {
		connect4.makeMove(0,Color.RED);
		connect4.makeMove(0,Color.RED);
		connect4.makeMove(0,Color.RED);
		connect4.makeMove(0,Color.RED);
		assertTrue(connect4.isThereAWinner(5, 0) == Color.RED);
		assertTrue(connect4.isThereAWinner(4, 0) == Color.RED);
		assertTrue(connect4.isThereAWinner(3, 0) == Color.RED);
		assertTrue(connect4.isThereAWinner(2, 0) == Color.RED);
	}
	
	@Test
	public void testCheckVertical_rightColumnSameColor() {
		connect4.makeMove(5,Color.RED);
		connect4.makeMove(5,Color.RED);
		connect4.makeMove(5,Color.RED);
		connect4.makeMove(5,Color.RED);
		assertTrue(connect4.isThereAWinner(5, 5) == Color.RED);
		assertTrue(connect4.isThereAWinner(4, 5) == Color.RED);
		assertTrue(connect4.isThereAWinner(3, 5) == Color.RED);
		assertTrue(connect4.isThereAWinner(2, 5) == Color.RED);
	}
	
	@Test
	public void testCheckVertical_differentColor() {
		connect4.makeMove(3,Color.RED);
		connect4.makeMove(3,Color.RED);
		connect4.makeMove(3,Color.YELLOW);
		connect4.makeMove(3,Color.RED);
		assertFalse(connect4.isThereAWinner(5, 3) == Color.RED);
		assertFalse(connect4.isThereAWinner(4, 3) == Color.RED);
		assertFalse(connect4.isThereAWinner(3, 3) == Color.YELLOW);
		assertFalse(connect4.isThereAWinner(2, 3) == Color.RED);
	}
	
	@Test
	public void testCheckDiagonal_FromBottomLeftForwardDifferentColor() {
		connect4.setBoard(5, 0, Color.RED);
		connect4.setBoard(4, 1, Color.RED);
		connect4.setBoard(3, 2, Color.YELLOW);
		connect4.setBoard(2, 3, Color.RED);
		assertFalse(connect4.isThereAWinner(5, 0) == Color.RED);
		assertFalse(connect4.isThereAWinner(4, 1) == Color.RED);
		assertFalse(connect4.isThereAWinner(3, 2) == Color.YELLOW);
		assertFalse(connect4.isThereAWinner(2, 3) == Color.RED);
	}
	
	@Test
	public void testCheckDiagonal_FromCenterForwardDifferentColor() {
		connect4.setBoard(3, 3, Color.RED);
		connect4.setBoard(2, 4, Color.RED);
		connect4.setBoard(1, 5, Color.RED);
		connect4.setBoard(0, 6, Color.YELLOW);
		assertFalse(connect4.isThereAWinner(3, 3) == Color.RED);
		assertFalse(connect4.isThereAWinner(2, 4) == Color.RED);
		assertFalse(connect4.isThereAWinner(1, 5) == Color.RED);
		assertFalse(connect4.isThereAWinner(0, 6) == Color.YELLOW);
	}
	
	@Test
	public void testCheckDiagonal_FromCenterBackwardDifferentColor() {
		connect4.setBoard(3, 3, Color.RED);
		connect4.setBoard(2, 2, Color.YELLOW);
		connect4.setBoard(1, 1, Color.RED);
		connect4.setBoard(0, 0, Color.RED);
		assertFalse(connect4.isThereAWinner(3, 3) == Color.RED);
		assertFalse(connect4.isThereAWinner(2, 2) == Color.YELLOW);
		assertFalse(connect4.isThereAWinner(1, 1) == Color.RED);
		assertFalse(connect4.isThereAWinner(0, 0) == Color.RED);
	}
	
	@Test
	public void testCheckDiagonal_FromBottomRightBackwardDifferentColor() {
		connect4.setBoard(5, 6, Color.RED);
		connect4.setBoard(4, 5, Color.RED);
		connect4.setBoard(3, 4, Color.YELLOW);
		connect4.setBoard(2, 3, Color.RED);
		assertFalse(connect4.isThereAWinner(5, 6) == Color.RED);
		assertFalse(connect4.isThereAWinner(4, 5) == Color.RED);
		assertFalse(connect4.isThereAWinner(3, 4) == Color.YELLOW);
		assertFalse(connect4.isThereAWinner(2, 3) == Color.RED);
	}
	
	@Test
	public void testCheckDiagonal_FromBottomLeftForward() {
		connect4.setBoard(5, 0, Color.RED);
		connect4.setBoard(4, 1, Color.RED);
		connect4.setBoard(3, 2, Color.RED);
		connect4.setBoard(2, 3, Color.RED);
		assertTrue(connect4.isThereAWinner(5, 0) == Color.RED);
		assertTrue(connect4.isThereAWinner(4, 1) == Color.RED);
		assertTrue(connect4.isThereAWinner(3, 2) == Color.RED);
		assertTrue(connect4.isThereAWinner(2, 3) == Color.RED);
	}
	
	@Test
	public void testCheckDiagonal_FromCenterForward() {
		connect4.setBoard(3, 3, Color.RED);
		connect4.setBoard(2, 4, Color.RED);
		connect4.setBoard(1, 5, Color.RED);
		connect4.setBoard(0, 6, Color.RED);
		assertTrue(connect4.isThereAWinner(3, 3) == Color.RED);
		assertTrue(connect4.isThereAWinner(2, 4) == Color.RED);
		assertTrue(connect4.isThereAWinner(1, 5) == Color.RED);
		assertTrue(connect4.isThereAWinner(0, 6) == Color.RED);
	}
	
	@Test
	public void testCheckDiagonal_FromCenterBackward() {
		connect4.setBoard(3, 3, Color.RED);
		connect4.setBoard(2, 2, Color.RED);
		connect4.setBoard(1, 1, Color.RED);
		connect4.setBoard(0, 0, Color.RED);
		assertTrue(connect4.isThereAWinner(3, 3) == Color.RED);
		assertTrue(connect4.isThereAWinner(2, 2) == Color.RED);
		assertTrue(connect4.isThereAWinner(1, 1) == Color.RED);
		assertTrue(connect4.isThereAWinner(0, 0) == Color.RED);
	}
	
	@Test
	public void testCheckDiagonal_FromBottomRightBackward() {
		connect4.setBoard(5, 6, Color.RED);
		connect4.setBoard(4, 5, Color.RED);
		connect4.setBoard(3, 4, Color.RED);
		connect4.setBoard(2, 3, Color.RED);
		assertTrue(connect4.isThereAWinner(5, 6) == Color.RED);
		assertTrue(connect4.isThereAWinner(4, 5) == Color.RED);
		assertTrue(connect4.isThereAWinner(3, 4) == Color.RED);
		assertTrue(connect4.isThereAWinner(2, 3) == Color.RED);
	}
	
	@Test
	public void testIsBoardFull_fullBoard() {
		for (int i = 0; i < 6; i++) {
			for (int j =0 ; j < 7; j++) {
				connect4.setBoard(i, j, Color.RED);
			}
		}
		boolean bool = connect4.isBoardFull();
		assertTrue(bool);
		
	}
	
	@Test
	public void testIsBoard_notFullBoard() {
		for (int i = 0; i < 6; i++) {
			for (int j =0 ; j < 5; j++) {
				connect4.setBoard(i, j, Color.RED);
			}
		}
		boolean bool = connect4.isBoardFull();
		assertFalse(bool);
		
	}
	
	@Test
	public void testIsThereAWinner() {
		connect4.setBoard(5, 6, Color.RED);
		connect4.setBoard(4, 5, Color.RED);
		connect4.setBoard(3, 4, Color.RED);
		connect4.setBoard(2, 3, Color.RED);
		assertTrue(connect4.isThereAWinner(5, 6) == Color.RED);
		assertTrue(connect4.isThereAWinner(4, 5) == Color.RED);
		assertTrue(connect4.isThereAWinner(3, 4) == Color.RED);
		assertTrue(connect4.isThereAWinner(2, 3) == Color.RED);
		connect4.setBoard(5, 6, Color.RED);
		connect4.setBoard(4, 5, Color.YELLOW);
		connect4.setBoard(3, 4, Color.RED);
		connect4.setBoard(2, 3, Color.RED);
		assertTrue(connect4.isThereAWinner(5, 6) == null);
		assertTrue(connect4.isThereAWinner(4, 5) == null);
		assertTrue(connect4.isThereAWinner(3, 4) == null);
		assertTrue(connect4.isThereAWinner(2, 3) == null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetRowForColumn_negativeCol() {
		connect4.startGame(6, 7);
		connect4.getRowForColumn(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStartGame_negativeCol() {
		connect4.startGame(6, -7);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStartGame_negativeRow() {
		connect4.startGame(-6, 7);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMakeMove_negativeCol() {
		connect4.startGame(6, 7);
		connect4.makeMove(-8, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMakeMove_outOfBoundsCols() {
		connect4.startGame(6, 7);
		connect4.makeMove(10, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMakeMove_colorNull() {
		connect4.startGame(6, 7);
		connect4.makeMove(3, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetBoard_colorNull() {
		connect4.startGame(6, 7);
		connect4.makeMove(3, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetBoard_negativeRow() {
		connect4.startGame(6, 7);
		connect4.setBoard(-4, 3, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetBoard_negativeCol() {
		connect4.startGame(6, 7);
		connect4.setBoard(4, -3, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetBoard_rowOutOfBound() {
		connect4.startGame(6, 7);
		connect4.setBoard(40, 3, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetBoard_colOutOfBound() {
		connect4.startGame(6, 7);
		connect4.setBoard(4, 30, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWinner_colOutOfBound() {
		connect4.startGame(6, 7);
		connect4.isThereAWinner(4, 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWinner_rowOutOfBound() {
		connect4.startGame(6, 7);
		connect4.isThereAWinner(9, 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWinner_colNegative() {
		connect4.startGame(6, 7);
		connect4.isThereAWinner(4, -9);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWinner_rowNegative() {
		connect4.startGame(6, 7);
		connect4.isThereAWinner(-4, 1);
	}

}
