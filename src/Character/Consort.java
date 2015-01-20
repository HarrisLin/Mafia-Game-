package Character;

import java.util.ArrayList;
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
 * Blocks one player from performing their night actions.
 * 
 * @author pacified
 *
 */
public class Consort extends Character {

	Consort(Player player) {
		super(Roles.Escort, player);
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
	 * Removes target's targets
	 */
	@Override
	public String doAction() {
		if(targets.isEmpty()) {
			return GameMessage.NO_ACTION;
		}
		if(targets.isEmpty()) {
			return GameMessage.NO_ACTION;
		}
		String message = "Thank you for performing your action you filthy whore.";
		if(this.isRoleBlocked()) {
			return message;
		}
		GameEngine.getCharacter(targets.get(0)).addVisitor(getPlayer());
		GameEngine.getCharacter(targets.get(0)).blockNightAction();
		return message;
	}
}