package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

/*
 * SerialKiller targets one person to kill at night and is immune at night.
 */

public class SerialKiller extends Character {

	SerialKiller() {
		super(Roles.SerialKiller, true);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		if (targets.size() == 1) {
			if (GameEngine.AliveList.contains(targets.get(0))) {
				this.targets = new ArrayList<Player>(targets);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/*
	 * questionable-- alive status of victim is important or not?
	 */
	@Override
	public String doAction() {
		GameEngine.getCharacter(targets.get(0)).kill();
		return "You've attempted to kill " + targets.get(0).getName();
	}

}