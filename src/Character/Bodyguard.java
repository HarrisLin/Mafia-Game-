package Character;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

/**
 * 
 * @author pacified
 *
 */
public class Bodyguard extends Character {

	public Bodyguard(Player player) {
		super(Roles.Bodyguard, player);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		if (targets.size() != 1
				|| !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		return setTargets(targets);
	}

	@Override
	public String doAction() {
		// TODO Auto-generated method stub
		return null;
	}
}
