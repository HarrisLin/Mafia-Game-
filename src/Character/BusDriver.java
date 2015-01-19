package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

/**
 * Bus driver swap two characters' in everyone's target list
 * 
 * @author pacified
 *
 */
public class BusDriver extends Character {
	BusDriver() {
		super(Roles.BusDriver);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		// bus driver must only target 2 people
		if (targets.size() != 2
				|| !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		this.targets = new ArrayList<Player>(targets);
		return true;
	}

	/**
	 * Swap two characters' in everyone's target list
	 * 
	 */
	@Override
	public String doAction() {
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
		return "Your night action has been received.";
	}
}
