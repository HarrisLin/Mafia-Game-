package GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GameEngine.GameRegistration.Player;

public class GameTargetEngine {

	private static final Map<Player, List<Player>> player_target_map = new HashMap<Player, List<Player>>();
	
	protected static boolean setupTargetMap(List<Player> player_list) {
		player_target_map.clear();
		for(Player player : player_list) {
			player_target_map.put(player, new ArrayList<Player>());
		}
		return true;
	}
	
	protected static String setTarget(Player player, Player target) {
		return null;
	}

	protected static String setTarget(Player player, Player target1, Player target2) {
		// TODO Auto-generated method stub
		return null;
	}
}
