package GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GameEngine.GameRegistration.Player;

public class GameVoteEngine {
	
	private static final Map<Player, List<Player>> player_vote_map = new HashMap<Player, List<Player>>();
	
	protected static boolean setupVoteMap(List<Player> player_list) {
		player_vote_map.clear();
		for(Player player : player_list) {
			player_vote_map.put(player, new ArrayList<Player>());
		}
		return true;
	}
	
	protected static String setVote(Player player, Player target) {
		return null;
	}
}
