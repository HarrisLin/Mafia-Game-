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
 * Blocks one player from performing their night actions.
 * 
 * @author pacified
 *
 */
public class Escort extends Character {

	public Escort(Player player) {
		super(Roles.Escort, player);
	}

	/**
	 * Removes target's targets
	 * @throws CannotGetPlayerException 
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
			return GameMessage.BLOCK_FEEDBACK();
		}
		
		GameEngine.getCharacter(target).addVisitor(getPlayer());

		GameEngine.getCharacter(target).blockNightAction();
		
		return GameMessage.BLOCK_FEEDBACK();
	}
}
