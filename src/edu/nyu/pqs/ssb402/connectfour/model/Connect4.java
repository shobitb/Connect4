package edu.nyu.pqs.ssb402.connectfour.model;

import java.awt.Color;

/**
 * This is the model class that contains the game logic (i.e. rules) and the
 * data (i.e. the board). This class demonstrates the **************** SINGLETON
 * FACTORY PATTERN ************* There has to be always a single instance of the
 * Model and this is done by keeping the constructor private. The public static
 * method getInstance makes sure to keep a single instance of the class at all
 * times. This class is helped by the Controller and together with the View they
 * implement the ************** OBSERVER PATTERN (MVC) ****************
 * 
 * @author shobit 
 * NOTE: I chose to design this class without an interface on purpose.
 * 
 */
public class Connect4 {

	// this is the Board object that keeps the information about the progress of
	// the game
	private Board board;
	// the initialization is made during class load time and is returned to all
	// objects that use the static getInstance method
	private static final Connect4 connect4 = new Connect4();

	// the private constructor so that objects cannot be made using this
	// publicly
	private Connect4() {
	}

	/**
	 * This method is used to get the one and only instance of the Connect4
	 * class created during class load
	 * 
	 * @return
	 */
	public static Connect4 getInstance() {
		return connect4;
	}

	/**
	 * This method creates the board object with rows x cols dimensions. It uses
	 * the Builder class of the Board object to set the parameters
	 * 
	 * @param rows
	 * @param cols
	 * @return if the board is made then true else false
	 */
	public boolean startGame(int rows, int cols) {
		if (rows < 0 || cols < 0) {
			throw new IllegalArgumentException("row, col" + rows + " " + cols);
		}
		board = new Board.Builder().setCols(cols).setRows(rows).build();
		return board == null ? false : true;
	}

	/**
	 * This method is used to make the move at a particular column with a
	 * particular color. The row at which the color is placed is obtained by
	 * using the getRowForColumn method. If the row returned is not -1, then
	 * board is colored with c at row,col
	 * 
	 * @param col
	 * @param c
	 * @return the row if move is made at the col, else -1
	 */
	public int makeMove(int col, Color c) {
		if (col < 0 || col > getCols() || c == null) {
			throw new IllegalArgumentException("col, color" + col + " " + c);
		}
		// if row is -1, simply return the row
		int row = getRowForColumn(col);
		if (row != -1) {
			setBoard(row, col, c);
		}
		return row;
	}

	/**
	 * This method is used to set the board with a color c at row,col.
	 * 
	 * @param row
	 * @param col
	 * @param c
	 */
	public boolean setBoard(int row, int col, Color c) {
		if (row < 0 || row > getRows() || col < 0 || col > getCols()) {
			throw new IllegalArgumentException("row, col, color" + row + " "
					+ col + " " + c);
		}
		return board.setBoard(row, col, c);
	}

	/**
	 * This method is used to get the row which is available for the column col.
	 * 
	 * @param col
	 * @return row if available at that column, else -1
	 */
	public int getRowForColumn(int col) {
		if (col < 0 || col > getCols()) {
			throw new IllegalArgumentException("col " + col);
		}
		return board.getRowForColumn(col);
	}

	/**
	 * This method is used to reset the board
	 */
	public void reset() {
		board.resetBoard();
	}

	/**
	 * This method is a private method called as part of the isThereAWinner
	 * method to check if there is a horizontal win that includes the row,col
	 * combination. It is called at every stage of the game to check if
	 * horizontal win has occurred and therefore requires the row,col
	 * combination
	 * 
	 * @param row
	 * @param col
	 * @return true if horizontal win else false
	 */
	private boolean checkHorizontal(int row, int col) {
		int wins = 0;
		int i = col;
		Color c = getColorAt(row, col);

		// now get the color at row,col and check if the number of wins of that
		// color to the left and right of row,col totals to 4

		// check to the right
		while (i < getCols() && getColorAt(row, i) == c && wins < 4) {
			wins++;
			i++;
		}
		i = col;
		// and check to the left
		while (i > 0 && getColorAt(row, i - 1) == c && wins < 4) {
			wins++;
			i--;
		}

		if (wins == 4) {
			return true;
		}
		return false;
	}

	/**
	 * This method is a private method called as part of the isThereAWinner
	 * method to check if there is a vertical win that includes the row,col
	 * combination. It is called at every stage of the game to check if vertical
	 * win has occurred and therefore requires the row,col combination
	 * 
	 * @param row
	 * @param col
	 * @return true if vertical win else false
	 */
	private boolean checkVertical(int row, int col) {
		int wins = 0;
		int i = row;
		Color c = getColorAt(row, col);
		// now get the color at row,col and check if the number of wins of that
		// color above and below of row,col totals to 4
		// check in the upward direction
		while (i < getRows() && getColorAt(i, col) == c && wins < 4) {
			wins++;
			i++;
		}
		i = row;
		// or check in the downward direction
		while (i > 0 && getColorAt(i - 1, col) == c && wins < 4) {
			wins++;
			i--;
		}
		if (wins == 4) {
			return true;
		}
		return false;
	}

	/**
	 * This method is a private method called as part of the isThereAWinner
	 * method to check if there is a diagonal win that includes the row,col
	 * combination. It is called at every stage of the game to check if a
	 * diagonal win has occurred and therefore requires the row,col combination
	 * 
	 * @param row
	 * @param col
	 * @return true if diagonal win else false
	 */
	private boolean checkDiagonal(int row, int col) {
		// in this we count the wins in both the diagonal directions forward and
		// backward. This requires four while loops for going upward forward
		// (row,col to right top), downward backward (row,col to bottom left),
		// upward backward (row,col to left top), and downward forward (row,col
		// to
		// bottom right)
		int winsForward = 0;
		int winsBackward = 0;
		int i = row;
		int j = col;
		Color c = getColorAt(row, col);
		// go downward forward
		while (i < getRows() && j < getCols() && getColorAt(i, j) == c
				&& winsBackward < 4) {
			winsBackward++;
			i++;
			j++;
		}
		i = row;
		j = col;
		// go upward backward
		while (i > 0 && j > 0 && getColorAt(i - 1, j - 1) == c
				&& winsBackward < 4) {
			winsBackward++;
			i--;
			j--;
		}
		i = row;
		j = col;
		// go upward forward
		while (i > 0 && j < getCols() - 1 && getColorAt(i - 1, j + 1) == c
				&& winsForward < 4) {
			winsForward++;
			i--;
			j++;
		}
		i = row;
		j = col;
		// go downward backward
		while (i < getRows() && j >= 0 && getColorAt(i, j) == c
				&& winsForward < 4) {
			winsForward++;
			i++;
			j--;
		}
		if (winsForward == 4 || winsBackward == 4) {
			return true;
		}
		return false;
	}

	/**
	 * This method is used to check if there is a winner either horizontally,
	 * vertically or diagonally with the row,col point as reference
	 * 
	 * @param row
	 * @param col
	 * @return Color that wins else null
	 */
	public Color isThereAWinner(int row, int col) {
		if (row < 0 || col < 0 || row > getRows() || col > getCols()) {
			throw new IllegalArgumentException("row, col " + row + col);
		}

		if (checkHorizontal(row, col) || checkVertical(row, col)
				|| checkDiagonal(row, col)) {
			return getColorAt(row, col);
		}
		return null;
	}

	/**
	 * Checks if the board is full
	 * 
	 * @return true if full else false
	 */
	public boolean isBoardFull() {
		return board.isBoardFull();
	}

	/* public for the purpose of testing reset */
	public Color getColorAt(int row, int col) {
		return board.getColorAt(row, col);
	}

	/**
	 * This method returns the number of rows in the board
	 * 
	 * @return rows
	 */
	public int getRows() {
		return board.getRows();
	}

	/**
	 * This method returns the number of cols in the board
	 * 
	 * @return cols
	 */
	public int getCols() {
		return board.getCols();
	}

}