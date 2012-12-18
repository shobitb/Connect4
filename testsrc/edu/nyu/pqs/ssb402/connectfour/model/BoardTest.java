package edu.nyu.pqs.ssb402.connectfour.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	Board board, board2, board3;

	@Before
	public void before() {
		board = new Board.Builder().setCols(7).setRows(6).build();
		board2 = new Board.Builder().setCols(8).setRows(7).build();
		board3 = new Board.Builder().setCols(7).setRows(6).build();
	}

	@Test
	public void testEquals_twoSameBoards() {
		assertTrue(board.equals(board));
	}

	@Test
	public void testEquals_twoEqualBoards() {
		assertTrue(board.equals(board3));
	}

	@Test
	public void testEquals_twoUnEqualBoards() {
		assertFalse(board.equals(board2));
	}

	@Test
	public void testEquals_BoardWithNull() {
		assertFalse(board.equals(null));
	}

	@Test
	public void testHashCode_twoDifferentBoards() {
		int b1 = board.hashCode();
		int b2 = board2.hashCode();
		assertFalse(b1 == b2);
	}

	@Test
	public void testHashCode_twoDifferentObjects() {
		String b = "Board";
		assertFalse(board.equals(b));
	}

}
