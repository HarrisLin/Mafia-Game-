package Character;

import Enumerators.Roles;
import GameEngine.CannotGetPlayerException;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.GameMessage;
import GameEngine.Player;

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
public class Consort extends Character {

	public Consort(Player player) {

		super(Roles.Consort, player);
	}

	/**
	 * Removes target's targets
	 * @throws CannotGetPlayerException 
	 */
	@Override
	public String doAction() throws CannotGetPlayerException {
		
		if (getTarget().size() != 1) {
			return GameMessage.NO_ACTION;
		}
		
		Player target = getTarget().get(0);
		
		if(!GameEngine.getCharacter(target).isAlive()) {
			return GameMessage.TARGET_DEAD;
		}
		if (this.isRoleBlocked()) {
			return GameMessage.ROLE_BLOCKED;
		}
		
		GameEngine.getCharacter(target).addVisitor(getPlayer());

		GameEngine.getCharacter(target).blockNightAction();
		
		return GameMessage.CONSORT_FEEDBACK;
	}
}