package GameEngine;

import java.util.List;
import java.util.Map;

import Character.Character;
import GameEngine.GameRegistration.Player;
import Resources.GameMessage;

public class GameManagement {
	/**
	 * Starts a new game
	 * 
	 * @param in_game
	 * @param player_character_map
	 * @param alive_characters
	 * @return appropriate game message string
	 */
	protected static String startGame(boolean in_game,
			Map<Player, Character> player_character_map,
			List<Character> alive_characters) {
		if (!in_game) {
			if (assignAllCharacters(player_character_map, alive_characters)) {
				return GameMessage.Management.NEWGAME_SUCCESS();
			} else {
				return GameMessage.Management.NEWGAME_FAIL();
			}
		} else {
			return GameMessage.Management.NEWGAME_INGAME();
		}
	}
	
	/**
	 * Starts a new day
	 * 
	 * @return appropriate game message string
	 */
	protected static String startNewDay() {
		return null;
	}

	/**
	 * Starts the lynch
	 * 
	 * @return appropriate game message string
	 */
	protected static String startLynch() {
		return null;
	}
	
	/**
	 * Assigns all players to a character
	 * 
	 * @param player_character_map
	 * @param alive_characters
	 * @return true if success, else false
	 */
	protected static boolean assignAllCharacters(
			Map<Player, Character> player_character_map,
			List<Character> alive_characters) {
		return true;
	}
}
