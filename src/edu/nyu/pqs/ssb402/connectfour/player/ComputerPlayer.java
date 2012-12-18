package edu.nyu.pqs.ssb402.connectfour.player;

import java.awt.Color;

import edu.nyu.pqs.ssb402.connectfour.model.Connect4;

/**
 * ComputerPlayer is an implementation of the Player interface. It is used as
 * the AI for playing single player games.
 * 
 * @author shobit
 * 
 */
public class ComputerPlayer implements Player {

	Color c;
	// this is used to communicate to the Controller that the move was made at
	// this column
	int nextMoveCol;

	protected ComputerPlayer(Color c) {
		this.c = c;
	}

	/**
	 * This method is where the Computer makes its move. For all columns, it
	 * makes a valid move and checks if that move results in a win. If not, it
	 * undoes that move and plays another valid move until all columns are
	 * checked. If during the process it wins, it sets its nextMoveCol and
	 * returns the corresponding row. Else it plays a random move.
	 * 
	 * @params connect4 - the model provided by the controller to make the move
	 * @return row - the row at which the move was made. the column is available
	 *         in the nextMoveCol
	 *  @throws IllegalArgumentException if the argument is null
	 */
	@Override
	public int play(Connect4 connect4) {
		if (connect4 == null) {
			throw new IllegalArgumentException();
		}
		int row = -1;

		for (int i = 0; i < connect4.getCols(); i++) {
			// make the move for all columns from 0 to max columns on the board
			row = connect4.makeMove(i, c);
			if (row == -1) {
				continue;
			}
			if (connect4.isThereAWinner(row, i) == this.c) {
				// if the move resulted in a win, it's a good move!
				setColumnForMove(i);
				return row;
			} else {
				// undo the move and continue with the for loop
				connect4.setBoard(row, i, null);
				row = -1;
			}
		}

		while (row == -1) {
			// make a random valid move
			int rand = (int) (Math.random() * (connect4.getCols()));
			row = connect4.makeMove(rand, c);
			setColumnForMove(rand);
		}
		return row;
	}

	/**
	 * This method returns the Color of itself
	 */
	@Override
	public Color getColor() {
		return c;
	}

	/**
	 * This method returns the type of the player
	 */
	@Override
	public PlayerType getType() {
		return PlayerType.COMPUTER;
	}

	/**
	 * This method sets the nextMovCol with the column move it has made so that
	 * the controller can use it via the getColumnForMove method
	 * @throws IllegalArgumentException if the col is not in range
	 */
	@Override
	public void setColumnForMove(int col) {
		if (col < 0) {
			throw new IllegalArgumentException("Col cannot be less than zero");
		}
		nextMoveCol = col;

	}

	/**
	 * This method returns the column of the last move made by the Computer
	 */
	@Override
	public int getColumnForMove() {
		return nextMoveCol;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "ComputerPlayer [c=" + c + ", nextMoveCol=" + nextMoveCol + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + nextMoveCol;
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
		if (!(obj instanceof ComputerPlayer))
			return false;
		ComputerPlayer other = (ComputerPlayer) obj;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		if (nextMoveCol != other.nextMoveCol)
			return false;
		return true;
	}

	/**
	 * THIS METHOD IS FOR TESTING ONLY This method is exactly like the play
	 * method except for the random generation part that has been replaced with
	 * a hardcoded value for testing
	 * 
	 * @param connect4
	 * @return
	 */
	public int playForTest(Connect4 connect4) {
		int row = -1;
		for (int i = 0; i < connect4.getCols(); i++) {
			row = connect4.makeMove(i, c);
			if (row == -1) {
				continue;
			}
			if (connect4.isThereAWinner(row, i) == this.c) {
				setColumnForMove(i);
				return row;
			} else {
				connect4.setBoard(row, i, null);
				row = -1;
			}
		}

		int rand = 3;
		row = connect4.makeMove(rand, c);
		setColumnForMove(rand);
		return row;
	}

}
