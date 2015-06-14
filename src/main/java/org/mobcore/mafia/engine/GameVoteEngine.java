package org.mobcore.mafia.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mobcore.mafia.resouces.GameLog;

/**
 * Game Engine's component for storing votes
 */
public class GameVoteEngine {
	
	private static final Map<GameRegistration.Player, GameRegistration.Player> player_vote_map = new HashMap<GameRegistration.Player, GameRegistration.Player>();
	private static List<GameRegistration.Player> mayor_list = new ArrayList<GameRegistration.Player>();
	
	/**
	 * First setup
	 * 
	 * @param player_list
	 * @return true
	 */
	protected static boolean setupVoteMap(List<GameRegistration.Player> player_list) {
		player_vote_map.clear();
		for(GameRegistration.Player player : player_list) {
			player_vote_map.put(player, null);
		}
		return true;
	}
	
	/**
	 * @return player vote map
	 */
	protected static Map<GameRegistration.Player, GameRegistration.Player> getVoteMap() {
		return player_vote_map;
	}
	
	/**
	 * Add a mayor
	 * 
	 * @param player
	 * @return true
	 */
	protected static boolean addMayor(GameRegistration.Player player) {
		mayor_list.add(player);
		return true;
	}
	
	/**
	 * @return list of mayor(s)
	 */
	protected static List<GameRegistration.Player> getMayors() {
		return mayor_list;
	}
	
	/**
	 * Set vote
	 * 
	 * @param player
	 * @param target
	 * @return appropriate game message string
	 */
	protected static String setVote(GameRegistration.Player player, GameRegistration.Player target) {
		if(player_vote_map.put(player, target) != null) {
			return GameLog.Inputs.VOTE_SUCCESS(player, target);
		} else {
			return GameLog.Inputs.VOTE_FAIL(player, target);
		}
	}
}
