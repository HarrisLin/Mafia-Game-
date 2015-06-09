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
	public static final String TARGET_DEAD = "Your target was already dead.";
	public static final String NO_FEEDBACK = "You performed your action to the best of your ability.";

	public static final String KILL_ATTEMPT(Player player) {
		return "You have attempted to kill " + player.getName() +".";
	}
	
	// ------------------------------------------
	// CONSORT/ESCORT MESSAGES
	// --------------------------------------------
	public static final String CONSORT_FEEDBACK = "You are blocking your target.";
	public static final String ESCORT_FEEDBACK = "You are blocking your target.";
	
	// ------------------------------------------
	// INVESTIGATOR/CONSIGLIERE MESSAGES
	// --------------------------------------------

	public static final String INVESTIGATION_EXACT(String name) {
		return "The result of your investigation yielded a role of " + name
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
	// BUS DRIVER MESSAGES
	// -----------------------------------------------	
	public static final String BUS_DRIVER_FEEDBACK = "You have transported your passengers to their destinations.";
	
	// ------------------------------------------
	// DETECTIVE MESSAGES
	// -----------------------------------------------	
	public static final String DETECTIVE_FEEDBACK(List<Player> visited) {
		if (visited.isEmpty()) {
			return "Your target did not visit anybody.";
		}
		else if (visited.size() == 1) {
			return "Your target visited " + visited.get(0).getName() + ".";
		}
		else {
			String output_message = "Your target visited ";
			for (int k = 0; k < visited.size() - 1 ; k++) {
				output_message += visited.get(k).getName() + ", ";				
			}
			output_message += " and " + visited.get(visited.size() - 1) + ".";
			return output_message;
		}
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

	public static final String ERROR_PLAYER_NOT_IN_GAME(String player) {
		return "Player " + player + " is not in the game.";
	}

	public static final String ERROR_TARGET_SIZE1() {
		return "Please select one player for target.";
	}

	public static final String ERROR_TARGET_SIZE2() {
		return "Please select two players for target.";
	}

	public static final String ERROR_GENERAL() {
		return "Oops. Something went wrong.";
	}

	public static final String IN_GAME_ERROR() {
		return "Game is already in session. Changes canannot be made.";
	}

	public static final String NOT_IN_GAME_ERROR() {
		return "The game has not started yet. Item cannot be displayed.";
	}

	public static final String BAD_INPUT() {
		return "Bad input. Learn to type.";
	}

	public static final String BAD_ADMIN_KEY() {
		return "Bad Admin Key. No hacks pl0x..";
	}

	public static final String ERROR_PLAYER_IS_DEAD(String name) {
		return name + " is dead. Cannot select " + name + " for target";
	}

	// ----------------------------------------------------------
	// GAME ENGINE MESSAGES
	// ----------------------------------------------------------
	// ------------------------------------------------------------
	// Help Messages
	// -----------------------------------------------------------
	public static final String HELP_MESSAGE() {
		return "To request help: help\n" + "To request admin help: admin\n"
				+ "To add player: addplayer\n"
				+ "To remove player: removeplayer\n"
				+ "To get player list: listplayer\n"
				+ "To get alive player list: listalive\n"
				+ "To get dead player list: listdead\n"
				+ "To vote player for lynch: vote [name]\n"
				+ "To show your vote: showvote\n"
				+ "See all the votes: seevote\n"
				+ "To target someone: target [name]\n"
				+ "To show your target: showtarget\n"
				+ "To update last will: lastwill [message]\n"
				+ "To show last will: showlastwill\n"
				+ "To show mafia daily: showdaily\n"
				+ "To show your character role: showrole\n"
				+ "To show game status: status";
	}

	public static final String HELP_ADMIN() {
		return "To request admin help: admin\n"
				+ "To add player: addplayer [admin key] [name]\n"
				+ "To remove player: removeplayer [admin key] [name]\n"
				+ "To show player role: showrole [admin key] [name]\n"
				+ "To show player last will: showlastwill [admin key] [name]\n"
				+ "To show player target: showtarget [admin key] [name]\n"
				+ "To show player vote: showvote [admin key] [name]\n"
				+ "To show game status: status [admin key]\n"
				+ "To start game: startgame [admin key]\n"
				+ "To start game with specific characters: startgame [admin key] Mayor Godfather etc...\n"
				+ "To start a new day: forcenewday [admin key]"
				+ "To reboot game: rebootgame [admin key]\n"
				+ "To reset game: resetgame [admin key]";
	}

	// --------------------------------------------------------
	public static final String REGISTERED_PLAYER(String name) {
		return "Successfully registered " + name + " in the game engine.";
	}

	public static final String REGISTER_ERROR(String name) {
		return "Player " + name + " is already in game engine.";
	}

	public static final String REMOVE_PLAYER(String name) {
		return "Successfully removed " + name + " in the game engine.";
	}

	public static final String REMOVE_ERROR(String name) {
		return "Player " + name + " is not in game engine.";
	}

	// --------------------------------------------
	// Get status Messages
	// -------------------------------
	public static final String FORMAT_STATUS(boolean inGame) {
		int sizeOfGame = Player.listAllPlayers().size();
		if (inGame) {
			int sizeOfLiving = GameEngine.getAlivePlayer().size();
			int sizeOfDead = GameEngine.getDeadPlayer().size();
			return "Currently in game. " + sizeOfGame + " playing.\n"
					+ sizeOfLiving + " people alive, " + sizeOfDead
					+ " people dead.";
		} else {
			return "Not in game yet. Currently " + sizeOfGame + " registered.";
		}
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

	public static final String LIST_ALIVE_PLAYERS(List<Character> alivePlayers) {
		StringBuilder result = new StringBuilder();
		result.append("People still alive in this round are: ");
		for (int i = 0; i < alivePlayers.size(); i++) {
			result.append(alivePlayers.get(i).getPlayer().getName());
			if (i + 1 != alivePlayers.size()) {
				result.append(", ");
			} else {
				result.append(".");
			}
		}
		return result.toString();
	}

	public static final String LIST_DEAD_PLAYERS(List<Character> deadPlayers) {
		StringBuilder result = new StringBuilder();
		result.append("People dead in this round are: ");
		for (int i = 0; i < deadPlayers.size(); i++) {
			result.append(deadPlayers.get(i).getPlayer().getName());
			if (i + 1 != deadPlayers.size()) {
				result.append(", ");
			} else {
				result.append(".");
			}
		}
		return result.toString();
	}

	public static final String LIST_VOTE_MAP() {
		StringBuilder voteMap = new StringBuilder();
		for (Character character : GameEngine.getAlivePlayer()) {
			voteMap.append(character.getPlayer().getName() + "has"
					+ character.getLynchCount() + "votes\n");
		}
		if (voteMap.length() > 0) {
			   voteMap.setLength(voteMap.length() - 1);
			}
		return voteMap.toString();
	}

	public static final String LIST_ROLE_MAP() {
		StringBuilder roleMap = new StringBuilder();
		for (Character character : GameEngine.getAlivePlayer()) {
			roleMap.append(character.getPlayer().getName() + " is the role "
					+ character.getRoleString() + ".\n");
		}
		if (roleMap.length() > 0) {
			   roleMap.setLength(roleMap.length() - 1);
			}
		return roleMap.toString();
	}

	// ---------------------------------------------
	// Vote, Night Actions, Last Will, Role, messages.
	// ---------------------------------------------

	public static final String UPDATED_LAST_WILL() {
		return "Thank you. Your last will has been updated";
	}

	public static final String SHOW_LAST_WILL(String lastWill) {
		return "Your last will is:" + lastWill;
	}

	public static final String UPDATED_TARGETS() {
		return "Thank you. Your target(s) has been updated";
	}

	public static final String SHOW_TARGETS(List<Player> targetList) {
		StringBuilder result = new StringBuilder();
		result.append("Your target(s) is/are ");
		for (int i = 0; i < targetList.size(); i++) {
			result.append(targetList.get(i).getName());
			if (i + 1 != targetList.size()) {
				result.append(", ");
			} else {
				result.append(".");
			}
		}
		return result.toString();
	}

	public static final String UPDATED_VOTES() {
		return "Thank you. Your vote(s) has been updated";
	}

	public static final String SHOW_VOTES(List<Player> targetList) {
		StringBuilder result = new StringBuilder();
		result.append("Your vote target is ");
		for (int i = 0; i < targetList.size(); i++) {
			result.append(targetList.get(i).getName());
			if (i + 1 != targetList.size()) {
				result.append(", ");
			} else {
				result.append(".");
			}
		}
		return result.toString();
	}

	public static final String SHOW_ROLE(String role) {
		return "Your role is " + role + ".";
	}

	// --------------------------------------------
	// Game Setup Messages
	// -----------------------------------------
	public static final String NEW_GAME() {
		return "Sucessfully assigned all players. Game has started.";
	}

	public static final String RESET_GAME() {
		return "The game has been reset.";
	}
}