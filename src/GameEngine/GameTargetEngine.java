package GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Character.Character;
import Character.Mayor;
import GameEngine.GameRegistration.Player;
import Resources.GameLog;

/**
 * Game Engine's component for storing night actions
 */
public class GameTargetEngine {

	private static final Map<Player, List<Player>> player_target_map = new HashMap<Player, List<Player>>();

	/**
	 * First setup
	 * 
	 * @param player_list
	 * @return true
	 */
	protected static boolean setupTargetMap(List<Player> player_list) {
		player_target_map.clear();
		for (Player player : player_list) {
			player_target_map.put(player, new ArrayList<Player>());
		}
		return true;
	}

	/**
	 * @return player target map
	 */
	protected static Map<Player, List<Player>> getTargetMap() {
		return player_target_map;
	}

	/**
	 * Set target
	 * 
	 * @param player
	 * @param target
	 * @return appropriate game engine message
	 */
	protected static String setTarget(Player player, Player target) {
		player_target_map.get(player).clear();
		if (player_target_map.get(player).add(target)) {
			return GameLog.Inputs.TARGET_SUCCESS(player, target);
		} else {
			return GameLog.Inputs.TARGET_FAIL(player, target);
		}
	}

	/**
	 * Set target
	 * 
	 * @param player
	 * @param target1
	 * @param target2
	 * @return appropriate game engine message
	 */
	protected static String setTarget(Player player, Player target1,
			Player target2) {
		player_target_map.get(player).clear();
		if (player_target_map.get(player).add(target1)
				&& player_target_map.get(player).add(target2)) {
			return GameLog.Inputs.TARGET_SUCCESS(player, target1, target2);
		} else {
			return GameLog.Inputs.TARGET_FAIL(player, target1, target2);
		}
	}

	protected static String revealMayor(Player player, Character character) {
		List<Player> mayor_list = GameVoteEngine.getMayors();
		if (mayor_list.contains(player)) {
			((Mayor) character).reveal();
			GameVoteEngine.addRevealedMayor(player);
			return GameLog.Inputs.MAYOR_REVEALED(player);
		} else {
			return GameLog.Inputs.MAYOR_FAIL(player);
		}
	}
}
