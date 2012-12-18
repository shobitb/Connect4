package edu.nyu.pqs.ssb402.connectfour.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.ssb402.connectfour.model.Connect4;
import edu.nyu.pqs.ssb402.connectfour.player.Player;
import edu.nyu.pqs.ssb402.connectfour.player.PlayerFactory;
import edu.nyu.pqs.ssb402.connectfour.player.PlayerType;
import edu.nyu.pqs.ssb402.connectfour.view.IView;

/**
 * The Controller class is a medium between the Views and the Model. It responds
 * to the messages from the Views, fires off some events in response to these
 * messages, talks to the Model. It contains the player objects and controls the
 * turns of the players.
 * 
 * In a way, this class partly demonstrates the use of OBSERVER PATTERN. This is
 * the Subject of the Observer Pattern (together with the model Connect4),
 * because many Observers (the IView instances) observe the Controller class.
 * The Controller class maintains a list of its observers to which it notifies
 * when certain events occur
 * 
 * @author shobit
 * 
 */
public class Controller implements IController {

	private List<IView> viewers = new ArrayList<IView>();
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	// this is the Singleton instance of the Connect4 model
	private Connect4 connect4 = Connect4.getInstance();

	/**
	 * This method takes an instance of IView and adds it to its list of viewers
	 * if it is not null.
	 * 
	 * @params view to be added to the list of viewers
	 */
	@Override
	public void addView(IView view) {
		if (view == null) {
			throw new IllegalArgumentException();
		}
		viewers.add(view);
	}

	/**
	 * This method takes an instance of IView and removes it from its list of
	 * viewers if it not null
	 * 
	 * @params view to be removed from the list of viewers
	 */
	@Override
	public void removeView(IView view) {
		if (view == null) {
			throw new IllegalArgumentException();
		}
		viewers.remove(view);
	}

	/**
	 * This method is used to control which player will play next. It sets the
	 * next player to play.
	 * 
	 */
	@Override
	public void setPlayerTurn() {
		currentPlayer = getCurrentPlayer() == player1 ? player2 : player1;
	}

	/**
	 * This method takes two PlayerType objects from a view and asks the
	 * PlayerFactory to create two Players. It then fires an event to notify all
	 * views that the players have been set
	 * 
	 * @params player1, player2 are two PlayerType objects to be created. can be
	 *         HUMAN and HUMAN or HUMAN and COMPUTER
	 */
	@Override
	public void setPlayers(PlayerType player1, PlayerType player2) {
		if (player1 == null || player2 == null) {
			throw new IllegalArgumentException("player1 " + player1
					+ " player2 " + player2);
		}
		this.player1 = PlayerFactory.createPlayer(player1, Color.RED);
		this.player2 = PlayerFactory.createPlayer(player2, Color.YELLOW);
		currentPlayer = this.player1;
		fireGamePlayersSetEvent();
	}

	/**
	 * This method takes rows and cols from a view and tells the model to start
	 * the game with the rows and cols. It then fires a game started event to
	 * notify all views that the game has been started
	 * 
	 * @params rows, cols - the size of the board
	 */
	@Override
	public void startGame(int rows, int cols) {
		if (rows < 0 || cols < 0) {
			throw new IllegalArgumentException("rows, cols " + rows + " "
					+ cols);
		}
		currentPlayer = player1;
		connect4.startGame(rows, cols);
		fireGameStartedEvent(rows, cols);
	}

	/**
	 * This method takes a column from a view to make a move. It asks the Player
	 * objects to play the next move at the column at the appropriate row. If it
	 * is a HumanPlayer, this method sets the player's columnForMove. Else it
	 * asks the ComputerPlayer to play its move.
	 */
	@Override
	public void makeMove(int col) {
		Player winner = null;
		int row;

		if (getCurrentPlayer().getType() == PlayerType.HUMAN) {
			// set the player's next move column before asking the player to
			// play
			getCurrentPlayer().setColumnForMove(col);
			row = getCurrentPlayer().play(connect4);
			if (row == -1) {
				// the player will have to choose another column
				return;
			}
			fireGameMoveEvent(row, col, getCurrentPlayer().getColor());
			if ((winner = checkIfAnyWinner(row, col)) != null || isBoardFull()) {
				// if someone has won, the winner is not null. if the board is
				// full, the winner is null denoting draw situation
				fireGameEndedEvent(winner);
				return;
			}
			setPlayerTurn();
			// check if the next player is COMPUTER
			if (getCurrentPlayer().getType() == PlayerType.COMPUTER) {
				row = getCurrentPlayer().play(connect4);
				// the computer will set its column for move and we will get it
				// here
				col = getCurrentPlayer().getColumnForMove();
				fireGameMoveEvent(row, col, getCurrentPlayer().getColor());
				if ((winner = checkIfAnyWinner(row, col)) != null
						|| isBoardFull()) {
					fireGameEndedEvent(winner);
					return;
				}
				setPlayerTurn();
			}
		}
	}

	/**
	 * This method checks if the board is full
	 * 
	 * @return true if the board is full
	 */
	@Override
	public boolean isBoardFull() {
		return connect4.isBoardFull();
	}

	/**
	 * 
	 * This method get the Player object with the Color c
	 * 
	 * @params c - The Player whose Color is c
	 */
	@Override
	public Player getPlayerWithColor(Color c) {
		return player1.getColor() == c ? player1 : player2;
	}

	/**
	 * This method asks the model to reset the game and then notifies all the
	 * viewers that the game has reset
	 */
	@Override
	public void resetGame() {
		currentPlayer = player1;
		connect4.reset();
		fireGameResetEvent();
	}

	/**
	 * This method returns the current player
	 */
	@Override
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * This method notifies all the viewers of the controller that the game has
	 * started with board size equal to rows x cols
	 */
	@Override
	public void fireGameStartedEvent(int rows, int cols) {
		for (IView viewer : viewers) {
			viewer.gameStarted(rows, cols);
		}
	}

	/**
	 * This method notifies all the viewers of the controller that the game has
	 * been reset
	 */
	@Override
	public void fireGameResetEvent() {
		for (IView viewer : viewers) {
			viewer.gameReset();
		}
	}

	/**
	 * This method notifies all the viewers of the controller that the players
	 * have been set
	 */
	@Override
	public void fireGamePlayersSetEvent() {
		for (IView viewer : viewers) {
			viewer.playersSet();
		}
	}

	/**
	 * This method notifies all the viewers of the controller that the game
	 * event has ended
	 * 
	 * @params winner - if null, the game resulted in a draw. else it consists
	 *         of a Player object denoting the winner
	 */
	@Override
	public void fireGameEndedEvent(Player winner) {
		for (IView viewer : viewers) {
			viewer.gameEnded(winner);
		}
	}

	/**
	 * This method notifies all the viewers of the controller that a move has
	 * been made in the game
	 * 
	 * @params row, col, c - a player made a move at (row,col) with the color c
	 */
	@Override
	public void fireGameMoveEvent(int row, int col, Color c) {
		for (IView viewer : viewers) {
			viewer.gameMove(row, col, c);
		}
	}

	/**
	 * This method asks the model to check if there is a winner. The controller
	 * calls this after every move.
	 * 
	 * @params row, col - the check is made from the row,col that was last
	 *         inserted.
	 */
	@Override
	public Player checkIfAnyWinner(int row, int col) {
		Color color = connect4.isThereAWinner(row, col);
		if (color != null) {
			return getPlayerWithColor(color);
		}
		return null;
	}

	/**
	 * This method notifies all the viewers of the controller that the game exit
	 * event has occurred
	 */
	@Override
	public void fireGameExitEvent() {
		for (IView viewer : viewers) {
			viewer.exitGame();
		}
	}
}
