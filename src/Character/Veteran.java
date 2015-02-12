package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

public class Veteran extends Character {
	public Veteran(Player player) {
		super(Roles.Veteran, player);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		if (targets.size() != 1
				|| GameEngine.getCharacter(targets.get(0)) != this) {
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
