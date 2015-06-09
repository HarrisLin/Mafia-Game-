package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.Player;
import Resources.GameMessage;

public class Witch extends Character {

	public Witch(Player player) {
		super(Roles.Witch, player);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		// bus driver must only target 2 people
		if (targets.size() != 2) {
			return false;
		}
		try {
			if (!GameEngine.getCharacter(targets.get(0)).isAlive()
					|| GameEngine.getCharacter(targets.get(1)).isAlive()) {
				return false;
			}
		} catch (CannotGetPlayerException e) {
			System.out.println(GameMessage.NO_CHARACTER(targets.get(0),
					targets.get(1)));
			return false;
		}
		actionTarget = new ArrayList<Player>(targets);
		return true;
	}

	@Override
	public String doAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
