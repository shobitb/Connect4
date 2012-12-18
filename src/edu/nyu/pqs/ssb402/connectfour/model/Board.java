package edu.nyu.pqs.ssb402.connectfour.model;

import java.awt.Color;
import java.util.Arrays;

/**
 * The Board class is a data structure used by the model to keep track of the
 * progress of the game. It is an object representation of the grid in the game.
 * It is used only by the model class and is not available outside this package.
 * 
 * This class demonstrates the use of *********** BUILDER PATTERN. *************
 * The Board is built using
 * the Builder which is the inner class of this class. Rows and columns are the
 * optional arguments with 6 and 7 as default values.
 * 
 * @author shobit
 * 
 */
class Board {

	int rows;
	int cols;
	// internally, the board is a double array of Color that is row x cols big
	Color[][] board;

	/**
	 * This class is used to build the Board object using the setRows and
	 * setCols methods. If they are not used, the default board size of 6x7 is
	 * built
	 * 
	 * @author shobit
	 */
	static class Builder {
		private int rows = 6;
		private int cols = 7;

		protected Builder setRows(int rows) {
			this.rows = rows;
			return this;
		}

		protected Builder setCols(int cols) {
			this.cols = cols;
			return this;
		}

		protected Board build() {
			return new Board(this);
		}
	}

	/**
	 * This is the private Board constructor that build calls in order to
	 * finally build the Board object
	 */
	private Board(Builder builder) {
		rows = builder.rows;
		cols = builder.cols;
		board = new Color[rows][cols];
	}

	/**
	 * This method is used by the model to set a row,col pair with a particular
	 * Color c
	 * 
	 * @param row
	 * @param col
	 * @param c
	 */
	protected boolean setBoard(int row, int col, Color c) {
		board[row][col] = c;
		return true; // used for testing
	}

	/**
	 * This method is used to obtain the row which is currently available for
	 * the col in the argument
	 * 
	 * @param col
	 * @return row if available. else -1
	 */
	protected int getRowForColumn(int col) {
		// the for loop will check for the first row that is available for the
		// given column. if no rows are available it will return -1
		for (int i = rows - 1; i >= 0; i--) {
			if (board[i][col] == null) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This method returns the number of rows in the board
	 * 
	 * @return
	 */
	protected int getRows() {
		return rows;
	}

	/**
	 * This method returns the number of cols in the board
	 * 
	 * @return
	 */
	protected int getCols() {
		return cols;
	}

	/**
	 * This method returns the Color at the given row, col in the board
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	protected Color getColorAt(int row, int col) {
		return board[row][col];
	}

	/**
	 * This method resets the board. It does so by pointing to a new double
	 * array of Color
	 */
	protected void resetBoard() {
		board = new Color[rows][cols];
	}

	/**
	 * This method checks if the board has become full.
	 * 
	 * @return true if board is full, else false
	 */
	protected boolean isBoardFull() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		result = prime * result + cols;
		result = prime * result + rows;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Board))
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		if (cols != other.cols)
			return false;
		if (rows != other.rows)
			return false;
		return true;
	}

}
