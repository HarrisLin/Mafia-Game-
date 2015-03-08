package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.GameMessage;
import GameEngine.Player;

/**
 * Arsonist douses and kills
 * 
 * @author pacified
 *
 */
public class Arsonist extends Character {
	
	private static final int ARSONIST_MAX_NUM_TARGETS = 1;
	
	public Arsonist(Player player) {
		super(Roles.Arsonist, player, true);
	}

	/**
	 * If a target is selected then Arsonist douses that target. If target
	 * selected is oneself then Arsonist ignites. If Arsonist does no action, if
	 * arsonist is doused, he is then undoused.
	 */
	@Override
	public boolean setTarget(List<Player> targets) {
		if (targets.size() > ARSONIST_MAX_NUM_TARGETS || !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		return setTargets(targets);
	}

	/**
	 * 
	 */
	@Override
	public String doAction() {
		if (getTargets().isEmpty()) {
			return GameMessage.NO_ACTION;
		}
		
		Player target = getTargets().get(0);
		if (target.getName().equals(this.getPlayer().getName())) {
			// If arsonist targets himself, he ignites
			ArrayList<Player> victims = new ArrayList<Player>();
			for (Player player : GameEngine.getAlivePlayer()) {
				Character target_character = GameEngine.getCharacter(player);
				if (target_character.isDoused()) {
					if (target_character.kill()) {
						victims.add(player);
					}
				}
			}
			return GameMessage.ARSONIST_KILL(victims);
		} else {
			GameEngine.getCharacter(target).douse();
			return GameMessage.ARSONIST_DOUSE(target);
		}
	}
}
