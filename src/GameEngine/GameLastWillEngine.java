package GameEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GameEngine.GameRegistration.Player;
import Resources.GameMessage;
/**
 * Game Engine's component for storing last wills
 */
public class GameLastWillEngine {
	private static final Map<Player, String> player_lastwill_map = new HashMap<Player, String>();
	
	/**
	 * First setup
	 * 
	 * @param player_list
	 * @return true
	 */
	protected static boolean setupLastWillMap(List<Player> player_list) {
		player_lastwill_map.clear();
		for(Player player : player_list) {
			player_lastwill_map.put(player, GameMessage.Inputs.LASTWILL_EMPTY());
		}
		return true;
	}
	
	/**
	 * Update last will
	 * 
	 * @param player
	 * @param lastwill
	 * @return appropriate game message string
	 */
	protected static String updateLastWill(Player player, String lastwill) {
		player_lastwill_map.put(player, lastwill);
		return GameMessage.Inputs.LASTWILL_SUCCESS();
	}
	/**
	 * @param player
	 * @return player's last will
	 */
	protected static String getLastWill(Player player) {
		return player_lastwill_map.get(player);
	}
}
