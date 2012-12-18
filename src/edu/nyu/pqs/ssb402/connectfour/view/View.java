package edu.nyu.pqs.ssb402.connectfour.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import edu.nyu.pqs.ssb402.connectfour.controller.Controller;
import edu.nyu.pqs.ssb402.connectfour.controller.IController;
import edu.nyu.pqs.ssb402.connectfour.player.Player;
import edu.nyu.pqs.ssb402.connectfour.player.PlayerType;
import edu.nyu.pqs.ssb402.connectfour.utilities.ColorToString;

/**
 * The View is an implementation IView interface which is an observer for the
 * OBSERVER PATTERN. It uses Java's AWT Swing library for creating the layout
 * and frames. It contains methods to start, reset, exit the game and listeners
 * for events from the controller
 * 
 * @author shobit
 * 
 */
public class View implements IView, ActionListener {

	private IController controller = new Controller();
	private JFrame playerSelectionFrame = new JFrame();
	private JFrame gameFrame = new JFrame();
	private JFrame startGameFrame = new JFrame();
	private JPanel playerSelectionPanel = new JPanel();
	private JPanel gamePanel = new JPanel();
	private JPanel resetAndExitPanel = new JPanel();
	private JPanel startGamePanel = new JPanel();
	private JButton singlePlayerButton = new JButton("Single Player");
	private JButton twoPlayerButton = new JButton("Two Player");
	private JButton startGameButton = new JButton("Start!");
	private JButton resetGameButton = new JButton("Reset");
	private JButton exitGameButton = new JButton("Exit");
	private JPanel panelForResultOfGame = new JPanel();
	private JTextField rowsTextField = new JTextField("6", 3);
	private JTextField colsTextField = new JTextField("7", 3);
	private JLabel configurations = new JLabel(
			"Standard configurations: 7x6, 8x7, 9x7 and 10x7");
	private JLabel enterRowsLabel = new JLabel("Enter rows: ");
	private JLabel enterColsLabel = new JLabel("Enter cols: ");
	private Border grayLine = BorderFactory.createLineBorder(Color.GRAY);
	private int rows = 6;
	private int cols = 7;
	private JButton[] columnButtons = new JButton[cols];
	private JPanel[][] panelsOnMainBoard = new JPanel[rows][cols];

	/**
	 * This constructor takes a controller of whose viewer this view will
	 * become. It asks the controller to add itself as one of the viewers.
	 * 
	 * @param controller
	 */
	public View(Controller controller) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.controller = controller;
		controller.addView(this);
		// graphic related set up
		setUpFrameCloseDefaults();
		setUpButtonsWithActionListeners();
		start();
	}

	/**
	 * This method will add action listeners to the buttons in the game
	 */
	private void setUpButtonsWithActionListeners() {
		singlePlayerButton.addActionListener(this);
		twoPlayerButton.addActionListener(this);
		startGameButton.addActionListener(this);
		exitGameButton.addActionListener(this);
		resetGameButton.addActionListener(this);
	}

	/**
	 * This method will set up the default actions on close for the frame
	 */
	private void setUpFrameCloseDefaults() {
		playerSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method shows the second screen when the game is started. It provides
	 * the options to set rows and cols
	 */
	@Override
	public void start() {
		playerSelectionPanel.setBackground(Color.GRAY);
		playerSelectionPanel.setLayout(new GridLayout(0, 1));
		playerSelectionPanel.add(singlePlayerButton);
		playerSelectionPanel.add(twoPlayerButton);
		resetAndExitPanel.setBackground(Color.GRAY);
		resetAndExitPanel.setLayout(new GridLayout(0, 1));
		resetAndExitPanel.add(exitGameButton);
		playerSelectionPanel.add(resetAndExitPanel);
		playerSelectionFrame.add(playerSelectionPanel);
		playerSelectionFrame.setSize(cols * 60, cols * 60);
		playerSelectionFrame.setLocationRelativeTo(null);
		playerSelectionFrame.setVisible(true);
	}

	/**
	 * This method is called when the game play starts. It is the used by the
	 * controller to notify the view. It displays the board with rows x cols
	 * dimensions.
	 */
	@Override
	public void gameStarted(int row, int col) {
		this.rows = row;
		this.cols = col;
		columnButtons = new JButton[cols];
		panelsOnMainBoard = new JPanel[rows][cols];
		startGameFrame.dispose();
		gamePanel.setBackground(Color.GRAY);
		gamePanel
				.setPreferredSize(new Dimension(cols * 60, cols * 60 * 8 / 10));
		resetAndExitPanel.setBackground(Color.GRAY);
		resetAndExitPanel.setPreferredSize(new Dimension(cols * 60,
				cols * 60 * 1 / 10));
		panelForResultOfGame.setBackground(Color.GRAY);
		panelForResultOfGame.setPreferredSize(new Dimension(cols * 60,
				cols * 60 * 1 / 10));
		panelForResultOfGame.setVisible(false);
		gameFrame.getContentPane().add(panelForResultOfGame,
				BorderLayout.PAGE_START);
		gameFrame.getContentPane().add(gamePanel, BorderLayout.CENTER);
		gameFrame.getContentPane()
				.add(resetAndExitPanel, BorderLayout.PAGE_END);
		gamePanel.setLayout(new GridLayout(0, cols));
		resetAndExitPanel.setLayout(new GridLayout(0, 2));
		for (int i = 0; i < cols; i++) {
			JButton columnButton = new JButton(Integer.toString(i + 1));
			columnButton.addActionListener(this);
			gamePanel.add(columnButton);
			columnButtons[i] = columnButton;
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// the grids that get colored are JPanel implementations
				JPanel panel = new JPanel();
				panel.setBorder(grayLine);
				gamePanel.add(panel);
				panelsOnMainBoard[i][j] = panel;
			}
		}
		resetAndExitPanel.add(resetGameButton);
		resetAndExitPanel.add(exitGameButton);
		gameFrame.setSize(cols * 60, cols * 60);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
	}

	/**
	 * This method is called when a valid move takes place. This is used by the
	 * controller to notify the view to set the panel at row,col with the Color
	 * c
	 */
	@Override
	public void gameMove(int row, int col, Color c) {
		panelsOnMainBoard[row][col].setBackground(c);
	}

	/**
	 * This method is called when the players are set. It is used by the
	 * controller to notify the view
	 */
	@Override
	public void playersSet() {

		playerSelectionFrame.dispose();
		startGamePanel.setLayout(new GridLayout(0, 1));
		enterRowsLabel.setLabelFor(rowsTextField);
		enterColsLabel.setLabelFor(colsTextField);
		startGamePanel.add(configurations);
		startGamePanel.add(enterColsLabel);
		startGamePanel.add(colsTextField);
		startGamePanel.add(enterRowsLabel);
		startGamePanel.add(rowsTextField);
		startGamePanel.add(startGameButton);
		startGameFrame.add(startGamePanel);
		startGameFrame.setSize(cols * 60, cols * 60);
		startGameFrame.setLocationRelativeTo(null);
		startGameFrame.setVisible(true);
	}

	/**
	 * This method is called when the game ends. There are two cases when the
	 * game ends: if someone wins, the argument winner will be a Player object.
	 * if the game draws, the argument winner will be null It is used by the
	 * controller to notify the view that the game has ended
	 */
	@Override
	public void gameEnded(Player winner) {
		if (winner != null) {
			String color = ColorToString.getString(winner.getColor());
			panelForResultOfGame.add(new JLabel(color + " wins!"),
					BorderLayout.CENTER);
			panelForResultOfGame.setBackground(winner.getColor());
			panelForResultOfGame.setVisible(true);
		} else {
			panelForResultOfGame.add(new JLabel("Game draws"),
					BorderLayout.CENTER);
			panelForResultOfGame.setVisible(true);
		}
		for (JButton button : columnButtons) {
			button.removeActionListener(this);
		}
	}

	/**
	 * This method is called when the game is exit. It is used by the controller
	 * to notify the view that the game has been exit
	 */
	@Override
	public void exitGame() {
		controller.removeView(this);
		System.exit(0);
	}

	/**
	 * This method is called when the game is reset. It is used by the
	 * controller to notify the view that the game has been reset
	 */
	@Override
	public void gameReset() {
		gamePanel.removeAll();
		panelForResultOfGame.removeAll();
		gameStarted(rows, cols);
	}

	/**
	 * This method is called when any of the buttons is pressed anytime in the
	 * game which button was pressed is decided by any of the if conditions
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == singlePlayerButton) {
			controller.setPlayers(PlayerType.HUMAN, PlayerType.COMPUTER);
		}

		if (e.getSource() == twoPlayerButton) {
			controller.setPlayers(PlayerType.HUMAN, PlayerType.HUMAN);
		}

		if (e.getSource() == startGameButton) {
			try {
				rows = Integer.parseInt(rowsTextField.getText());
				cols = Integer.parseInt(colsTextField.getText());
			} catch (NumberFormatException exception) {

			}
			controller.startGame(rows, cols);
		}

		if (e.getSource() == exitGameButton) {
			controller.fireGameExitEvent();
		}

		if (e.getSource() == resetGameButton) {
			controller.resetGame();
		}

		if (panelsOnMainBoard != null) {
			for (int i = 0; i < cols; i++) {
				if (e.getSource() == columnButtons[i]) {
					controller.makeMove(i);
				}
			}
		}
	}
}
