package edu.nyu.pqs.ssb402.connectfour.utilities;

import edu.nyu.pqs.ssb402.connectfour.controller.Controller;
import edu.nyu.pqs.ssb402.connectfour.view.IView;
import edu.nyu.pqs.ssb402.connectfour.view.View;

/**
 * This class is the starting point of execution for the game
 * 
 * @author shobit
 * 
 */
public class Starter {

	public static void main(String[] args) {
		Controller controller = new Controller();
		// starts two views
		new View(controller);
		new View(controller);
	}
}
