package edu.nyu.pqs.ssb402.connectfour.player;

import java.awt.Color;

/**
 * The PlayerFactory uses the *********** FACTORY PATTERN *****************
 * for creating Player object based
 * on a PlayerType. It has a single method which takes a PlayerType and Color
 * object and creates the Player for that Color
 * 
 * @author shobit
 * 
 */
public class PlayerFactory {

	/**
	 * This method takes a PlayerType and Color and returns the appropriate
	 * Player
	 * 
	 * @param player
	 * @param color
	 * @return Player object that is created or null if not
	 * @throws IllegalArgumentException
	 */
	public static Player createPlayer(PlayerType player, Color color) {
		if (player == null || color == null) {
			throw new IllegalArgumentException("player, color " + player + " "
					+ color);
		}
		if (player == PlayerType.HUMAN) {
			return new HumanPlayer(color);
		} else if (player == PlayerType.COMPUTER) {
			return new ComputerPlayer(color);
		}

		return null;
	}
}
