package CLI;

import java.util.ArrayList;

import GameEngine.GameEngine;
import GameEngine.Player;

public class CLIListPlayers {
	
	/**
	 * Displays the list of all registered players
	 * @return CLIState.MAIN_MENU after printing 
	 */
	protected static CLIState run() {
		System.out.println(CLIStrings.LIST_OF_PLAYERS_PREAMBLE);
		listPlayers();
		return CLIState.MAIN_MENU;
	}

	/**
	 * Prints a list of all registered players.
	 */
	protected static void listPlayers() {
		ArrayList<Player> players = (ArrayList<Player>) Player.getAllPlayers();
		for (Player p : players) {
			System.out.println(p.getName());
		}
		System.out.println("");
	}
}
