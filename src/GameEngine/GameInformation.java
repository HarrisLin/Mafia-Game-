package GameEngine;

import java.util.List;
import java.util.Map;

import Character.Character;
import GameEngine.GameRegistration.Player;
import Resources.GameLog;

/**
 *  Game Engine's component for information
 */
public class GameInformation {

	protected static String listAlivePlayers(List<Player> alive_players) {
		return GameLog.Information.LIST_ALIVE_PLAYERS(alive_players);
	}

	protected static String showRole(Map<Player, Character> player_character_map,
			Player player) {
		return GameLog.Information.SHOW_ROLE(player_character_map.get(player).getRole());
	}

	protected static String showResult(Map<Player, Character> player_character_map,
			Player player) {
		return GameLog.Information.SHOW_RESULT(player_character_map.get(player).getResult());
	}
}
