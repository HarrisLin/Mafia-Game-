package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

//****************************************************************
//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE
//this is a tag for all the characters/classes that are done
//*****************************************************************

public class Doctor extends Character {

	Doctor(Player player) {
		super(Roles.Doctor, player);
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
		String message = "Thanks doc";
		if (this.isRoleBlocked()) {
			return message;
		}
		GameEngine.getCharacter(targets.get(0)).addVisitor(getPlayer());
		GameEngine.getCharacter(targets.get(0)).healPlayer();
		return message;
	}
}
