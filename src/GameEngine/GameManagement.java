package GameEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import Character.Character;
import Character.CharacterFactory;
import Character.CharacterFactory.Roles;
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
	protected static String startGame(List<Roles> role_list,
			Map<Player, Character> player_character_map,
			List<Player> alive_players) {
		if (assignAllCharacters(role_list, player_character_map, alive_players)) {
			return GameMessage.Management.NEWGAME_SUCCESS();
		} else {
			return GameMessage.Management.NEWGAME_FAIL();
		}
	}

	/**
	 * Starts a new day
	 * 
	 * @return appropriate game message string
	 */
	protected static String startNewDay(
			Map<Player, Character> player_character_map,
			List<Player> alive_players) {
		if (performNightActions(player_character_map, alive_players)) {
			return GameMessage.Management.NIGHTACTION_SUCCESS();
		} else {
			return GameMessage.Management.NIGHTACTION_FAIL();
		}
	}

	/**
	 * Starts the lynch
	 * 
	 * @return appropriate game message string
	 */
	protected static String startLynch(List<Character> alive_characters) {
		return null;
	}

	/**
	 * Assigns all players to a character
	 * 
	 * @param player_character_map
	 * @param alive_characters
	 * @return true if success, else false
	 */
	private static boolean assignAllCharacters(List<Roles> role_list,
			Map<Player, Character> player_character_map,
			List<Player> alive_players) {
		alive_players.clear();
		player_character_map.clear();
		alive_players.addAll(GameRegistration.getAllPlayers());
		Collections.shuffle(role_list);
		for (int i = 0; i < alive_players.size(); i++) {
			player_character_map.put(alive_players.get(i), CharacterFactory
					.makeCharacter(role_list.get(i), alive_players.get(i)));
		}
		GameTargetEngine.setupTargetMap(new ArrayList<Player>(
				player_character_map.keySet()));
		GameVoteEngine.setupVoteMap(new ArrayList<Player>(player_character_map
				.keySet()));
		GameLastWillEngine.setupLastWillMap(new ArrayList<Player>(
				player_character_map.keySet()));
		return true;
	}

	/**
	 * Performs all players night actions
	 * 
	 * @param player_character_map
	 * @param alive_characters
	 * @return true if success, else false
	 */
	private static boolean performNightActions(
			Map<Player, Character> player_character_map,
			List<Player> alive_players) {
		//Perform each player's night actions.
		//Each character performAction returns a String.
		//Append the strings and save it as a game log.
		return true;
	}
}
