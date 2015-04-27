package CLI;

import GameEngine.GameEngine;
import GameEngine.Player;

public class CLIRemovePlayer {
	
	/**
	 * Prompts the user to de-register a player.
	 * @return CLIState.MAIN_MENU after an attempt to register
	 */
	protected static CLIState run() {
		String player_name;
		
		System.out.println(CLIStrings.REMOVE_PLAYER_PROMPT);
		CLIListPlayers.listPlayers();

		try{				
			player_name = CLI.readString();
			//if (GameEngine.removePlayer(player_name)) {
				System.out.println(CLIStrings.SUCCESSFULLY_REMOVED_PLAYER(player_name));
			//} else {
				System.out.println(CLIStrings.PLAYER_DOES_NOT_EXIST(player_name));
				System.out.println("");
			//}		
		} catch (Exception e) {
			System.out.println(CLIStrings.INVALID_INPUT);
		}
		
		return CLIState.MAIN_MENU;
	} 		
}		

