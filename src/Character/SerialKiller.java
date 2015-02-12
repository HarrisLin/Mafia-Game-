package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.GameMessage;
import GameEngine.Player;

//****************************************************************
//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE
//this is a tag for all the characters/classes that are done
//*****************************************************************

/**
 * 
 * SerialKiller targets one person to kill at night and is immune at night.
 */

public class SerialKiller extends Character {

	public SerialKiller(Player player) {
		super(Roles.SerialKiller, player, true);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		if (targets.size() != 1
				|| !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		return setTargets(targets);
	}

	/**
	 * questionable-- alive status of victim is important or not?
	 */
	@Override
	public String doAction() {
		List<Player> targets = getTargets();
		if(targets.isEmpty()) {
			return GameMessage.NO_ACTION;
		}
		String message = "You've attempted to kill " + targets.get(0).getName();
		GameEngine.getCharacter(targets.get(0)).addVisitor(getPlayer());
		if(this.isRoleBlocked()) {
			return message;
		}
		GameEngine.getCharacter(targets.get(0)).kill();
		return message;
	}
}