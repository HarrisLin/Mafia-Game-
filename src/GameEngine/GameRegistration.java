package GameEngine;

import java.util.ArrayList;
import java.util.List;

import Resources.GameMessage;

/**
 * The Registration class encapsulates the abstraction for registration and
 * outputs game message strings for the user.
 */
public class GameRegistration {
	/**
	 * Registers a player in the game
	 * 
	 * @param in game status
	 * @param name of player
	 * @return appropriate game message string
	 */
	protected static String registerPlayer(String name) {
		if (PlayerValidation.register(name)) {
			return GameMessage.Registration.REGISTER_SUCCESS(name);
		} else {
			return GameMessage.Registration.REGISTER_FAIL(name);
		}
	}

	/**
	 * Unregisters a player from the game
	 * 
	 * @param name of player
	 * @return appropriate game message string
	 */
	protected static String unregisterPlayer(String name) {
		if (PlayerValidation.unregister(name)) {
			return GameMessage.Registration.UNREGISTER_SUCCESS(name);
		} else {
			return GameMessage.Registration.UNREGISTER_FAIL(name);
		}
	}

	/**
	 * Unregister all players from the game
	 * 
	 * @return appropriate game message string
	 */
	protected static String unregisterAll() {
		if (PlayerValidation.unregisterAll()) {
			return GameMessage.Registration.UNREGISTER_ALL_SUCCESS();
		} else {
			return GameMessage.Registration.UNREGISTER_ALL_FAIL();
		}
	}

	/**
	 * Lists all players in the game
	 * 
	 * @return appropriate game message string
	 */
	protected static String listAllPlayers() {
		List<String> allPlayers = PlayerValidation.listAll();
		if(!allPlayers.isEmpty()) {
			return GameMessage.Registration.LIST_ALL_PLAYERS(allPlayers);
		} else {
			return GameMessage.Registration.NO_PLAYER_REGISTERED();
		}
	}
	
	protected static List<Player> getAllPlayers() {
		GameRegistration gameRegistration = new GameRegistration();
		List<String> allPlayersString = PlayerValidation.listAll();
		List<Player> allPlayers = new ArrayList<Player>();
		for(String playerString : allPlayersString) {
			allPlayers.add(gameRegistration.new Player(playerString));
		}
		return allPlayers;
	}
	
	/**
	 * The Player class acts as a data type to represent the player
	 */
	public class Player {
		private final String name;
		
		public Player(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
	/**
	 * The PlayerValidation class acts as a data type to manage the registration of human
	 * players in the game.
	 */
	private static class PlayerValidation {

		private static final ArrayList<String> valid_names = new ArrayList<String>();

		/**
		 * Registers a player in the game
		 * 
		 * @param name of player
		 * @return true if successfully registered, else false.
		 */
		private static boolean register(String name) {
			if (!valid_names.contains(name) && !name.contains(" ")) {
				valid_names.add(name);
				return true;
			} else {
				return false;
			}
		}

		/**
		 * Unregisters a player from the game
		 * 
		 * @param name of player
		 * @return true if successfully unregistered, else false.
		 */
		private static boolean unregister(String name) {
			if (valid_names.contains(name)) {
				valid_names.remove(name);
				return true;
			} else {
				return false;
			}
		}

		/**
		 * Unregisters all players from the game
		 * 
		 * @return true if successfully unregistered all, else false.
		 */
		private static boolean unregisterAll() {
			if (!valid_names.isEmpty()) {
				valid_names.clear();
				return true;
			} else {
				return false;
			}
		}

		/**
		 * @return a list of all players in the game
		 */
		private static List<String> listAll() {
			return new ArrayList<String>(valid_names);
		}
	}
}