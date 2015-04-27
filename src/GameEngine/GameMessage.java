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

	// -----------------------------------------------------
	// START GAME MESSAGE
	// -----------------------------------------------------

	// --------------------------------------------------------
	// GENERAL ACTION FEEDBACK MESSAGES
	// --------------------------------------------------------
	public static final String NO_FEEDBACK() {
		return "Your action has been performed. Please see the \"Mafia Daily\" for your results.";
	}

	public static final String NO_ACTION() {
		return "No action has been registered.";
	}

	public static final String NO_RESULT() {
		return "Cannot get your results.";
	}

	public static final String NO_KILL() {
		return "Nobody was killed.";
	}

	public static final String TARGET_DEAD() {
		return "Your target was dead prior to action";
	}

	// -------------------------------------------
	// INVESTIGATOR/CONSIGLIERE MESSAGES
	// ------------------------------------
	public static final String INVESTIGATION_EXACT(Player target)
			throws CannotGetPlayerException {
		return "The result of your investigation yeilded a role of "
				+ GameEngine.getCharacter(target).getRoleString() + ".";
	}

	public static final String INVESTIGATION_VAGUE(Player target)
			throws CannotGetPlayerException {
		return "The result of your investigation suggests a role guilty of "
				+ GameEngine.getCharacter(target).getInvestigation() + ".";
	}

	// ----------------------------------------------
	// ESCORT/CONSORT MESSAGES
	// ---------------------------------------
	public static final String BLOCK_FEEDBACK() {
		return "Thank you for performing your night deeds, you dirty whole.";
	}

	// --------------------------------------------
	// DETECTIVE MESSAGE
	// ----------------------------------------------
	public static final String DETECTIVE_RESULTS(List<Player> tracker) {
		if (tracker.isEmpty()) {
			return "Your target did not visit anyone.";
		} else {
			StringBuffer result = new StringBuffer();
			result.append("Your target visited ");
			for (int i = 0; i < tracker.size(); i++) {
				result.append(tracker.get(i).getName());
				if (i + 1 != tracker.size()) {
					result.append(", ");
				}
				result.append(".");
			}
			return result.toString();
		}
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
		return "The outcome of your results suggests your target is " + result + ".";
	}

	// -------------------------------------
	// LOOKOUT MESSSAGES
	// --------------------------------------
	public static final String LOOKOUT_FEEDBACK(List<Player> tracker) {
		StringBuffer result = new StringBuffer();
		result.append("Your target was visited by ");
		for (int i = 0; i < tracker.size(); i++) {
			result.append(tracker.get(i).getName());
			if (i + 1 == tracker.size()) {
				result.append(".");
				break;
			}
			result.append(", ");
		}
		return result.toString();
	}

	// ------------------------------------------
	// ARSONIST MESSAGES
	// -----------------------------------------------
	public static final String ARSONIST_KILL(List<Player> victims) {
		if (victims.size() == 0) {
			return NO_KILL();
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

	public static final String ARSONIST_DOUSE(Player player) {
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

}
