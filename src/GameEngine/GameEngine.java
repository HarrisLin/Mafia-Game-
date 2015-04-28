package GameEngine;

import Enumerators.Roles;
import Character.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

	/**
	 * @return all characters
	 */
	public static List<Character> getAllCharacters() {
		return new ArrayList<Character>(player_character_map.values());
	}

	// --------------------------------------------------------------------
	// PLAYER REGISTRATION METHODS
	// --------------------------------------------------------------------

	/**
	 * GameEngine.registerPlayer Registers a player into the game.
	 * 
	 * @param name
	 * @return true if successful, false if not.
	 */
	public static String registerPlayer(String name) {
		if (!inGame) {
			if (Player.register(name)) {
				return GameMessage.REGISTERED_PLAYER(name);
			} else {
				return GameMessage.REGISTER_ERROR(name);
			}
		} else {
			return GameMessage.REGISTER_ERROR(name);
		}
	}

	/**
	 * GameEngine.registerPlayer De-registers a player from the game.
	 * 
	 * @param name
	 * @return true if successful, false if not.
	 */
	public static String removePlayer(String name) {
		if (!inGame) {
			if (Player.remove(name)) {
				return GameMessage.REGISTERED_PLAYER(name);
			} else {
				return GameMessage.REMOVE_ERROR(name);
			}
		} else {
			return GameMessage.REMOVE_ERROR(name);
		}
	}

	/**
	 * Resets the GameEngine. Clears all records of players and characters. This
	 * does not clear the database.
	 */
	public static String reset() {
		inGame = false;
		Player.removeAllPlayers();
		player_character_map.clear();
		alive_character.clear();
		dead_character.clear();
		return GameMessage.RESET_GAME();
	}

	/**
	 * Start the game Currently very simple but later can add more.
	 */
	public static String start() {
		if (!inGame) {
			if (assignAllCharacters(0)) {
				mafiaDaily = "The daily has not yet been implemented";
				inGame = true;
				return GameMessage.NEW_GAME();
			} else {
				return GameMessage.ERROR_GENERAL();
			}
		} else {
			return GameMessage.ERROR_GENERAL();
		}
	}

	/**
	 * starts a new day
	 * 
	 * @return message
	 */
	public static String newDay() {
		if (inGame) {
			lynch();
			StringBuilder log = new StringBuilder();
			performNightActions();
			for (Character character : player_character_map.values()) {
				log.append(character.newDay());
			}
			return log.toString() + "not yet implemnted";
		} else {
			return GameMessage.NOT_IN_GAME_ERROR();
		}
	}

	/**
	 * If the game crashes, then it will reboot from the database using this
	 * method.
	 */
	public static String reboot() {
		inGame = true;
		return "not yet implemented";
	}

	// -------------------------------------------------------------
	// GAME MECHANICS AND ACTION METHODS (PUBLIC)
	// -------------------------------------------------------------

	// -----------------------------------------------
	// Get Player Role/Get Player Chracter map
	// ----------------------------------------------
	public static String getRole(String name) {
		if (inGame) {
			Player player = null;
			try {
				player = Player.get(name);
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_PLAYER_NOT_IN_GAME(name);
			}
			try {
				return GameMessage.SHOW_ROLE(getCharacter(player)
						.getRoleString());
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_GENERAL();
			}
		} else {
			return GameMessage.NOT_IN_GAME_ERROR();
		}
	}

	// --------------------------------------
	// Night Action Target Methods
	// -------------------------------------
	/**
	 * GameEngine.setTarget
	 * 
	 * @param player
	 *            The player performing the action
	 * @param targets
	 *            The target(s) of the action
	 * @return true if target was successfully set, false otherwise
	 */
	public static boolean setTarget(Player player, List<Player> target) {
		return player_character_map.get(player).setTarget(target);
	}

	public static String setTarget(String name, List<String> targetList) {
		if (inGame) {
			Player player = null;
			List<Player> target = new ArrayList<Player>();
			try {
				player = Player.get(name);
				for (String targetName : targetList) {
					target.add(Player.get(targetName));
				}
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_PLAYER_NOT_IN_GAME(name, "target");
			}
			try {
				if (getCharacter(player).setTarget(target)) {
					return GameMessage.UPDATED_TARGETS();
				} else {
					return GameMessage.ERROR_PLAYER_IS_DEAD("target");
				}
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_GENERAL();
			}
		} else {
			return GameMessage.NOT_IN_GAME_ERROR();
		}
	}

	public static String getTarget(String name) {
		if (inGame) {
			Player player = null;
			try {
				player = Player.get(name);
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_PLAYER_NOT_IN_GAME(name);
			}
			try {
				return GameMessage.SHOW_TARGETS(getCharacter(player)
						.getTarget());
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_GENERAL();
			}
		} else {
			return GameMessage.NOT_IN_GAME_ERROR();
		}
	}

	// ------------------------------------
	// Vote Methods
	// -------------------------------
	public static boolean setVote(Player player, List<Player> target) {
		return player_character_map.get(player).setVote(target);
	}

	public static String setVote(String name, String targetName) {
		if (inGame) {
			Player player = null;
			Player target = null;
			try {
				player = Player.get(name);
				target = Player.get(targetName);
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_PLAYER_NOT_IN_GAME(name, targetName);
			}
			try {
				if (getCharacter(player).setVote(target)) {
					return GameMessage.UPDATED_VOTES();
				} else {
					return GameMessage.ERROR_PLAYER_IS_DEAD(targetName);
				}
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_GENERAL();
			}
		} else {
			return GameMessage.NOT_IN_GAME_ERROR();
		}
	}

	public static String getVote(String name) {
		if (inGame) {
			Player player = null;
			try {
				player = Player.get(name);
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_PLAYER_NOT_IN_GAME(name);
			}
			try {
				return GameMessage.SHOW_VOTES(getCharacter(player).getVote());
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_GENERAL();
			}
		} else {
			return GameMessage.NOT_IN_GAME_ERROR();
		}
	}

	// ----------------------------------------
	// Last Will Methods
	// -------------------------------------
	public static boolean setLastWill(Player player, String lastWill) {
		return player_character_map.get(player).setLastWill(lastWill);
	}

	public static String setLastWill(String name, String lastWill) {
		if (inGame) {
			Player player = null;
			try {
				player = Player.get(name);
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_PLAYER_NOT_IN_GAME(name);
			}
			try {
				if (getCharacter(player).setLastWill(lastWill)) {
					return GameMessage.UPDATED_LAST_WILL();
				} else {
					return GameMessage.ERROR_PLAYER_IS_DEAD(name);
				}
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_GENERAL();
			}
		} else {
			return GameMessage.NOT_IN_GAME_ERROR();
		}
	}

	public static String getLastWill(Player player) {
		return player_character_map.get(player).getLastWill();
	}

	public static String getLastWill(String name) {
		if (inGame) {
			Player player = null;
			try {
				player = Player.get(name);
			} catch (CannotGetPlayerException e) {
				e.printStackTrace();
				return GameMessage.ERROR_PLAYER_NOT_IN_GAME(name);
			}
			try {
				return GameMessage.SHOW_LAST_WILL(getCharacter(player)
						.getLastWill());
			} catch (CannotGetPlayerException e) {
				return GameMessage.ERROR_GENERAL();
			}
		} else {
			return GameMessage.NOT_IN_GAME_ERROR();
		}
	}

	// --------------------------------------
	// Get status of game
	// -------------------------------------
	public static String getStatus(int type) {
		StringBuilder status = new StringBuilder();
		status.append(GameMessage.FORMAT_STATUS(inGame));
		if (inGame && type == 2) {
			status.append("\n");
			status.append(GameMessage.LIST_ROLE_MAP());
		}
		return status.toString();
	}

	public static String getDaily() {
		return GameMessage.FORMAT_DAILY(mafiaDaily);
	}

	// ---------------------------------------
	// Get Other List Methods
	// --------------------------------------------
	public static String getAliveList() {
		if (inGame) {
			return GameMessage.LIST_ALIVE_PLAYERS();
		} else {
			return GameMessage.NOT_IN_GAME_ERROR();
		}
	}

	public static String getDeadList() {
		if (inGame) {
			return GameMessage.LIST_DEAD_PLAYERS();
		} else {
			return GameMessage.NOT_IN_GAME_ERROR();
		}
	}

	public static String getVoteList() {
		if (inGame) {
			return GameMessage.LIST_VOTE_MAP();
		} else {
			return GameMessage.NOT_IN_GAME_ERROR();
		}
	}

	// **********************************************************************************
	// **********************************************************************************
	// IN THIS SECTION ARE METHODS NOT USED BY CLI AND SHOULD BE KEPT TO LOWEST
	// VISIBLITY
	// ********************************************************************************
	// ********************************************************************************

	// --------------------------------------------------------------------
	// GAME MECHANICS AND ACTION METHODS (PRIVATE/PROTECTED) OR SHOULD BE
	// ---------------------------------------------------------------------
	/**
	 * @return TRUE if successfully killed and FALSE if not
	 */
	protected static boolean killCharacter(Character character) {
		if (!dead_character.contains(character)
				|| alive_character.contains(character)) {
			dead_character.add(character);
			alive_character.remove(character);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * GameEngine.performNightActions Makes each character perform his/her night
	 * action
	 * 
	 * @return summary of all the deaths, last wills, etc.
	 */
	public static String performNightActions() {
		// If we sort the list when the game starts then we probably won't need
		// to do this search crazy method
		
		return null;
	}

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
			throw new CannotGetPlayerException("Player not in map.");
		}
	}

	// -------------------------------------------------------------
	// GAME SETUP METHODS
	// -------------------------------------------------------------
	/**
	 * GameEngine.assignCharacters Randomly assigns a character role to all
	 * players.
	 * 
	 * @param type
	 * @return TRUE if characters successfully assigned and FALSE it not
	 */
	public static boolean assignAllCharacters(int type) {
		List<Player> allPlayers = Player.listAllPlayers();

		Collections.shuffle(allPlayers);

		FileInputStream jsonStream;
		try {
			jsonStream = new FileInputStream("gamegenerator/"
					+ allPlayers.size() + ".json");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		try {
			BufferedReader jsonReader = new BufferedReader(
					new InputStreamReader(jsonStream));

			String jsonLine = jsonReader.readLine();

			jsonReader.close();
			jsonStream.close();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonLine);
			jsonObject = (JSONObject) jsonObject.get(allPlayers.size() + "."
					+ type);

			long id = (long) jsonObject.get("id");
			System.out.println("Id of game type: " + id);
			long size = (long) jsonObject.get("size");
			System.out.println("Number of players: " + size);
			String name = (String) jsonObject.get("name");
			System.out.println("Name of the game type: " + name);
			JSONArray characters = (JSONArray) jsonObject.get("characters");

			for (int i = 0; i < characters.size(); i++) {
				String character = (String) characters.get(i);

				Roles role = Roles.fromString(character);

				switch (role) {
				case Administrator:
					// assignCharacter(allPlayers.get(i), new
					// Administrator(allPlayers.get(i)));
					break;
				case Agent:
					break;
				case Amnesiac:
					break;
				case Arsonist:
					assignCharacter(allPlayers.get(i),
							new Arsonist(allPlayers.get(i)));
					break;
				case Auditor:
					break;
				case Beguiler:
					break;
				case Blackmailer:
					break;
				case Bodyguard:
					assignCharacter(allPlayers.get(i),
							new Bodyguard(allPlayers.get(i)));
					break;
				case BusDriver:
					assignCharacter(allPlayers.get(i),
							new BusDriver(allPlayers.get(i)));
					break;
				case Citizen:
					break;
				case Consigliere:
					assignCharacter(allPlayers.get(i), new Consigliere(
							allPlayers.get(i)));
					break;
				case Consort:
					assignCharacter(allPlayers.get(i),
							new Consort(allPlayers.get(i)));
					break;
				case Coroner:
					break;
				case Crier:
					break;
				case Cultist:
					break;
				case Deceiver:
					break;
				case Detective:
					assignCharacter(allPlayers.get(i),
							new Detective(allPlayers.get(i)));
					break;
				case Disguiser:
					break;
				case Doctor:
					assignCharacter(allPlayers.get(i),
							new Doctor(allPlayers.get(i)));
					break;
				case DragonHead:
					break;
				case Enforcer:
					break;
				case Escort:
					assignCharacter(allPlayers.get(i),
							new Escort(allPlayers.get(i)));
					break;
				case Executioner:
					break;
				case Forger:
					break;
				case Framer:
					break;
				case Godfather:
					assignCharacter(allPlayers.get(i),
							new Godfather(allPlayers.get(i)));
					break;
				case IncenseMaster:
					break;
				case Informant:
					break;
				case Interrogator:
					break;
				case Investigator:
					assignCharacter(allPlayers.get(i), new Investigator(
							allPlayers.get(i)));
					break;
				case Jailor:
					break;
				case Janitor:
					break;
				case Jester:
					break;
				case Judge:
					break;
				case Kidnapper:
					break;
				case Liaison:
					break;
				case Lookout:
					assignCharacter(allPlayers.get(i),
							new Lookout(allPlayers.get(i)));
					break;
				case Mafioso:
					assignCharacter(allPlayers.get(i),
							new Mafioso(allPlayers.get(i)));
					break;
				case Marshall:
					break;
				case Mason:
					break;
				case MasonLeader:
					break;
				case MassMurderer:
					assignCharacter(allPlayers.get(i), new MassMurderer(
							allPlayers.get(i)));
					break;
				case Mayor:
					assignCharacter(allPlayers.get(i),
							new Mayor(allPlayers.get(i)));
					break;
				case SerialKiller:
					assignCharacter(allPlayers.get(i), new SerialKiller(
							allPlayers.get(i)));
					break;
				case Sheriff:
					assignCharacter(allPlayers.get(i),
							new Sheriff(allPlayers.get(i)));
					break;
				case Silencer:
					break;
				case Spy:
					break;
				case Stump:
					break;
				case Survivor:
					break;
				case Vanguard:
					break;
				case Veteran:
					assignCharacter(allPlayers.get(i),
							new Veteran(allPlayers.get(i)));
					break;
				case Vigilante:
					assignCharacter(allPlayers.get(i),
							new Vigilante(allPlayers.get(i)));
					break;
				case Witch:
					assignCharacter(allPlayers.get(i),
							new Witch(allPlayers.get(i)));
					break;
				case WitchDoctor:
					break;
				default:
					break;

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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

	// -------------------------------------------------------------
	// GAME MECHANICS AND ACTION METHODS (PUBLIC)
	// -------------------------------------------------------------
	/**
	 * GameEngine.setTarget
	 * 
	 * @param player
	 *            The player performing the action
	 * @param targets
	 *            The target(s) of the action
	 * @return true if target was successfully set, false otherwise
	 */
	public static boolean setTarget(Player player, List<Player> target) {
		return player_character_map.get(player).setTarget(target);
	}

	// --------------------------------------------------------------------
	// GAME MECHANICS AND ACTION METHODS (PRIVATE/PROTECTED) OR SHOULD BE
	// ---------------------------------------------------------------------
	/**
	 * @return TRUE if successfully killed and FALSE if not
	 */
	protected static boolean killCharacter(Character character) {
		if (!dead_player.contains(character)
				|| alive_player.contains(character)) {
			dead_player.add(character);
			alive_player.remove(character);
			return true;
		} else {
			return false;
		}
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
	protected static boolean lynch() {
		Character topLynch = null;
		int topVote = 0;
		boolean good = false;
		for (Character character : alive_player) {
			if (character.getLynchCount() > topVote) {
				good = true;
				topLynch = character;
				topVote = character.getLynchCount();
			} else if (character.getLynchCount() == topVote) {
				good = false;
			}
		}
		if (good == true) {
			topLynch.lynch();
			return good;
		} else {
			return good;
		}
	}

	// -------------------------------------------------------
	// DATABASE METHODS
	// ----------------------------------------------------------
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

	// ------------------------------------------------------------
	// PUBLIC METHODS WHEN GAME STARTS
	// -----------------------------------------------------------
	/**
	 * Resets the GameEngine. Clears all records of players and characters. This
	 * does not clear the database.
	 */
	public static String reset() {
		Player.removeAllPlayers();
		player_character_map.clear();
		alive_player.clear();
		dead_player.clear();
		return GameMessage.RESET_GAME();
	}

	/**
	 * Start the game
	 */
	public static String start() {
		return "not yet implemented";
	}

	/**
	 * starts a new day
	 * 
	 * @return message
	 */
	public static String newDay() {
		return "not yet implemnted";
	}

	/**
	 * If the game crashes, then it will reboot from the database using this
	 * method.
	 */
	public static String reboot() {
		return "not yet implemented";
	}
}
