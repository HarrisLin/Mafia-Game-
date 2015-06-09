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
 * 
 * SerialKiller targets one person to kill at night and is immune at night.
 */

public class SerialKiller extends Character {

	public SerialKiller(Player player) {
		super(Roles.SerialKiller, player, true);
	}

	/**
	 * questionable-- alive status of victim is important or not?
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

		GameEngine.getCharacter(target).kill();	
		
		return GameMessage.NO_FEEDBACK;
	}
}