package org.mobcore.mafia.engine;

import org.mobcore.mafia.engine.GameRegistration.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mobcore.mafia.resouces.GameLog;

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
			player_lastwill_map.put(player, GameLog.Inputs.LASTWILL_EMPTY());
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
		if(player_lastwill_map.put(player, lastwill) != null) {
			return GameLog.Inputs.LASTWILL_SUCCESS(player, lastwill);
		} else {
			return GameLog.Inputs.LASTWILL_FAIL(player, lastwill);
		}
	}
}
