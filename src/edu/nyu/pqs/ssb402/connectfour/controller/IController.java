package edu.nyu.pqs.ssb402.connectfour.controller;

import java.awt.Color;

import edu.nyu.pqs.ssb402.connectfour.player.Player;
import edu.nyu.pqs.ssb402.connectfour.player.PlayerType;
import edu.nyu.pqs.ssb402.connectfour.view.IView;

/**
 * The IController is the interface for the controllers. For a particular
 * implementation of the game, there will be a single controller but this is
 * provided if someone wants to develop their own game.
 * 
 * @author shobit
 * 
 */
public interface IController {

	public void addView(IView view);

	public void removeView(IView view);

	public void setPlayerTurn();

	public void setPlayers(PlayerType player1, PlayerType player2);

	public void startGame(int rows, int cols);

	public void makeMove(int col);

	public void resetGame();

	public Player getCurrentPlayer();

	public Player checkIfAnyWinner(int row, int col);

	public Player getPlayerWithColor(Color c);

	public void fireGameStartedEvent(int row, int col);

	public void fireGameResetEvent();

	public void fireGamePlayersSetEvent();

	public void fireGameEndedEvent(Player winner);

	public void fireGameMoveEvent(int row, int col, Color c);

	public void fireGameExitEvent();

	boolean isBoardFull();

}
