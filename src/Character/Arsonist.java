package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

/**
 * Arsonist douses and kills
 * 
 * @author pacified
 *
 */
public class Arsonist extends Character {
	Arsonist(Player player) {
		super(Roles.Arsonist, player, true);
	}

	/**
	 * If a target is selected then Arsonist douses that target. If target
	 * selected is oneself then Arsonist ignites. If Arsonist does no action, if
	 * arsonist is doused, he is then undoused.
	 */
	@Override
	public boolean setTarget(List<Player> targets) {
		if (targets.size() > 1 || !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		return setTargets(targets);
	}

	/**
	 * 
	 */
	@Override
	public String doAction() {
		// TODO Auto-generated method stub
		return null;
	}
}
