package CLI;

import GameEngine.GameEngine;

public class CLIAddPlayer {

	/**
	 * Prompts the user to register a new player.
	 * 
	 * @return CLIState.MAIN_MENU after an attempt to register
	 */
	protected static CLIState run() {
		String new_player_name;

		System.out.println(CLIStrings.ADD_PLAYER_PROMPT);

		try {
			new_player_name = CLI.readString();
			System.out.println(GameEngine.registerPlayer(new_player_name));
			// if (GameEngine.registerPlayer(new_player_name)) {
			// System.out.println(CLIStrings.SUCCESSFULLY_ADDED_PLAYER(new_player_name));
			// } else {
			// System.out.println(CLIStrings.PLAYER_ALREADY_EXISTS(new_player_name));
			// System.out.println("");
			// }
		} catch (Exception e) {
			System.out.println(CLIStrings.INVALID_INPUT);
		}

		return CLIState.MAIN_MENU;
	}
}
