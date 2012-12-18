package edu.nyu.pqs.ssb402.connectfour.view;

import java.awt.Color;

import edu.nyu.pqs.ssb402.connectfour.player.Player;

/**
 * The IView interface is the interface for the view (observer) and declares
 * methods that are required to start the game and listen to messages from the
 * controller
 * 
 * @author shobit
 * 
 */
public interface IView {

	public void start();

	public void gameStarted(int row, int col);

	public void gameMove(int row, int col, Color c);

	public void playersSet();

	public void gameEnded(Player winner);

	public void gameReset();

	public void exitGame();

}