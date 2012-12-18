package edu.nyu.pqs.ssb402.connectfour.controller;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.ssb402.connectfour.model.Connect4;
import edu.nyu.pqs.ssb402.connectfour.player.Player;
import edu.nyu.pqs.ssb402.connectfour.player.PlayerFactory;
import edu.nyu.pqs.ssb402.connectfour.player.PlayerType;
import edu.nyu.pqs.ssb402.connectfour.view.IView;

public class ControllerTest {

	IController controller;
	IView viewMock;
	Connect4 connect4Mock;

	@Before
	public void setUp() {
		connect4Mock = createMock(Connect4.class);
		viewMock = createMock(IView.class);
		controller = new Controller();
		controller.addView(viewMock);
	}
	
	@Test
	public void testSetPlayers() {
		viewMock.playersSet();
		replay(viewMock);
		controller.setPlayers(PlayerType.HUMAN, PlayerType.COMPUTER);
		verify(viewMock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddView_null() {
		controller.addView(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveView_null() {
		controller.removeView(null);
	}

	@Test
	public void testStartGame() {
		viewMock.gameStarted(6, 7);
		replay(viewMock);
		controller.startGame(6, 7);
		verify(viewMock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStartGame_rowsLessThanZero() {
		controller.startGame(-1, 7);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStartGame_colsLessThanZero() {
		controller.startGame(6, -7);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPlayer_nullFirstPlayer() {
		controller.setPlayers(null, PlayerType.HUMAN);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPlayer_nullSecondPlayer() {
		controller.setPlayers(PlayerType.COMPUTER, null);
	}
	
	@Test
	public void testMakeMove() {	
		viewMock.playersSet();
		viewMock.gameStarted(6, 7);
		viewMock.gameMove(5, 3, Color.RED);
		replay(viewMock);
		controller.setPlayers(PlayerType.HUMAN, PlayerType.HUMAN);
		controller.startGame(6, 7);
		controller.makeMove(3);
		verify(viewMock);
	}
	
	@Test
	public void testMakeMove_winCase() {
		Player player1 = PlayerFactory.createPlayer(PlayerType.HUMAN, Color.RED);
		Player player2 = PlayerFactory.createPlayer(PlayerType.HUMAN, Color.YELLOW);
		viewMock.playersSet();
		viewMock.gameStarted(6, 7);
		viewMock.gameMove(5, 1, Color.RED);
		viewMock.gameMove(5, 2, Color.YELLOW);
		viewMock.gameMove(4, 1, Color.RED);
		viewMock.gameMove(4, 2, Color.YELLOW);
		viewMock.gameMove(3, 1, Color.RED);
		viewMock.gameMove(3, 2, Color.YELLOW);
		viewMock.gameMove(2, 1, Color.RED);
		player1.setColumnForMove(1); // because two players are equal if their Color and nextMovCol are equal
		viewMock.gameEnded(player1);
		replay(viewMock);
		controller.setPlayers(PlayerType.HUMAN, PlayerType.HUMAN);
		controller.startGame(6, 7);
		controller.makeMove(1);
		controller.makeMove(2);
		controller.makeMove(1);
		controller.makeMove(2);
		controller.makeMove(1);
		controller.makeMove(2);
		controller.makeMove(1);
		verify(viewMock);
	}
	
	@Test
	public void testMakeMove_drawCase() {
		viewMock.playersSet();
		viewMock.gameStarted(3, 3);
		viewMock.gameMove(2, 0, Color.RED);
		viewMock.gameMove(2, 1, Color.YELLOW);
		viewMock.gameMove(2, 2, Color.RED);
		viewMock.gameMove(1, 0, Color.YELLOW);
		viewMock.gameMove(1, 1, Color.RED);
		viewMock.gameMove(1, 2, Color.YELLOW);
		viewMock.gameMove(0, 0, Color.RED);
		viewMock.gameMove(0, 1, Color.YELLOW);
		viewMock.gameMove(0, 2, Color.RED);
		viewMock.gameEnded(null);
		replay(viewMock);
		controller.setPlayers(PlayerType.HUMAN, PlayerType.HUMAN);
		controller.startGame(3, 3);
		for (int i = 0; i < 9; i++) {
			controller.makeMove(i % 3);
		}
		verify(viewMock);
	}
	
	@Test
	public void testGameExit() {	
		viewMock.exitGame();
		replay(viewMock);
		controller.fireGameExitEvent();
		verify(viewMock);
	}

	@Test
	public void testReset() {	
		viewMock.gameReset();
		replay(viewMock);
		controller.resetGame();
		verify(viewMock);
	}
	
	@Test
	public void testRemoveView() {
		// viewMock.gameReset();
		replay(viewMock);
		controller.removeView(viewMock);
		controller.resetGame();
		verify(viewMock);
	}	
	
	@Test
	public void testGetPlayerWithColor() {
		controller.setPlayers(PlayerType.HUMAN, PlayerType.COMPUTER);
		controller.startGame(6, 7);
		Player p = controller.getPlayerWithColor(Color.YELLOW);
		assertTrue(p.getType() == PlayerType.COMPUTER);
	}
}