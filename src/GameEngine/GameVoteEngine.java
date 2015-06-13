package GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GameEngine.GameRegistration.Player;
import Resources.GameLog;

/**
 * Game Engine's component for storing votes
 */
public class GameVoteEngine {

	private static final Map<Player, Player> player_vote_map = new HashMap<Player, Player>();
	private static List<Player> mayor_revealed_list = new ArrayList<Player>();
	private static List<Player> mayor_list = new ArrayList<Player>();
	private static final Map<Player, Integer> lynchCount = new HashMap<Player, Integer>();

	/**
	 * First setup
	 * 
	 * @param player_list
	 * @return true
	 */
	protected static boolean setupVoteMap(List<Player> player_list) {
		player_vote_map.clear();
		lynchCount.clear();
		for (Player player : player_list) {
			player_vote_map.put(player, null);
			lynchCount.put(player, 0);
		}
		return true;
	}

	/**
	 * @return player vote map
	 */
	protected static Map<Player, Player> getVoteMap() {
		return player_vote_map;
	}
	
	/**
	 * Add a mayor
	 * 
	 * @param player
	 * @return true
	 */
	protected static boolean addMayor(Player player) {
		mayor_list.add(player);
		return true;
	}

	/**
	 * Add a revealed mayor
	 * 
	 * @param player
	 * @return true
	 */
	protected static boolean addRevealedMayor(Player player) {
		mayor_revealed_list.add(player);
		return true;
	}

	/**
	 * @return list of revealed mayor(s)
	 */
	protected static List<Player> getRevealedMayors() {
		return mayor_revealed_list;
	}
	
	/**
	 * @return list of mayor(s)
	 */
	protected static List<Player> getMayors() {
		return mayor_list;
	}

	/**
	 * Set vote
	 * 
	 * @param player
	 * @param target
	 * @return appropriate game message string
	 */
	protected static String setVote(Player player, Player target) {
		if (player_vote_map.put(player, target) != null) {
			return GameLog.Inputs.VOTE_SUCCESS(player, target);
		} else {
			return GameLog.Inputs.VOTE_FAIL(player, target);
		}
	}

	/**
	 * Return Lynch Targets
	 * 
	 * @return returns person to be lynched, more than one person if votes tied
	 */
	protected static ArrayList<GameRegistration.Player> getLynchTargets(
			List<Player> alive_players) {
		ArrayList<GameRegistration.Player> list = new ArrayList<GameRegistration.Player>();

		sumVotes();

		int highestVotes = 0;

		int tempVotes = 0;
		for (GameRegistration.Player player : lynchCount.keySet()) {
			if (alive_players.contains(player)) {
				tempVotes = lynchCount.get(player);
				if (tempVotes > highestVotes) {
					highestVotes = tempVotes;
				}
			}
		}
		for (GameRegistration.Player person : lynchCount.keySet()) {
			if (lynchCount.get(person) == highestVotes) {
				list.add(person);
			}
		}
		
		return list;
	}

	/*
	 * Sums all the votes using lynchCount HashMap
	 */
	private static void sumVotes() {
		for (GameRegistration.Player player : lynchCount.keySet()) {
			lynchCount.put(player, 0);
		}

		for (GameRegistration.Player Key : player_vote_map.keySet()) {
			GameRegistration.Player vote = player_vote_map.get(Key);
			if (vote != null) {
				if (!mayor_revealed_list.contains(vote)) {
					lynchCount.put(vote, lynchCount.get(vote) + 1);
				} else {
					lynchCount.put(vote, lynchCount.get(vote) + 3);
				}
			}
		}
	}
	
}
