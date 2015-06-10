package GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Character.Character;
import Character.CharacterFactory.Roles;
import GameEngine.GameRegistration.Player;

/**
 * GameEngine The game engine is responsible for keeping track of everything
 * that happens in the game.
 * 
 * @class 1. Game Registration
 * @class 2. Game Management
 * @class 3. Game Inputs
 * @class 4. Game Information
 */
public class GameEngine {

	private static final Map<Player, Character> player_character_map = new HashMap<Player, Character>();
	private static final List<Player> alive_players = new ArrayList<Player>();

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
			return GameRegistration.registerPlayer(name);
		}

		/**
		 * Unregisters a player from the game
		 * 
		 * @param name
		 *            of player
		 * @return appropriate game message string
		 */
		public static String unregisterPlayer(String name) {
			return GameRegistration.unregisterPlayer(name);
		}

		/**
		 * Unregister all players from the game
		 * 
		 * @return appropriate game message string
		 */
		public static String unregisterAll() {
			return GameRegistration.unregisterAll();
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
		public static String startGame(List<Roles> role_list) {
			return GameManagement.startGame(role_list, player_character_map, alive_players);
		}
		
		//For testing purposes. Add option for not shuffling player order.
		public static String startGame(List<Roles> role_list, int option) {
			return GameManagement.startGame(role_list, player_character_map, alive_players, option);
		}

		/**
		 * Delay progress in the game for hours : minutes.
		 * 
		 * @return appropriate game message string
		 */
		public static String delayGame(int hours, int minutes) {
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
			return GameManagement.startNewDay(player_character_map, alive_players);
		}

		/**
		 * Starts the lynch
		 * 
		 * @return appropriate game message string
		 */
		public static String startLynch() {
			return GameManagement.startLynch(player_character_map, alive_players);
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
		/**
		 * Updates player's last will
		 * 
		 * @param player
		 * @param lastwill
		 * @return appropriate game message string
		 */
		public static String updateLastWill(Player player, String lastwill) {
			return GameLastWillEngine.updateLastWill(player, lastwill);
		}
		
		/**
		 * Sets player's vote
		 * 
		 * @param player
		 * @param target
		 * @return appropriate game message string
		 */
		public static String setVote(Player player, Player target) {
			return GameVoteEngine.setVote(player, target);
		}

		/**
		 * Sets player's target
		 * 
		 * @param player
		 * @param target
		 * @return appropriate game message string
		 */
		public static String setTarget(Player player, Player target) {
			return GameTargetEngine.setTarget(player, target);
		}
		
		/**
		 * Sets player's target
		 * 
		 * @param player
		 * @param target1
		 * @param target2
		 * @return appropriate game message string
		 */
		public static String setTarget(Player player, Player target1, Player target2) {
			return GameTargetEngine.setTarget(player, target1, target2);
		}

		public static String revealMayor(Player player) {
			Character character = player_character_map.get(player);
			return GameTargetEngine.revealMayor(player, character);
		}
		/**
		 * Taunt a player
		 * 
		 * @param player
		 * @param target
		 * @return appropriate game message string
		 */
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
		/**
		 * Lists all alive players in the game
		 * 
		 * @return appropriate game message string
		 */
		public static String listAlivePlayers() {
			return GameInformation.listAlivePlayers(alive_players);
		}

		/**
		 * Lists all dead players in the game
		 * 
		 * @return appropriate game message string
		 */
		public static String listDeadPlayers() {
			return null;
		}

		/**
		 * Show last will of the player
		 * 
		 * @param player
		 * @return appropriate game message string
		 */
		public static String showLastWill(Player player) {
			return null;
		}

		/**
		 * Show vote of the player
		 * 
		 * @return appropriate game message string
		 */
		public static String showVote(Player player) {
			return null;
		}

		/**
		 * Show target of the player
		 * 
		 * @return appropriate game message string
		 */
		public static String showTarget(Player player) {
			return null;
		}

		/**
		 * Show players who taunted you
		 * 
		 * @return appropriate game message string
		 */
		public static String showTaunt(Player player) {
			return null;
		}

		/**
		 * Show the daily
		 * 
		 * @return appropriate game message string
		 */
		public static String showDaily() {
			return null;
		}

		/**
		 * Show result of night action
		 * 
		 * @return appropriate game message string
		 */
		public static String showResult(Player player) {
			return null;
		}
		/**
		 * Show the role of the player 
		 * 
		 * @param player
		 * @return appropriate game message string
		 */
		public static String showRole(Player player) {
			return GameInformation.showRole(player_character_map, player);
		}
		
		/**
		 * Show shots left for vigilante or veteran
		 * 
		 * @param player
		 * @return appropriate game message string
		 */
		public static String showShotsLeft(Player player) {
			return null;
		}
		
		/**
		 * Show game status
		 * 
		 * @return appropriate game message string
		 */
		public static String showGameStatus() {
			return null;
		}

		/**
		 * Show administrative version of game status
		 * 
		 * @return appropriate game message string
		 */
		public static String adminGameLog() {
			//This should return full report.
			// - Player to character map.
			// - Currently alive, currently dead
			// - Player targets, votes, for each day.
			// - player last will
			// - Game log.
			// - All the generated dailies.
			return null;
		}
	}
}