package edu.nyu.pqs.ssb402.connectfour.player;

import java.awt.Color;

import edu.nyu.pqs.ssb402.connectfour.model.Connect4;

/**
 * The Player interface is used by any player. It declares the methods needed to
 * perform the Player roles such as play, setColumnForMove and few getter
 * methods to help other classes
 * 
 * @author shobit
 * 
 */
public interface Player {

	public void setColumnForMove(int col);

	public int getColumnForMove();

	public int play(Connect4 connect4);

	public Color getColor();

	public PlayerType getType();

}
