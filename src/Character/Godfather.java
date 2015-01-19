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
	/**
	 * Sets target for night action, target size must equal 1
	 * @return returns true if target was successfully set, else false
	 * @see GameEngine.Character#setTarget(java.util.List)
	 */
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
	/**
	 * Performs kill action on target list
	 * @see GameEngine.Character#doAction()
	 */
	@Override
	public String doAction() {
		GameEngine.getCharacter(targets.get(0)).kill();	
		return "You've attempted to kill your target" + targets.get(0).getName();
	}
}
