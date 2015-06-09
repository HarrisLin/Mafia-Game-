package GameEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Character.Character;
import Character.CharacterFactory;
import Character.CharacterFactory.Roles;
import Character.Mayor;
import GameEngine.GameRegistration.Player;
import Resources.GameMessage;

/**
 * Game Engine's component for game management
 */
public class GameManagement {

	/**
	 * Starts a new game
	 * 
	 * @param in_game
	 * @param player_character_map
	 * @param alive_characters
	 * @return appropriate game message string
	 */
	protected static String startGame(List<Roles> role_list,
			Map<Player, Character> player_character_map,
			List<Player> alive_players) {
		if (assignAllCharacters(role_list, player_character_map, alive_players)) {
			return GameMessage.Management.NEWGAME_SUCCESS();
		} else {
			return GameMessage.Management.NEWGAME_FAIL();
		}
	}

	protected static String startGame(List<Roles> role_list,
			Map<Player, Character> player_character_map,
			List<Player> alive_players, int option) {
		if (assignAllCharacters(role_list, player_character_map, alive_players, option)) {
			return GameMessage.Management.NEWGAME_SUCCESS();
		} else {
			return GameMessage.Management.NEWGAME_FAIL();
		}
	}

	/**
	 * Starts a new day
	 * 
	 * @return appropriate game message string
	 */
	protected static String startNewDay(
			Map<Player, Character> player_character_map,
			List<Player> alive_players) {
		if (performNightActions(player_character_map, alive_players)) {
			return GameMessage.Management.NIGHTACTION_SUCCESS();
		} else {
			return GameMessage.Management.NIGHTACTION_FAIL();
		}
	}

	/**
	 * Starts the lynch
	 * 
	 * @return appropriate game message string
	 */
	protected static String startLynch(
			Map<Player, Character> player_character_map,
			List<Player> alive_players) {
		if (countLynchVote(player_character_map, alive_players)) {
			return GameMessage.Management.LYNCH_SUCCESS();
		} else {
			return GameMessage.Management.LYNCH_FAIL();
		}
	}

	/**
	 * Assigns all players to a character
	 * 
	 * @param player_character_map
	 * @param alive_characters
	 * @return true if success, else false
	 */
	private static boolean assignAllCharacters(List<Roles> role_list,
			Map<Player, Character> player_character_map,
			List<Player> alive_players) {
		alive_players.clear();
		player_character_map.clear();
		alive_players.addAll(GameRegistration.getAllPlayers());
		Collections.shuffle(role_list);
		for (int i = 0; i < alive_players.size(); i++) {
			player_character_map.put(alive_players.get(i), CharacterFactory
					.makeCharacter(role_list.get(i), alive_players.get(i)));
		}
		GameTargetEngine.setupTargetMap(new ArrayList<Player>(
				player_character_map.keySet()));
		GameVoteEngine.setupVoteMap(new ArrayList<Player>(player_character_map
				.keySet()));
		GameLastWillEngine.setupLastWillMap(new ArrayList<Player>(
				player_character_map.keySet()));
		return true;
	}

	private static boolean assignAllCharacters(List<Roles> role_list,
			Map<Player, Character> player_character_map,
			List<Player> alive_players, int option) {
		alive_players.clear();
		player_character_map.clear();
		alive_players.addAll(GameRegistration.getAllPlayers());
		if (option != 1) {
			Collections.shuffle(role_list);
		}
		for (int i = 0; i < alive_players.size(); i++) {
			player_character_map.put(alive_players.get(i), CharacterFactory
					.makeCharacter(role_list.get(i), alive_players.get(i)));
		}
		GameTargetEngine.setupTargetMap(new ArrayList<Player>(
				player_character_map.keySet()));
		GameVoteEngine.setupVoteMap(new ArrayList<Player>(player_character_map
				.keySet()));
		GameLastWillEngine.setupLastWillMap(new ArrayList<Player>(
				player_character_map.keySet()));
		return true;
	}

	/**
	 * Performs all players night actions
	 * 
	 * @param player_character_map
	 * @param alive_characters
	 * @return true if success, else false
	 */
	private static boolean performNightActions(
			Map<Player, Character> player_character_map,
			List<Player> alive_players) {
		List<Player> all_players = new ArrayList<Player>(alive_players);
		Map<Player, List<Player>> target_map = GameTargetEngine.getTargetMap();
		for (Player player : all_players) {
			Character character = player_character_map.get(player);
			if (character.getRole().equals(Roles.Doctor)) {
				if (!target_map.get(player).isEmpty()) {
					character.performAction(player_character_map.get(target_map
							.get(player).get(0)));
				}
			}
		}
		for (Player player : all_players) {
			Character character = player_character_map.get(player);
			if (character.getRole().equals(Roles.Vigilante)) {
				if (!target_map.get(player).isEmpty()) {
					character.performAction(alive_players, player_character_map
							.get(target_map.get(player).get(0)));
				}
			}
		}
		// Perform each player's night actions.
		// Each character performAction returns a String.
		// Append the strings and save it as a game log.
		return true;
	}

	/**
	 * Count the lynch vote and lynches highest vote
	 * 
	 * @param player_character_map
	 * @param alive_players
	 * @return true if success, else false
	 */
	private static boolean countLynchVote(
			Map<Player, Character> player_character_map,
			List<Player> alive_players) {
		Map<Player, Integer> vote_count = new HashMap<Player, Integer>();
		Map<Player, Player> vote_map = GameVoteEngine.getVoteMap();
		List<Player> mayor_list = GameVoteEngine.getMayors();
		for (Player player : alive_players) {
			if (!mayor_list.contains(player)) {
				if (vote_map.get(player) != null) {
					if (vote_count.containsKey(vote_map.get(player))) {
						vote_count.put(vote_map.get(player),
								vote_count.get(vote_map.get(player)) + 1);
					} else {
						vote_count.put(vote_map.get(player), 1);
					}
				}
			} else {
				if (((Mayor) player_character_map.get(player)).isRevealed()) {
					if (vote_map.get(player) != null) {
						if (vote_count.containsKey(vote_map.get(player))) {
							vote_count.put(vote_map.get(player),
									vote_count.get(vote_map.get(player)) + 3);
						} else {
							vote_count.put(vote_map.get(player), 3);
						}
					}
				} else {
					if (vote_map.get(player) != null) {
						if (vote_count.containsKey(vote_map.get(player))) {
							vote_count.put(vote_map.get(player),
									vote_count.get(vote_map.get(player)) + 1);
						} else {
							vote_count.put(vote_map.get(player), 1);
						}
					}
				}
			}
		}
		Player most_vote_player = null;
		int count = 0;
		for (Player player : vote_count.keySet()) {
			if (count < vote_count.get(player)) {
				most_vote_player = player;
				count = vote_count.get(player);
			}
		}
		alive_players.remove(most_vote_player);
		return true;
	}
}
