package GameEngine;

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

	public static String noAction() {
		gameMessage = "No action has been registered.";
		return gameMessage;
	}

	/**
	 * A Message displayed when investigator/sheriff did not perform an action
	 * 
	 * @return
	 */
	public static String noResults() {
		gameMessage = "Cannot get your results.";
		return gameMessage;
	}

	public static String detectExactRole(Character character) {
		gameMessage = "The result of your investigation yeilded a role of "
				+ character.getRoleString() + ".";
		return gameMessage;
	}

	public static String detectVagueRole(Character character) {
		gameMessage = "The result of your investigation suggests a role guilty of "
				+ character.getInvestigation() + ".";
		return gameMessage;
	}
}
