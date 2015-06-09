package Character;

import Enumerators.Roles;
import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.Player;
import Resources.GameMessage;

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

	/**
	 * Performs kill action on target list
	 * @throws CannotGetPlayerException 
	 * @see GameEngine.Character#doAction()
	 */
	@Override
	public String doAction() throws CannotGetPlayerException {
		if (getTarget().size() != 1) {
			return GameMessage.NO_ACTION();
		}
		
		Player target = getTarget().get(0);
		
		if(!GameEngine.getCharacter(target).isAlive()) {
			return GameMessage.TARGET_DEAD();
		}
		if (this.isRoleBlocked()) {
			return GameMessage.NO_FEEDBACK();
		}
		
		GameEngine.getCharacter(target).addVisitor(getPlayer());

		GameEngine.getCharacter(target).kill();	
		
		return GameMessage.NO_FEEDBACK();
	}
}
