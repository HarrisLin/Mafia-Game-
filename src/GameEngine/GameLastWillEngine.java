package GameEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GameEngine.GameRegistration.Player;
import Resources.GameMessage;

public class GameLastWillEngine {
	private static final Map<Player, String> player_lastwill_map = new HashMap<Player, String>();
	
	protected static boolean setupTargetMap(List<Player> player_list) {
		player_lastwill_map.clear();
		for(Player player : player_list) {
			player_lastwill_map.put(player, GameMessage.Lastwill.NO_MESSAGE_YET());
		}
		return true;
	}
	
	protected static String updateLastWill(Player player, Player target) {
		return GameMessage.Lastwill.UPDATE_SUCCESS();
	}
}
