package Character;
import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

public class Vigilante extends Character {
	
	private int shots;
	
	Vigilante(Player player) {
		super(Roles.Vigilante, player);
		shots = 2;
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		if (targets.size() != 1
				|| !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		this.targets = new ArrayList<Player>(targets);
		return true;
	}

	@Override
	public String doAction() {
		String message = "You have " + shots + " left.";
		if(shots == 0) {
			return "You have used all your shots";
		}
		if(this.isRoleBlocked()) {
			return message;
		}
		// TODO Auto-generated method stub
		return message;
	}
}
