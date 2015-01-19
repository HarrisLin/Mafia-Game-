package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

/**
 * The Godfather targets a single player to kill and has night immunity.
 */
public class Godfather extends Character {

	Godfather() {
		super(Roles.Godfather, true);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		if(targets.size() == 1)
		{
			this.targets = new ArrayList<Player>(targets);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String doAction() {
		GameEngine.getCharacter(targets.get(0)).kill();	
		return "You've attempted to kill your target" + targets.get(0).getName();
	}
}
