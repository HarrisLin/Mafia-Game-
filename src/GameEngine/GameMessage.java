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

	public static final String INVESTIGATION_EXACT(String name) {
		return "The result of your investigation yeilded a role of " + name
				+ ".";
	}

	public static String INVESTIGATION_VAGUE(String name) {
		return "The result of your investigation suggests a role guilty of "
				+ name + ".";
	}

	public static String ARSONIST_KILL(List<Player> victims) {
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
}
