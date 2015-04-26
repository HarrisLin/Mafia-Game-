package GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GameEngine The game engine is responsible for keeping track of everything
 * that happens in the game. This will include: - Keeping a map of all Players
 * and their Character role - Assigning roles to all Players - Acting as an
 * interface to allow human players to set targets for their actions - Invoking
 * the actions of all Characters when it is night-time
 */
public class GameEngine {
	// -------------------------------------------------------------
	// GAME ENGINE INFORMATION
	// -------------------------------------------------------------
	private static Map<Player, Character> player_character_map = new HashMap<Player, Character>();
	// List of players still alive, used for setTarget() method in Character
	// Class
	private static List<Character> alive_player = new ArrayList<Character>();
	private static List<Character> dead_player = new ArrayList<Character>();

	// ---------------------------------------------------------
	// GET INFORMATION METHODS
	// ---------------------------------------------------------
	/**
	 * This method returns the list of dead players
	 * 
	 * @return dead_player - List of Dead Players
	 */
	public static List<Character> getDeadPlayer() {
		return new ArrayList<Character>(dead_player);
	}

	/**
	 * @return list of all the character
	 */
	public static List<Character> getAlivePlayer() {
		return new ArrayList<Character>(alive_player);
	}

	//--------------------------------------------------------------------
	// PLAYER REGISTRATION METHODS
	//--------------------------------------------------------------------
	/**
	 * GameEngine.registerPlayer Registers a player into the game.
	 * 
	 * @param name
	 * @return true if successful, false if not.
	 */
	public static boolean registerPlayer(String name) {
		return Player.register(name);
	}

	/**
	 * GameEngine.registerPlayer De-registers a player from the game.
	 * 
	 * @param name
	 * @return true if successful, false if not.
	 */
	public static boolean removePlayer(String name) {
		return Player.remove(name);
	}

	/**
	 * GameEngine.getCharacter
	 * 
	 * @param player
	 *            The human name of the player
	 * @return Gets the character role of the player
	 * @throws CannotGetPlayerException
	 */
	public static Character getCharacter(Player player)
			throws CannotGetPlayerException {
		if (player_character_map.containsKey(player)) {
			return player_character_map.get(player);
		} else {
			throw new CannotGetPlayerException("Player not in map");
		}
	}
	
	//-------------------------------------------------------------
	// GAME SETUP METHODS
	//-------------------------------------------------------------
	/**
	 * GameEngine.assignCharacters Randomly assigns a character role to all
	 * players
	 * 
	 * @return TRUE if characters successfully assigned and FALSE it not
	 */
	public static boolean assignAllCharacters() {
		// TODO: implement me!
		return true;
	}

	/**
	 * GameEngine.assignCharacter Assigns a single character role to a single
	 * player
	 * 
	 * @return TRUE if characters successfully assigned and FALSE it not
	 */
	public static boolean assignCharacter(Player player, Character character) {
		if (!Player.listAllPlayers().contains(player) || player == null
				|| character == null) {
			return false;
		}
		player_character_map.put(player, character);
		alive_player.add(character);
		return true;
	}
	
	//-------------------------------------------------------------
	// GAME MECHANICS AND ACTION METHODS
	//-------------------------------------------------------------
	/**
	 * @return TRUE if successfully killed and FALSE if not
	 */
	public static boolean killCharacter(Character character) {
		if(!dead_player.contains(character) || alive_player.contains(character)) {
			dead_player.add(character);
			alive_player.remove(character);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * GameEngine.setTarget
	 * 
	 * @param player
	 *            The player performing the action
	 * @param targets
	 *            The target(s) of the action
	 * @return true if target was successfully set, false otherwise
	 */
	public static boolean setTarget(Player player, List <Player> target) {
		return player_character_map.get(player).setTarget(target);
	}

	/**
	 * GameEngine.performNightActions Makes each character perform his/her night
	 * action
	 * 
	 * @return summary of all the deaths, last wills, etc.
	 */
	public static String performNightActions() {
		// TODO: implement me!
		return null;
	}

	/**
	 * Finds player with most lynch votes and kills them off
	 * 
	 * @return the player lynched
	 */
	public static boolean lynch() {
		
		return true;
	}

	/**
	 * Utility for GameEngine to export all character data to the database
	 * 
	 * @return true on success, false if one or more Characters could not be
	 *         added
	 */
	protected static boolean exportPlayerCharacterMap() {
		boolean return_flag = true;
		for (Character c : player_character_map.values()) {
			if (!DatabaseManager.addData(c)) {
				return_flag = false;
			}
		}
		return return_flag;
	}

	/**
	 * Utility for GameEngine to import all character data to the database. This
	 * clears all records of players and characters in the GameEngine before
	 * importing all of the database data.
	 * 
	 * @return true on success.
	 */
	protected static boolean importPlayerCharacterMap() {
		reset();
		DatabaseManager.importPlayers();
		for (Player p : Player.listAllPlayers()) {
			player_character_map.put(p, DatabaseManager.getData(p));
		}
		return true;
	}

	/**
	 * Resets the GameEngine. Clears all records of players and characters. This
	 * does not clear the database.
	 */
	public static void reset() {
		Player.removeAllPlayers();
		player_character_map.clear();
		alive_player.clear();
		dead_player.clear();
	}
}
