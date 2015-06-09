package Character;

import java.util.ArrayList;

import Enumerators.Roles;
import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.Player;
import Resources.GameMessage;

/**
 * Arsonist douses and kills
 * 
 * @author pacified
 *
 */

//****************************************************************
//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE
//this is a tag for all the characters/classes that are done
//*****************************************************************

public class Arsonist extends Character {

	public Arsonist(Player player) {
		super(Roles.Arsonist, player, true);
	}

	/**
	 * @throws CannotGetPlayerException
	 * 
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
		
		if (target.getName().equals(this.getPlayer().getName())) {
			// If arsonist targets himself, he ignites
			ArrayList<Player> victims = new ArrayList<Player>();
			for (Character character : GameEngine.getAlivePlayer()) {
				if (character.isDoused()) {
					if (character.kill()) {
						victims.add(character.getPlayer());
					}
				}
			}
			return GameMessage.ARSONIST_KILL(victims);
		} else {
			GameEngine.getCharacter(target).douse();
			return GameMessage.ARSONIST_DOUSE(target);
		}
	}
}
