package Character;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

public class Witch extends Character {
	Witch(Player player) {
		super(Roles.Witch, player);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		if (targets.size() != 2
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
