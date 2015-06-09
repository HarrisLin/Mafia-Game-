package GameEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GameEngine.GameRegistration.Player;
import Resources.GameMessage;

public class GameVoteEngine {
	
	private static final Map<Player, Player> player_vote_map = new HashMap<Player, Player>();
	
	protected static boolean setupVoteMap(List<Player> player_list) {
		player_vote_map.clear();
		for(Player player : player_list) {
			player_vote_map.put(player, null);
		}
		return true;
	}
	
	protected static String setVote(Player player, Player target) {
		player_vote_map.put(player, target);
		return GameMessage.Inputs.TARGET_SUCCESS();
	}
}
