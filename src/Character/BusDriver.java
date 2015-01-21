package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;
import GameEngine.GameMessage;

//****************************************************************
//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE
//this is a tag for all the characters/classes that are done
//*****************************************************************

/**
 * Bus driver swap two characters' in everyone's target list
 * 
 * @author pacified
 *
 */
public class BusDriver extends Character {
	BusDriver(Player player) {
		super(Roles.BusDriver, player);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		// bus driver must only target 2 people
		if (targets.size() != 2
				|| !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		setTargets(targets);
		return true;
	}

	/**
	 * Swap two characters' in everyone's target list
	 * 
	 */
	@Override
	public String doAction() {
		List<Player> targets = getTargets();
		if(targets.isEmpty()) {
			return GameMessage.NO_ACTION;
		}
		String message = "Your night action has been received.";
		if(this.isRoleBlocked()) {
			return message;
		}
		GameEngine.getCharacter(targets.get(0)).addVisitor(getPlayer());
		GameEngine.getCharacter(targets.get(1)).addVisitor(getPlayer());
		for (Player iterator : GameEngine.getAlivePlayer()) {
			Character character = GameEngine.getCharacter(iterator);
			List<Player> players = character.getTargets();
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).equals(targets.get(0))) {
					players.set(i, targets.get(1));
				}
				else if(players.get(i).equals(targets.get(1))) {
					players.set(i, targets.get(0));
				}
			}
		}
		return message;
	}
}
