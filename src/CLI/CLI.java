package CLI;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import GameEngine.GameEngine;

public class CLI {
	
	private static CLIState cli_state;
	
	public static void main(String args[]) {
		cli_state = CLIState.MAIN_MENU;
		
		while (true) {
			switch (cli_state) {
			case MAIN_MENU: cli_state = CLIMainMenu.run(); continue;
			case ADD_PLAYER: cli_state = CLIAddPlayer.run(); continue;
			case LIST_PLAYERS: cli_state = CLIListPlayers.run(); continue;
			case REMOVE_PLAYER: cli_state = CLIRemovePlayer.run(); continue;
			case EXIT: break;
			default: cli_state = CLIState.MAIN_MENU;
			}
			break; // break occurs only on case EXIT
		}
		
		System.out.println(CLIStrings.EXIT);
		// Any cleanup (ie. database saving) will occur here
		return;
	}
	
	/**
	 * Parses an integer from the user from the CLI
	 * @return The integer entered by the user
	 * @throws exceptions if the user is dumb
	 */
	protected static int readInt() throws InputMismatchException, NoSuchElementException, IllegalStateException {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}
	
	/**
	 * Parses a line from the user from the CLI
	 * @return The user's input as a String
	 * @throws exceptions if the user is dumb
	 */
	protected static String readString() throws NoSuchElementException, IllegalStateException {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}	
}
