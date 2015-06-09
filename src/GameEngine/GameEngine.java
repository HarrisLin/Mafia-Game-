package GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Character.Character;
import GameEngine.GameRegistration.Player;

/**
 * GameEngine The game engine is responsible for keeping track of everything
 * that happens in the game.
 * 
 * @class 1. Game Registration
 * @class 2. Game Management
 * @class 3. Game Inputs
 * @class 4. Game Information
 * @class 5. Game Help
 */
public class GameEngine {

	private static final Map<Player, Character> player_character_map = new HashMap<Player, Character>();
	private static final List<Character> alive_characters = new ArrayList<Character>();
	private static boolean in_game;

	/**
	 * 1. Game Registration
	 * 
	 * Player registration for setting up the game
	 */
	public static class Registration {
		/**
		 * Lists all players in the game
		 * 
		 * @return appropriate game message string
		 */
		public static String listAllPlayers() {
			return GameRegistration.listAllPlayers();
		}

		/**
		 * Registers a player in the game
		 * 
		 * @param name
		 *            of player
		 * @return appropriate game message string
		 */
		public static String registerPlayer(String name) {
			return GameRegistration.registerPlayer(in_game, name);
		}

		/**
		 * Unregisters a player from the game
		 * 
		 * @param name
		 *            of player
		 * @return appropriate game message string
		 */
		public static String unregisterPlayer(String name) {
			return GameRegistration.unregisterPlayer(in_game, name);
		}

		/**
		 * Unregister all players from the game
		 * 
		 * @return appropriate game message string
		 */
		public static String unregisterAll() {
			return GameRegistration.unregisterAll(in_game);
		}
	}

	/**
	 * 2. Game Management
	 * 
	 * Moderator/ management for maintaining the game
	 */
	public static class Management {
		/**
		 * Starts a new game
		 * 
		 * @return appropriate game message string
		 */
		public static String startGame() {
			String message = GameManagement.startGame(in_game, player_character_map, alive_characters);
			if(!in_game) {
				in_game = true;
				GameTargetEngine.setupTargetMap(new ArrayList<Player>(player_character_map.keySet()));
				GameVoteEngine.setupVoteMap(new ArrayList<Player>(player_character_map.keySet()));
			}
			return message;
		}

		/**
		 * Delay progress in the game for hours : minutes.
		 * 
		 * @return appropriate game message string
		 */
		public static String delayNewDay(int hours, int minutes) {
			return null;
		}

		/**
		 * Pauses the game
		 * 
		 * @return appropriate game message string
		 */
		public static String pauseGame() {
			return null;
		}

		/**
		 * Resumes game if paused
		 * 
		 * @return appropriate game message string
		 */
		public static String resumeGame() {
			return null;
		}

		/**
		 * Starts a new day
		 * 
		 * @return appropriate game message string
		 */
		public static String startNewDay() {
			return null;
		}

		/**
		 * Starts the lynch
		 * 
		 * @return appropriate game message string
		 */
		public static String startLynch() {
			return null;
		}

		/**
		 * Forces the game to end
		 * 
		 * @return appropriate game message string
		 */
		public static String forceEndGame() {
			return null;
		}
	}

	/**
	 * 3. Game Inputs
	 * 
	 * Character inputs for performing actions in the game
	 */
	public static class Inputs {
		public static String updateLastWill(Player player, String lastwill) {
			return null;
		}

		public static String setVote(Player player, Player target) {
			return GameVoteEngine.setVote(player, target);
		}

		public static String setTarget(Player player, Player target) {
			return GameTargetEngine.setTarget(player, target);
		}

		public static String setTarget(Player player, Player target1, Player target2) {
			return GameTargetEngine.setTarget(player, target1, target2);
		}

		public static String tauntPlayer(Player player, Player target) {
			return null;
		}
	}

	/**
	 * 4. Game Information
	 * 
	 * User tool for getting current game status
	 */
	public static class Information {
		/**
		 * Lists all players in the game
		 * 
		 * @return appropriate game message string
		 */
		public static String listAllPlayers() {
			return null;
		}

		public static String listAlivePlayers() {
			return null;
		}

		public static String listDeadPlayers() {
			return null;
		}

		public static String showLastWill(String player) {
			return null;
		}

		public static String showVote() {
			return null;
		}

		public static String showTarget() {
			return null;
		}

		public static String showTaunt() {
			return null;
		}

		public static String showDaily() {
			return null;
		}

		public static String showRole(String player) {
			return null;
		}

		public static String showGameStatus() {
			return null;
		}

		public static String adminGameStatus() {
			return null;
		}
	}

	/**
	 * 5. Game Help
	 * 
	 * Easy navigation for game mechanics and rules
	 */
	public static class Help {

	}
}