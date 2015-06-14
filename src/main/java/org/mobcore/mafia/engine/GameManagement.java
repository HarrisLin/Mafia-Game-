package org.mobcore.mafia.engine;

import org.mobcore.mafia.character.Character;
import org.mobcore.mafia.character.CharacterFactory;
import org.mobcore.mafia.character.Mayor;

import java.util.*;

import org.mobcore.mafia.resouces.GameLog;
import org.mobcore.mafia.engine.GameRegistration.Player;

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
	protected static String startGame(List<CharacterFactory.Roles> role_list,
			Map<Player, Character> player_character_map,
			List<Player> alive_players) {
		if (Management.assignAllCharacters(role_list, player_character_map,
				alive_players)) {
			return GameLog.Management.NEWGAME_SUCCESS();
		} else {
			return GameLog.Management.NEWGAME_FAIL();
		}
	}

	// for testing purposes ***will be removed**
	protected static String startGame(List<CharacterFactory.Roles> role_list,
			Map<Player, Character> player_character_map,
			List<Player> alive_players, int option) {
		if (Management.assignAllCharacters(role_list, player_character_map,
				alive_players, option)) {
			return GameLog.Management.NEWGAME_SUCCESS();
		} else {
			return GameLog.Management.NEWGAME_FAIL();
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
		if (Management.startNewDay(player_character_map, alive_players)) {
			return GameLog.Management.NEWDAY_SUCCESS();
		} else {
			return GameLog.Management.NEWDAY_FAIL();
		}
	}

	/**
	 * Perform night actions
	 * 
	 * @param player_character_map
	 * @param alive_players
	 * @return appropriate game message string
	 */
	protected static String performNightActions(
			Map<Player, Character> player_character_map,
			List<Player> alive_players) {
		if (Management.performNightActions(player_character_map, alive_players)) {
			return GameLog.Management.NIGHTACTION_SUCCESS();
		} else {
			return GameLog.Management.NIGHTACTION_FAIL();
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
		if (Management.countLynchVote(player_character_map, alive_players)) {
			return GameLog.Management.LYNCH_SUCCESS();
		} else {
			return GameLog.Management.LYNCH_FAIL();
		}
	}

	private static class Management {
		/**
		 * Assigns all players to a character
		 * 
		 * @param player_character_map
		 * @param alive_characters
		 * @return true if success, else false
		 */
		private static boolean assignAllCharacters(List<CharacterFactory.Roles> role_list,
				Map<Player, Character> player_character_map,
				List<Player> alive_players) {
			alive_players.clear();
			player_character_map.clear();
			alive_players.addAll(GameRegistration.getAllPlayers());
			Collections.shuffle(role_list);
			for (int i = 0; i < alive_players.size(); i++) {
				player_character_map.put(alive_players.get(i), CharacterFactory
						.makeCharacter(role_list.get(i), alive_players.get(i)));
				if (role_list.get(i).equals(CharacterFactory.Roles.Mayor)) {
					GameVoteEngine.addMayor(alive_players.get(i));
				}
			}
			GameTargetEngine.setupTargetMap(new ArrayList<Player>(
					player_character_map.keySet()));
			GameVoteEngine.setupVoteMap(new ArrayList<Player>(
					player_character_map.keySet()));
			GameLastWillEngine.setupLastWillMap(new ArrayList<Player>(
					player_character_map.keySet()));
			return true;
		}

		// For testing purposes
		private static boolean assignAllCharacters(List<CharacterFactory.Roles> role_list,
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
				if (role_list.get(i).equals(CharacterFactory.Roles.Mayor)) {
					GameVoteEngine.addMayor(alive_players.get(i));
				}
			}
			GameTargetEngine.setupTargetMap(new ArrayList<Player>(
					player_character_map.keySet()));
			GameVoteEngine.setupVoteMap(new ArrayList<Player>(
					player_character_map.keySet()));
			GameLastWillEngine.setupLastWillMap(new ArrayList<Player>(
					player_character_map.keySet()));
			return true;
		}

		private static boolean startNewDay(
				Map<Player, Character> player_character_map,
				List<Player> alive_players) {
			GameTargetEngine.setupTargetMap(new ArrayList<Player>(
					player_character_map.keySet()));
			GameVoteEngine.setupVoteMap(new ArrayList<Player>(
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
			StringBuilder result = new StringBuilder();
			List<Player> all_players = new ArrayList<Player>(alive_players);
			Map<Player, List<Player>> target_map = GameTargetEngine
					.getTargetMap();
			for (Player player : all_players) {
				Character character = player_character_map.get(player);
				switch (character.getRole()) {
				case Doctor:
				case Investigator:
					if (!target_map.get(player).isEmpty()) {
						character.performAction(alive_players,
								player_character_map.get(target_map.get(player)
										.get(0)));
						result.append(character.getResult());
						result.append("\n");
					} else {
						character.updateResult(GameLog.Character
								.NO_ACTION(player));
						result.append(character.getResult());
						result.append("\n");
					}
				default:
					break;
				}
			}
			// Hierarchy of type vigilante/kill characters, etc.
			for (Player player : all_players) {
				Character character = player_character_map.get(player);
				switch (character.getRole()) {
				case Vigilante:
					if (!target_map.get(player).isEmpty()) {
						character.performAction(alive_players,
								player_character_map.get(target_map.get(player)
										.get(0)));
						result.append(character.getResult());
						result.append("\n");
					} else {
						character.updateResult(GameLog.Character
								.NO_ACTION(player));
						result.append(character.getResult());
						result.append("\n");
					}
				default:
					break;
				}
			}
			// Perform each player's night actions.
			// Each character performAction returns a String.
			// Append the strings and save it as a game log.
			// This string does not all send back to user.
			// Clear target map and vote map
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
								vote_count
										.put(vote_map.get(player), vote_count
												.get(vote_map.get(player)) + 3);
							} else {
								vote_count.put(vote_map.get(player), 3);
							}
						}
					} else {
						if (vote_map.get(player) != null) {
							if (vote_count.containsKey(vote_map.get(player))) {
								vote_count
										.put(vote_map.get(player), vote_count
												.get(vote_map.get(player)) + 1);
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
}
