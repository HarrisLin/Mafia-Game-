package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.CannotGetPlayerException;
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
	
	public BusDriver(Player player) {
		super(Roles.BusDriver, player);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		// bus driver must only target 2 people
		if (targets.size() != 2) {
			return false;
		}
		try {
			if (!GameEngine.getCharacter(targets.get(0)).isAlive()
					|| GameEngine.getCharacter(targets.get(1)).isAlive()) {
				return false;
			}
		} catch (CannotGetPlayerException e) {
			System.out.println(GameMessage.NO_CHARACTER(targets.get(0),
					targets.get(1)));
			return false;
		}
		actionTarget = new ArrayList<Player>(targets);
		return true;
	}

	/**
	 * Swap two characters' in everyone's target list
	 * 
	 * @throws CannotGetPlayerException
	 * 
	 */
	@Override
	public String doAction() throws CannotGetPlayerException {

		List<Player> targets = getTarget();

		if (getTarget().size() != 2) {
			return GameMessage.NO_ACTION();
		}

		if (!GameEngine.getCharacter(targets.get(0)).isAlive()
				|| !GameEngine.getCharacter(targets.get(1)).isAlive()) {
			return GameMessage.TARGET_DEAD();
		}

		if (this.isRoleBlocked()) {
			return GameMessage.NO_FEEDBACK();
		}

		GameEngine.getCharacter(targets.get(0)).addVisitor(getPlayer());
		GameEngine.getCharacter(targets.get(1)).addVisitor(getPlayer());

		for (Character character : GameEngine.getAlivePlayer()) {
			List<Player> players = character.getTarget();
			boolean changed = false;
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).equals(targets.get(0))) {
					players.set(i, targets.get(1));
					changed = true;
				} else if (players.get(i).equals(targets.get(1))) {
					players.set(i, targets.get(0));
					changed = true;
				}
			}
			if(changed) {
				character.setTarget(players);	
			}
		}
		return GameMessage.NO_FEEDBACK();
	}
}
