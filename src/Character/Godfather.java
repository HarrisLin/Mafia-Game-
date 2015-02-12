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
 * The Godfather targets a single player to kill and has night immunity.
 */
public class Godfather extends Character {

	public Godfather(Player player) {
		super(Roles.Godfather, player, true, true);
	}
	/**
	 * Sets target for night action, target size must equal 1
	 * @return returns true if target was successfully set, else false
	 * @see GameEngine.Character#setTarget(java.util.List)
	 */
	@Override
	public boolean setTarget(List<Player> targets) {
		if (targets.size() != 1
				|| !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		return setTargets(targets);
	}
	/**
	 * Performs kill action on target list
	 * @see GameEngine.Character#doAction()
	 */
	@Override
	public String doAction() {
		List<Player> targets = getTargets();
		if(targets.isEmpty()) {
			return GameMessage.NO_ACTION;
		}
		String message = "You've attempted to kill your target" + targets.get(0).getName();
		GameEngine.getCharacter(targets.get(0)).addVisitor(getPlayer());
		GameEngine.getCharacter(targets.get(0)).kill();	
		return message;
	}
}
