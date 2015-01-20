package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

/**
 * 
 * SerialKiller targets one person to kill at night and is immune at night.
 */

public class SerialKiller extends Character {

	SerialKiller(Player player) {
		super(Roles.SerialKiller, player, true);
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

	/**
	 * questionable-- alive status of victim is important or not?
	 */
	@Override
	public String doAction() {
		String message = "You've attempted to kill " + targets.get(0).getName();
		if(this.isRoleBlocked()) {
			return message;
		}
		GameEngine.getCharacter(targets.get(0)).kill();
		return message;
	}
}