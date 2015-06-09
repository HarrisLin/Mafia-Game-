package GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GameEngine.GameRegistration.Player;
import Resources.GameMessage;

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
		player_target_map.get(player).clear();
		player_target_map.get(player).add(target);
		return GameMessage.Inputs.TARGET_SUCCESS();
	}

	protected static String setTarget(Player player, Player target1, Player target2) {
		player_target_map.get(player).clear();
		player_target_map.get(player).add(target1);
		player_target_map.get(player).add(target2);
		return GameMessage.Inputs.TARGET_SUCCESS();
	}
}
