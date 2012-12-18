package edu.nyu.pqs.ssb402.connectfour.player;

import java.awt.Color;

import edu.nyu.pqs.ssb402.connectfour.model.Connect4;

/**
 * HumanPlayer is an implementation of the Player interface. It is used as the
 * human for playing single player games or double player games.
 * 
 * @author shobit
 * 
 */
public class HumanPlayer implements Player {

	Color c;
	// this is used to by the Controller to set the col for the next move for
	// the player to make
	int nextMoveCol;

	protected HumanPlayer(Color c) {
		this.c = c;
	}

	/**
	 * This method is where the Player makes its move. Since this is a
	 * HumanPlayer, the column is received from the controller, where the
	 * controller sets the nextMoveCol. The row where the move was made is
	 * returned. It may or may not be -1.
	 * 
	 * @param connect4
	 *            - the model used to make the move
	 * @return row - the row where the move was made
	 * @throws IllegalArgumentException if the argument is null
	 */
	@Override
	public int play(Connect4 connect4) {
		if (connect4 == null) {
			throw new IllegalArgumentException("connect4 " + null);
		}
		return connect4.makeMove(nextMoveCol, getColor());
	}

	/**
	 * This method returns the Color for this player
	 */
	@Override
	public Color getColor() {
		return c;
	}

	/**
	 * This method returns the type of the player which is HUMAN
	 */
	@Override
	public PlayerType getType() {
		return PlayerType.HUMAN;
	}

	/**
	 * This method is used by the controller to denote to the player object
	 * where the next column move has to be made.
	 * @throws IllegalArgumentException if the col is not in range
	 *
	 */
	@Override
	public void setColumnForMove(int col) {
		if (col < -1) {
			throw new IllegalArgumentException("col " + col);
		}
		nextMoveCol = col;
	}

	/**
	 * This method returns the column of the last move made by the Human player
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
		return "HumanPlayer [c=" + c + ", nextMoveCol=" + nextMoveCol + "]";
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
		if (!(obj instanceof HumanPlayer))
			return false;
		HumanPlayer other = (HumanPlayer) obj;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		if (nextMoveCol != other.nextMoveCol)
			return false;
		return true;
	}

}
