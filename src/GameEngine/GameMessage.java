package GameEngine;

import java.util.List;

/**
 * A class to store all the game messages the game need to display so instead of
 * having to search everywhere for what the game needs to display, it uses this
 * as a median to maintain easy organized strings/messages to display to user.
 *
 * Also makes changing string messages easier.
 *
 * @author pacified
 *
 */
public class GameMessage {

	/**
	 * A message displayed when character has no targets and no action is
	 * performed
	 */

	private static String gameMessage;
	public static final String NO_ACTION = "No action has been registered.";
	public static final String NO_RESULT = "Cannot get your results.";
	public static final String NO_KILL = "Nobody was killed.";
	public static final String ROLE_BLOCKED = "You were unable to perform your action.";

	public static final String KILL_ATTEMPT(Player player) {
		return "You have attempted to kill " + player.getName() +".";
	}

	public static final String INVESTIGATION_EXACT(String name) {
		return "The result of your investigation yeilded a role of " + name
				+ ".";
	}

	public static String INVESTIGATION_VAGUE(String name) {
		return "The result of your investigation suggests a role guilty of "
				+ name + ".";
	}

	// ------------------------------------------
	// DOCTOR MESSAGE
	// --------------------------------------------
	public static final String DOCTOR_FEEDBACK() {
		return "thanks doc";
	}

	// ----------------------------------------------------
	// VIGILANTE MESSAGES
	// ----------------------------------------------------
	public static final String VIGILANTE_FEEDBACK(int shots) {
		return "You have " + shots + " left.";
	}

	// ------------------------------------------------
	// SHERIFF MESSAGES
	// ------------------------------------------------
	public static final String SHERIFF_FEEDBACK(String result) {
		return "The outcome of your results suggests your target is " + result
				+ ".";
	}

	// -------------------------------------
	// LOOKOUT MESSSAGES
	// --------------------------------------
	public static final String LOOKOUT_FEEDBACK(List<Player> tracker) {
		StringBuilder result = new StringBuilder();
		result.append("Your target was visited by ");
		for (int i = 0; i < tracker.size(); i++) {
			result.append(tracker.get(i).getName());
			if (i + 1 != tracker.size()) {
				result.append(", ");
			}
			else {
				result.append(".");	
			}
		}
		return result.toString();
	}

	// ------------------------------------------
	// ARSONIST MESSAGES
	// -----------------------------------------------
	public static final String ARSONIST_KILL(List<Player> victims) {
		if (victims.size() == 0) {
			return NO_KILL;
		}

		String return_string = "";
		for (int i = 0; i < victims.size(); i++) {
			if (i == 0) {
				return_string += victims.get(i).getName();
			} else if (i == victims.size() - 1) {
				return_string += " and " + victims.get(i).getName();
			} else {
				return_string += ", " + victims.get(i).getName();
			}
		}

		if (victims.size() == 1) {
			return_string += " has been killed.";
		} else {
			return_string += " have been killed.";
		}

		return return_string;
	}

	public static String ARSONIST_DOUSE(Player player) {
		return player.getName() + " has been doused.";
	}

	// -----------------------------------------------------
	// ERROR MESSAGES
	// ----------------------------------------------------
	public static final String NO_CHARACTER(Player player) {
		return "Cannot find player " + player.getName() + " in game engine.";
	}

	public static final String NO_CHARACTER(Player player1, Player player2) {
		return "Cannot find player " + player1.getName() + " or player "
				+ player2.getName() + " in game engine.";
	}

	public static final String TARGET_SIZE2_ERROR() {
		return "Please select two characters for target.";
	}

	// ----------------------------------------------------------
	// GAME ENGINE MESSAGES
	// --------------------------------------------------------
	public static final String REGISTERED_PLAYER(String name) {
		return "Successfully registered " + name + "in the game engine.";
	}

	public static final String REGISTER_ERROR(String name) {
		return "Player " + name + " is already in game engine.";
	}

	public static final String REMOVE_PLAYER(String name) {
		return "Successfully removed " + name + "in the game engine.";
	}

	public static final String REMOVE_ERROR(String name) {
		return "Player " + name + " is not in game engine.";
	}

	public static final String BAD_INPUT() {
		return "Bad input. Learn to type.";
	}

	public static final String RESET_GAME() {
		return "The game has been reset.";
	}

	public static final String HELP_MESSAGE() {
		return "To request help: help\n"
				+ "To add player: addplayer [name]\n"
				+ "To remove player: removeplayer [name]\n"
				+ "To start game: startgame [admin password]\n"
				+ "To start game with specific characters: startgame [admin password] Mayor Godfather etc...\n"
				+ "To reboot game: rebootgame [admin password]\n"
				+ "To reset game: resetgame [admin password]\n"
				+ "To get player list: listplayer\n"
				+ "To get alive player list: listalive\n"
				+ "To vote player for lynch: vote [name]\n"
				+ "To show current vote: showvote\n"
				+ "To show target someone: target [name]\n"
				+ "To update last will: lastwill [message]\n"
				+ "To show mafia daily: daily";
	}
	
	public static final String LIST_ALL_PLAYERS() {
		List<Player> allPlayers = Player.listAllPlayers();
		StringBuilder result = new StringBuilder();
		result.append("People in this round are: ");
		for (int i = 0; i < allPlayers.size(); i++) {
			result.append(allPlayers.get(i).getName());
			if (i + 1 != allPlayers.size()) {
				result.append(", ");
			}
			else {
				result.append(".");	
			}
		}
		return result.toString();
	}
}