package Resources;

import java.util.List;

/**
 * The GameMessage class stores all messages used in the game.
 */
public class GameMessage {
	/**
	 * The Registration class stores all messages used during registration.
	 */
	public static class Registration {
		// Successfully registered the player
		public static String REGISTER_SUCCESS(String name) {
			return "Successfully registered " + name + " in the game.";
		}

		// Failed to register the player
		public static String REGISTER_FAIL(String name) {
			return "Cannot register " + name
					+ " in the game. Player may already be registered or invalid name.";
		}

		// Failed to register player because the game has already started
		public static String REGISTER_INGAME(String name) {
			return "Cannot register " + name
					+ " in the game. The game has already started.";
		}

		// Successfully unregistered the player
		public static String UNREGISTER_SUCCESS(String name) {
			return "Successfully unregistered " + name + " from the game.";
		}

		// Failed to unregister the player
		public static String UNREGISTER_FAIL(String name) {
			return "Cannot unregister " + name
					+ " from the game. Player may not have been registered.";
		}

		// Failed to unregister the player because the game has already started
		public static String UNREGISTER_INGAME(String name) {
			return "Cannot register " + name
					+ " from the game. The game has already started.";
		}

		// Successfully unregistered all players
		public static String UNREGISTER_ALL_SUCCESS() {
			// TODO Auto-generated method stub
			return null;
		}

		// Failed to unregister all players
		public static String UNREGISTER_ALL_FAIL() {
			// TODO Auto-generated method stub
			return null;
		}

		// Failed to unregister all players because the game has already started
		public static String UNREGISTER_ALL_INGAME() {
			// TODO Auto-generated method stub
			return null;
		}

		// List all players currently registered
		public static String LIST_ALL_PLAYERS(List<String> allPlayers) {
			StringBuilder result = new StringBuilder();
			result.append("Players currently registered are: ");
			for (int i = 0; i < allPlayers.size(); i++) {
				result.append(allPlayers.get(i));
				if (i + 1 != allPlayers.size()) {
					result.append(", ");
				} else {
					result.append(".");
				}
			}
			return result.toString();
		}

		// Cannot list all players because there are no player registered
		public static String NO_PLAYER_REGISTERED() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	/**
	 * The Management class stores all messages used for managing the game.
	 */
	public static class Management {
		//Successfully created a new game
		public static String NEWGAME_SUCCESS() {
			// TODO Auto-generated method stub
			return null;
		}
		//Failed to create a new game
		public static String NEWGAME_FAIL() {
			// TODO Auto-generated method stub
			return null;
		}
		//Cannot start new game because already in game
		public static String NEWGAME_INGAME() {
			// TODO Auto-generated method stub
			return null;
		}		
	}
	/**
	 * 
	 */
	public static class Lastwill {
		//No message yet
		public static String NO_MESSAGE_YET() {
			// TODO Auto-generated method stub
			return null;
		}

		//Successfully updated last will
		public static String UPDATE_SUCCESS() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}